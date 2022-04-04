package ems.gui.controller.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EventTabController implements Initializable {


    public ListView ltvEvents;
    public TextField txfEventName;
    public TextArea txaEventDescription;
    public TextArea txaEventNotes;
    public TextField txfEventStartDate;
    public TextField txfEventStartTime;
    public TextField txfEventEndDate;
    public TextField txfEventEndTime;
    public TextArea txaEventLocation;
    public TextArea txaEventLocationGuidance;
    public ListView ltvEventTicketTypes;
    public TextField txfEventTicketType;
    public Button btnCancelEvent;
    public Button btnApplyEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }


    public void handleFilterEvents(KeyEvent keyEvent) {
    }

    public void handleNewEvent(ActionEvent event) {
    }

    public void handleRemoveEvent(ActionEvent event) {
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
    }

    public void handleAddTicketType(ActionEvent event) {
    }

    public void handleRemoveTicketType(ActionEvent event) {
    }

    public void handleCancelEvent(ActionEvent event) {
    }

    public void handleApplyEvent(ActionEvent event) {
    }
}
