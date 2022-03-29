package ems.gui.controller;

import ems.be.Customer;
import ems.be.EventCoordinator;
import ems.bll.exceptions.DatabaseException;
import ems.gui.view.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDialogController implements Initializable {
    @FXML
    private TextField txfCustomerName, txfCustomerEmail, txfCustomerPhoneNumber, txfCustomerNotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public String getCustomerName(){return txfCustomerName.getText();}

    public String getCustomerEmail(){return txfCustomerEmail.getText();}

    public String getCustomerPhoneNumber(){return txfCustomerPhoneNumber.getText();}

    public String getCustomerNotes(){return txfCustomerNotes.getText();}

    public void setCustomerName(String customerName){txfCustomerName.setText(customerName);}

    public void setCustomerEmail(String customerEmail){txfCustomerName.setText(customerEmail);}

    public void setCustomerPhoneNumber(String customerPhoneNumber){txfCustomerName.setText(customerPhoneNumber);}

    public void setCustomerNotes(String customerNotes){txfCustomerName.setText(customerNotes);}

    public Customer createFromFields() {
        if (getCustomerName().isEmpty() || getCustomerNotes().isEmpty() || getCustomerEmail().isEmpty())  {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return null;
        }
        //customer validator needs to be implemented
        return new Customer(getCustomerName(), getCustomerEmail(),getCustomerPhoneNumber(),getCustomerNotes());
    }

}
