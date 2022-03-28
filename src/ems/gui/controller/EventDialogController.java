package ems.gui.controller;

import ems.be.Event;
import ems.bll.exceptions.DatabaseException;
import ems.bll.util.EventNameValidator;

import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventDialogController implements Initializable {
    EventNameValidator eventNameValidator;

    String defaultEventName = null;
    @FXML
    private TextField txfEventName, txfEventDescription, txfNotes, txfStartDate, txfStartTime;

    @FXML
    private TextField txfEndDate, txfEndTime, txfLocation, txfLocationGuidance;

    @FXML
    private ListView<String> ltvTicketTypes;

    @FXML
    private TextField txfFilter, txfTicketType;

    public void initialize(URL location, ResourceBundle resources) {
        eventNameValidator = new EventNameValidator();
    }

    public String getEventName() {
        return txfEventName.getText();
    }

    public String getEventDescription() {
        return txfEventDescription.getText();
    }

    public String getNotes() {
        return txfNotes.getText();
    }

    public String getLocation() {
        return txfLocation.getText();
    }

    public String getLocationGuidance() {
        return txfLocationGuidance.getText();
    }

    public LocalDateTime getStart() {
        String startDate = txfStartDate.getText();
        String startTime = txfStartTime.getText();
        return parseToLocalDateTime(startDate, startTime);
    }

    public LocalDateTime getEnd() {
        String endDate = txfEndDate.getText();
        String endTime = txfEndTime.getText();
        return parseToLocalDateTime(endDate, endTime);
    }

    public List<String> getTicketTypes(){
        return List.copyOf(ltvTicketTypes.getItems());
    }

    public void setEventName(String eventName) {
        defaultEventName = eventName;
        txfEventName.setText(eventName);
    }

    public void setEventDescription(String EventDescription) {
        txfEventDescription.setText(EventDescription);
    }

    public void setNotes(String notes) {
        txfNotes.setText(notes);
    }

    public void setLocation(String location) {
        txfLocation.setText(location);
    }

    public void setLocationGuidance(String locationGuidance) {
        txfLocationGuidance.setText(locationGuidance);
    }

    public void setStartTime(String startTime) {
        txfStartTime.setText(startTime);
    }

    public void setStartDate(String startDate) {
        txfStartDate.setText(startDate);

    }

    public void setEndTime(String endTime) {
        txfEndTime.setText(endTime);
    }

    public void setEndDate(String endDate) {
        txfEndDate.setText(endDate);
    }

    public void setTicketTypes(List<String> ticketTypes){
        ltvTicketTypes.setItems(FXCollections.observableList(new ArrayList<>(ticketTypes)));
    }

    public void handleAddTicketType(ActionEvent event) {
        String ticketType = txfTicketType.getText();
        if (!ltvTicketTypes.getItems().contains(ticketType) && !ticketType.isEmpty()) {
            ltvTicketTypes.getItems().add(txfTicketType.getText());
        }
        txfTicketType.clear();
    }

    public void handleRemoveTicketType(ActionEvent event) {
        String selecedTicketType = ltvTicketTypes.getSelectionModel().getSelectedItem();
        if (selecedTicketType != null) {
            ltvTicketTypes.getItems().remove(selecedTicketType);
        }
    }

    private String formatDate(String provided) {
        String ret = "";
        for (char c :
                provided.toCharArray()) {
            if ((c >= 48 && c <= 57)) { //0-9
                ret += c;
            }
            if ((ret.length() == 4) || (ret.length() == 7)) {
                ret += '-';
            }
        }
        ret = ret.substring(0, Math.min(ret.length(), 10));
        return ret;
    }

    private String formatTime(String provided) {
        String ret = "";
        for (char c :
                provided.toCharArray()) {
            if ((c >= 48 && c <= 57)) { //0-9
                ret += c;
            }
            if (ret.length() == 2) {
                ret += ':';
            }
        }
        ret = ret.substring(0, Math.min(ret.length(), 5));
        return ret;
    }

    public void startDateKeyTypedHandle(KeyEvent keyEvent) {
        String input = txfStartDate.getText();
        String output = formatDate(input);
        txfStartDate.setText(output);
        txfStartDate.positionCaret(output.length());
    }

    public void startTimeKeyTypedHandle(KeyEvent keyEvent) {
        String input = txfStartTime.getText();
        String output = formatTime(input);
        txfStartTime.setText(output);
        txfStartTime.positionCaret(output.length());
    }

    public void endDateKeyTypedHandle(KeyEvent keyEvent) {
        String input = txfEndDate.getText();
        String output = formatDate(input);
        txfEndDate.setText(output);
        txfEndDate.positionCaret(output.length());
    }

    public void endTimeKeyTypedHandle(KeyEvent keyEvent) {
        String input = txfEndTime.getText();
        String output = formatTime(input);
        txfEndTime.setText(output);
        txfEndTime.positionCaret(output.length());
    }

    private LocalDateTime parseToLocalDateTime(String date, String time) {
        if (!date.isEmpty() && !time.isEmpty()) {
            String DT = date + " " + time;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                return LocalDateTime.parse(DT, formatter);
            } catch (Exception e) {

                return null;
            }
        } else
            return null;
    }

    public Event createFromFields() {
        if (getEventName().isEmpty() || txfStartDate.getText().isEmpty() || txfStartTime.getText().isEmpty() ||
                txfEndDate.getText().isEmpty() || txfEndTime.getText().isEmpty() || getLocation().isEmpty() || ltvTicketTypes.getItems().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return null;
        }
        try {
            if (!eventNameValidator.isValid(getEventName()) && !getEventName().equals(defaultEventName)) {
                PopUp.showError("Username already in use!");
                return null;
            }
        } catch (DatabaseException e) {
            PopUp.showError("Could not check if username already exists! Are you connected to the database?");
            return null;
        }

        if (getStart() == null || getEnd() == null) {
            PopUp.showError("Day time invalid!");
            return null;
        }
        if (getStart().isAfter(getEnd())) {
            PopUp.showError("Start date cannot be placed after end date");
            return null;
        }

        return new Event(getEventName(), getEventDescription(), getNotes(), getStart(), getEnd(), getLocation(), getLocationGuidance(), getTicketTypes());
    }
}
