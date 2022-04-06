package ems.gui.controller.tabs;

import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventTabController implements Initializable {

    public ListView<Event> ltvEvents;
    public TextField txfFilterEvents;

    public TextField txfEventName;
    public TextArea txaEventDescription;
    public TextArea txaEventNotes;
    public TextField txfEventStartDate;
    public TextField txfEventStartTime;
    public TextField txfEventEndDate;
    public TextField txfEventEndTime;
    public TextArea txaEventLocation;
    public TextArea txaEventLocationGuidance;

    public ListView<String> ltvEventTicketTypes;
    public TextField txfEventTicketType;

    public Button btnCancelEvent;
    public Button btnApplyEvent;

    private ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }

        ltvEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedEvent) -> {
            if (selectedEvent != null) {
                selectedEventListener(selectedEvent);
            }
        });

        ltvEvents.setItems(facade.getAllEvents());
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            ltvEvents.setItems(facade.getFilteredEvents(query));
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewEvent(ActionEvent event) {
        setDisableApplyButtons(false);

        ltvEvents.getSelectionModel().clearSelection();

        clearEventDetails();
    }

    public void handleRemoveEvent(ActionEvent event) {
        try {
            Event selected = ltvEvents.getSelectionModel().getSelectedItem();
            if (selected == null)
                return;

            facade.deleteEvent(selected);

            setDisableApplyButtons(true);

            clearEventDetails();

            ltvEvents.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
        //TODO: Filter ticket types
    }

    public void handleAddTicketType(ActionEvent event) {
        //If no string is provided
        if (txfEventTicketType.getText().isEmpty()) {
            return;
        }

        //If ticket type is already in list
        if (ltvEventTicketTypes.getItems().contains(txfEventTicketType.getText())) {
            txfEventTicketType.clear();
            return;
        }

        //Add ticket type & clear the text field
        ltvEventTicketTypes.getItems().add(txfEventTicketType.getText());
        txfEventTicketType.clear();
    }

    public void handleRemoveTicketType(ActionEvent event) {
        ltvEventTicketTypes.getItems().remove(ltvEventTicketTypes.getSelectionModel().getSelectedItem());
    }

    public void handleCancelEvent(ActionEvent event) {
        Event selectedEvent = ltvEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) { //a new event was about to be created
            clearEventDetails();
            btnApplyEvent.setDisable(true);
            btnCancelEvent.setDisable(true);
        } else //an existing event was about to be edited
        {
            fillEventDetails(selectedEvent);
        }
    }

    public void handleApplyEvent(ActionEvent event) {
        Event selectedEvent = ltvEvents.getSelectionModel().getSelectedItem();

        //check if all mandatory fields are filled out
        if (txfEventName.getText().isEmpty() ||
                txfEventStartDate.getText().isEmpty() ||
                txfEventStartTime.getText().isEmpty() ||
                txfEventEndDate.getText().isEmpty() ||
                txfEventEndTime.getText().isEmpty() ||
                txaEventLocation.getText().isEmpty() ||
                ltvEventTicketTypes.getItems().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields and add at least one ticket type! (*)");
            return;
        }

        //check if dates can be parsed (if they are valid)
        LocalDateTime start;
        LocalDateTime end;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            start = LocalDateTime.parse(txfEventStartDate.getText() + " " + txfEventStartTime.getText(), formatter);
            end = LocalDateTime.parse(txfEventEndDate.getText() + " " + txfEventEndTime.getText(), formatter);
        } catch (Exception e) {
            PopUp.showError("Invalid date format!");
            return;
        }

        if (start.isAfter(end)) {
            PopUp.showError("Start date cannot be after end date!");
            return;
        }

        try {
            if (selectedEvent == null) { //it's a new event

                if (facade.getAllEvents().stream().map(Event::getName).toList().contains(txfEventName.getText())) {
                    PopUp.showError("Event name already in use!");
                    return;
                }

                facade.createEvent(
                        new Event(txfEventName.getText(),
                                txaEventDescription.getText(),
                                txaEventNotes.getText(),
                                start,
                                end,
                                txaEventLocation.getText(),
                                txaEventLocationGuidance.getText(),
                                ltvEventTicketTypes.getItems()));

                setDisableApplyButtons(true);

                clearEventDetails();
            } else { //it's an existing event

                if (facade.getAllEvents().stream().map(Event::getName).toList().contains(txfEventName.getText()) && !txfEventName.getText().equals(selectedEvent.getName())) {
                    PopUp.showError("Event name already in use!");
                    return;
                }

                selectedEvent.setName(txfEventName.getText());
                selectedEvent.setDescription(txaEventDescription.getText());
                selectedEvent.setNotes(txaEventNotes.getText());
                selectedEvent.setStart(start);
                selectedEvent.setEnd(end);
                selectedEvent.setLocation(txaEventLocation.getText());
                selectedEvent.setLocationGuidance(txaEventLocationGuidance.getText());
                selectedEvent.setTicketTypes(ltvEventTicketTypes.getItems());

                facade.updateEvent(selectedEvent);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        //ltvEvents.refresh(); //solves many problems yes
    }

    private void selectedEventListener(Event selectedEvent) {
        setDisableApplyButtons(false);
        fillEventDetails(selectedEvent);
    }

    private void setDisableApplyButtons(boolean value) {
        btnApplyEvent.setDisable(value);
        btnCancelEvent.setDisable(value);
    }

    private void clearEventDetails() {
        txfEventName.clear();
        txaEventDescription.clear();
        txaEventNotes.clear();
        txfEventStartDate.clear();
        txfEventStartTime.clear();
        txfEventEndDate.clear();
        txfEventEndTime.clear();
        txaEventLocation.clear();
        txaEventLocationGuidance.clear();
        ltvEventTicketTypes.getItems().clear();
        txfEventTicketType.clear();
    }

    private void fillEventDetails(Event event) {
        txfEventName.setText(event.getName());
        txaEventDescription.setText(event.getDescription());
        txaEventNotes.setText(event.getNotes());
        txfEventStartTime.setText(event.getStart().toLocalTime().toString());
        txfEventStartDate.setText(event.getStart().toLocalDate().toString());
        txfEventEndTime.setText(event.getEnd().toLocalTime().toString());
        txfEventEndDate.setText(event.getEnd().toLocalDate().toString());
        txaEventLocation.setText(event.getLocation());
        txaEventLocationGuidance.setText(event.getLocationGuidance());
        ltvEventTicketTypes.setItems(FXCollections.observableList(event.getTicketTypes()));
    }
}
