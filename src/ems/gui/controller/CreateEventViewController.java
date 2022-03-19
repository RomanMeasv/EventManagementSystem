package ems.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class CreateEventViewController implements Initializable {
    @FXML
    private TextField txtEventName, txtEventDescription, txtNotes, txtStartTime, txtEndTime,txtLocation, txtLocationGuidance;
    @FXML
    private ListView ltvTicketTypes;
    @FXML
    private DatePicker  dtpEndDate;
    @FXML
    private DatePicker dtpStartDate;

    public void initialize(URL location, ResourceBundle resources) {
        LocalDate maxDate = LocalDate.now();
        dtpStartDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }});
        dtpStartDate.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }});
    }

    public String getEventName(){return this.txtEventName.getText();}
    public String getEventDescription(){return this.txtEventDescription.getText();}
    public String getNotes(){return this.txtNotes.getText();}
    public String getLocation(){return this.txtLocation.getText();}
    public String getLocationGuidance(){return this.txtLocationGuidance.getText();}
    public LocalDateTime getStart(){return parseToLocalDateTime(dtpStartDate.getValue(), txtStartTime.getText());}
    public LocalDateTime getEnd(){return parseToLocalDateTime(dtpEndDate.getValue(), txtEndTime.getText()) ;}

    private LocalDateTime parseToLocalDateTime(LocalDate date, String time){
        String now = date +" "+ time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
        return formatDateTime;
    }

    public void setEventName(String eventName){txtEventName.setText(eventName);}
    public void setEventDescription(String EventDescription){txtEventDescription.setText(EventDescription);}
    public void setNotes(String notes){ txtNotes.setText(notes);}
    public void setLocation(String location){txtLocation.setText(location);}
    public void setLocationGuidance(String locationGuidance){txtLocationGuidance.setText(locationGuidance);}
    public void setStartTime(String startTime){txtStartTime.setText(startTime);}
    public void setStartDate(LocalDate startDate){ dtpStartDate.setValue(startDate);}
    public void setEndTime(String endTime){ txtEndTime.setText(endTime);}
    public void setEndDate(LocalDate endDate){dtpEndDate.setValue(endDate);}

    public void handleAddTicketType(){

    }
}
