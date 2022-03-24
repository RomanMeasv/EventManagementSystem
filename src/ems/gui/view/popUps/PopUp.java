package ems.gui.view.popUps;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopUp {
    public static void showError(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Something went wrong...");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
