package ems.gui.controller.adminPage;

import ems.gui.view.util.PopUp;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {


    public TabPane tabPaneAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleLogout(Event event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ems/gui/view/loginView.fxml"));
            Stage stage = (Stage) tabPaneAdmin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }
}
