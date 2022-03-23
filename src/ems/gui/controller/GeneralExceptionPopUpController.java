package ems.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GeneralExceptionPopUpController {
    @FXML
    private Label txfMessage;

    public void setTxfMessage(String message){
        txfMessage.setText(message);
    }
}
