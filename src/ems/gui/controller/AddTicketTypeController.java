package ems.gui.controller;


import ems.be.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class AddTicketTypeController extends Dialog {

    public AddTicketTypeController controller;
    public AddTicketTypeController(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addTicketTypeDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Add Ticket");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType == ButtonType.APPLY){
                    return new Event(controller.getEventName());
                }
                return null;
            });

        } catch (IOException ioex){
            //System.out.println("Couldn't load view!");
        }
    }
}
}
