package ems.gui.controller;

import ems.be.EventCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class ECDialogController implements Initializable {
    @FXML
    private TextField txfECName, txfECPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


}
