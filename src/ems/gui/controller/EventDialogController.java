package ems.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventDialogController implements Initializable {
    @FXML
    private TextField txfEventName, txfEventDescription, txfNotes, txfStartTime, txfEndTime, txfLocation, txfLocationGuidance;
    @FXML
    private ListView ltvTicketTypes;
    @FXML
    private DatePicker dtpStartDate;
    @FXML
    private DatePicker dtpEndDate;

    public void initialize(URL location, ResourceBundle resources) {
        LocalDate maxDate = LocalDate.now();
        dtpStartDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }
                });
        dtpStartDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }
                });
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
        return parseToLocalDateTime(dtpStartDate.getValue(), txfStartTime.getText());
    }

    public LocalDateTime getEnd() {
        return parseToLocalDateTime(dtpEndDate.getValue(), txfEndTime.getText());
    }

    private LocalDateTime parseToLocalDateTime(LocalDate date, String time) {
        String now = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        return formatDateTime;
    }

    public void setEventName(String eventName) {
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

    public void setStartDate(LocalDate startDate) {
        dtpStartDate.setValue(startDate);
    }

    public void setEndTime(String endTime) {
        txfEndTime.setText(endTime);
    }

    public void setEndDate(LocalDate endDate) {
        dtpEndDate.setValue(endDate);
    }

    public void handleAddTicketType() {

    }
}
