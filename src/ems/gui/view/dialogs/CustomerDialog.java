package ems.gui.view.dialogs;


import ems.be.Customer;
import ems.gui.controller.CustomerDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class CustomerDialog extends Dialog<Customer> {
    private CustomerDialogController controller;

    public CustomerDialog()  {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerDialog.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Create/Edit Customer");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.APPLY) {
                    Customer created = controller.createFromFields();
                    return created;
                }
                return null;
            });
        }catch (IOException ioex){

        }
    }
    public void setFields(Customer customer){
        controller.setCustomerName(customer.getName());
        controller.setCustomerEmail(customer.getEmail());
        controller.setCustomerPhoneNumber(customer.getPhoneNumber());
        controller.setCustomerNotes(customer.getNotes());
    }
}

