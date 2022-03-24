package ems.gui.controller;

import ems.be.EventCoordinator;
import ems.bll.util.UserNameValidator;
import ems.bll.exceptions.DatabaseException;
import ems.gui.view.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ECDialogController implements Initializable {
    @FXML
    private TextField txfECName, txfECPassword;

    private UserNameValidator userNameValidator;

    private String defaultName = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameValidator = new UserNameValidator();
    }

    public String getECName() {
        return txfECName.getText();
    }

    public String getECPassword() {
        return txfECPassword.getText();
    }

    public void setECName(String name) {
        defaultName = name;
        txfECName.setText(name);
    }

    public void setECPassword(String password) {
        txfECPassword.setText(password);
    }

    public EventCoordinator createFromFields() {
        if (getECName().isEmpty() || getECPassword().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return null;
        }
        try {
            if (!userNameValidator.isValid(getECName()) && !getECName().equals(defaultName))
            {
                PopUp.showError("Username already in use!");
                return null;
            }
        } catch (DatabaseException e) {
            PopUp.showError("Could not check if username already exists! Are you connected to the database?");
        }
        return new EventCoordinator(getECName(), getECPassword());
    }
}
