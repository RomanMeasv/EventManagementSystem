package ems.gui.view;


import ems.be.EventCoordinator;
import ems.be.User;
import ems.gui.controller.ECDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class ECDialog extends Dialog<EventCoordinator> {
    private ECDialogController controller;

    public ECDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ecDialog.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Create/Edit EC");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if (buttonType == ButtonType.APPLY) {
                    EventCoordinator created = controller.createFromFields();
                    return created;
                }
                return null;
            });

        } catch (IOException ioex) {
            //System.out.println("Couldn't load view!");
        }
    }
}
