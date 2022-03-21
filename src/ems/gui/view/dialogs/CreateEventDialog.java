package ems.gui.view.dialogs;

import ems.be.Event;
import ems.gui.controller.CreateEventViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class CreateEventDialog extends Dialog<Event> {
    public CreateEventViewController controller;
        public CreateEventDialog(){
            super();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createEventDialogView.fxml"));
                DialogPane dp = loader.load();
                controller = loader.getController();
                this.setTitle("Create Event");
                this.setDialogPane(dp);
                this.setResultConverter(buttonType -> {
                    if(buttonType == ButtonType.APPLY){
                        return new Event(controller.getEventName(), controller.getEventDescription(), controller.getNotes(),
                                            controller.getStart(), controller.getEnd(), controller.getLocation(),
                                            controller.getLocationGuidance());
                    }
                    return null;
                });

            } catch (IOException ioex){
                //System.out.println("Couldn't load view!");
            }
        }
}
