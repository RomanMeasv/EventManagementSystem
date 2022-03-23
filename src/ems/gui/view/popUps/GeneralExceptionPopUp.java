package ems.gui.view.popUps;


import ems.gui.controller.GeneralExceptionPopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class GeneralExceptionPopUp extends Dialog {

    private GeneralExceptionPopUpController controller;

    public GeneralExceptionPopUp(String message) {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("generalExceptionPopUp.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Create Event");
            this.setDialogPane(dp);
            controller.setTxfMessage(message);

        } catch (IOException ioex) {
            //System.out.println("Couldn't load view!");
        }
    }
}
