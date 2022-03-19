package ems.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.w3c.dom.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ECDialogController implements Initializable {
    @FXML
    private TextField txfECName, txfECPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public String getECName(){return txfECName.getText();}
    public String getECPassword(){return txfECPassword.getText();}

    public void setECName(String name){txfECName.setText(name);}
    public void setECPassword(String password){txfECPassword.setText(password);}



}
