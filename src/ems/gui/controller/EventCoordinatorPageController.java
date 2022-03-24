package ems.gui.controller;

import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.ECDialog;
import ems.gui.view.dialogs.EventDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.management.openmbean.OpenMBeanAttributeInfo;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {

    private EventModel eventModel;

    public TextField txfFilter;
    public TableView<Event> tbvEvents;
    public TableColumn<Event, String> colEvents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        colEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEvents.setItems(eventModel.getObservableEvents());
    }

    public void handleCreate(MouseEvent mouseEvent) {
        EventDialog dialog = new EventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {

                eventModel.createEvent(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void handleDelete(MouseEvent mouseEvent) {
        try {
            Event selected = tbvEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdate(MouseEvent mouseEvent) {
        try {
            Event oldEvent = tbvEvents.getSelectionModel().getSelectedItem();
            if (oldEvent != null) {
                EventDialog dialog = new EventDialog();
                dialog.setFields(oldEvent);
                Optional<Event> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Event updatedEvent = result.get();
                    updatedEvent.setId(oldEvent.getId());
                    eventModel.updateEvent(oldEvent, updatedEvent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleFilter(KeyEvent keyEvent) {
        try {
            String query = txfFilter.getText();
            eventModel.filterEvents(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
