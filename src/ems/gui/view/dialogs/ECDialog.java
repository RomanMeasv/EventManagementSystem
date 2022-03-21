package ems.gui.view.dialogs;


import ems.be.EventCoordinator;
import ems.gui.controller.ECDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class ECDialog extends Dialog<EventCoordinator> {
    private ECDialogController controller;

    public ECDialog() throws Exception {
        super();
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
    }

    public void setFields(EventCoordinator ec) {
        controller.setECName(ec.getUsername());
        controller.setECPassword(ec.getPassword());
    }
}
