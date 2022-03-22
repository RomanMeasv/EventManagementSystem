package ems.gui.controller;

import ems.be.EventCoordinator;
import ems.bll.EventCoordinatorLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ECDialogController implements Initializable {

    private EventCoordinatorLogic eventCoordinatorLogic;

    public Label lblHeader;

    @FXML
    private TextField txfECName, txfECPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            eventCoordinatorLogic = new EventCoordinatorLogic();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getECName() {
        return txfECName.getText();
    }

    public String getECPassword() {
        return txfECPassword.getText();
    }

    public void setECName(String name) {
        txfECName.setText(name);
    }

    public void setECPassword(String password) {
        txfECPassword.setText(password);
    }

    public EventCoordinator createFromFields() {
        EventCoordinator ret = null;
        if (!getECName().isEmpty() && !getECPassword().isEmpty()) {
            ret = new EventCoordinator(getECName(), getECPassword());
        }
        return ret;
    }

    public void usernameKeyHandle(KeyEvent keyEvent) {
        try{
            String username = txfECName.getText();
            if(eventCoordinatorLogic.isUsernameTaken(username)){
                lblHeader.setStyle("visibility: visible;");
            } else {
                lblHeader.setStyle("visibility: hidden;");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
