package ems.gui.controller;

import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.gui.model.UserModel;
import ems.gui.view.popUps.GeneralExceptionPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class LoginPageController {

    public Label lblWrongCredentials;
    public TextField txfUsername;
    public PasswordField psfPassword;
    public Button btnLogin;

    UserModel userModel;

    public LoginPageController() {

        userModel = new UserModel();
    }

    public void handleLogin(ActionEvent event) {
        String username = txfUsername.getText();
        String password = psfPassword.getText();

        try {
            User loggedUser = userModel.tryLogin(username, password);
            if (loggedUser == null) {
                lblWrongCredentials.setStyle("visibility: visible;");
            } else if (loggedUser.getClass().equals(Admin.class)) {
                Parent root = FXMLLoader.load(getClass().getResource("../view/adminPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (loggedUser.getClass().equals(EventCoordinator.class)) {
                Parent root = FXMLLoader.load(getClass().getResource("../view/eventCoordinatorPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (UnconnecedDatabaseException e) {
            GeneralExceptionPopUp dp = new GeneralExceptionPopUp(e.getMessage());
            Optional result = dp.showAndWait();
        } catch (IOException e) {
            //couldn't load X page
        } catch (Exception e){
            GeneralExceptionPopUp dp = new GeneralExceptionPopUp(e.getMessage());
            Optional result = dp.showAndWait();
        }
    }
}
