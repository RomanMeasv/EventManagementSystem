package ems.gui.view.dialogs;

import ems.be.Event;
import ems.gui.controller.EventDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
;


import java.io.IOException;

public class EventDialog extends Dialog<Event> {
    private EventDialogController controller;

    public EventDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("eventDialog.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Create Event");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.APPLY) {
                    return controller.createFromFields();
                }

                return null;
            });

        } catch (IOException ioex) {
            //System.out.println("Couldn't load view!");
        }
    }

    public void setFields(Event e) {
        controller.setEventName(e.getName());
        controller.setEventDescription(e.getDescription());
        controller.setNotes(e.getNotes());

        String startDate = e.getStart().toLocalDate().toString();
        String startTime = e.getStart().toLocalTime().toString();
        controller.setStartDate(startDate);
        controller.setStartTime(startTime);

        String endDate = e.getEnd().toLocalDate().toString();
        String endTime = e.getEnd().toLocalTime().toString();
        controller.setEndDate(endDate);
        controller.setEndTime(endTime);

        controller.setLocation(e.getLocation());
        controller.setLocationGuidance(e.getLocationGuidance());

        controller.setTicketTypes(e.getTicketTypes());
    }
}
