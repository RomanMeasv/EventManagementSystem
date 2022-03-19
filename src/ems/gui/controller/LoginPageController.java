package ems.gui.controller;

import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.gui.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginPageController {

    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button loginButton;

    UserModel userModel;

    public LoginPageController() throws Exception {

        userModel = new UserModel();
    }

    public void loginAction(ActionEvent event) {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        try {
            User loggedUser = userModel.tryLogin(username, password);
            if(loggedUser.getClass().equals(Admin.class)){
                Parent root = FXMLLoader.load(getClass().getResource("../view/adminPage.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if(loggedUser.getClass().equals(EventCoordinator.class)){
                Parent root = FXMLLoader.load(getClass().getResource("../view/coordinatorPage.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if(loggedUser == null){
                //TODO: implement "entered wrong credentials"

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
