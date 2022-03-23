package ems.gui.controller;

import ems.be.EventCoordinator;
import ems.bll.UserValidator;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.UsernameAlreadyTakenException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ECDialogController implements Initializable {
    @FXML
    private TextField txfECName, txfECPassword;

    private UserValidator userValidator;

    private String defaultName = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userValidator = new UserValidator();
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
        EventCoordinator ret = null;
        if (!getECName().isEmpty() && !getECPassword().isEmpty()) {
            ret = new EventCoordinator(getECName(), getECPassword());
        }
        return ret;
    }

    public void txfUsernameKeyTypedHandle(KeyEvent keyEvent) {
        if (!txfECName.getText().equals(defaultName))
        {
            boolean isUsernameValid = false;

            try{
                isUsernameValid = userValidator.validateUsername(txfECName.getText());
            } catch (UsernameAlreadyTakenException uae){
                //display error
            } catch (UnconnecedDatabaseException ude) {
                //display error
            }

            if (isUsernameValid)
            {
                //undo/do stuff
            }
        }
    }
}
