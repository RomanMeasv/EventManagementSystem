package ems.gui.controller;

import ems.bll.LoginPageLogic;
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

    LoginPageLogic loginLogic;

    public LoginPageController() {
        loginLogic = new LoginPageLogic();
    }

    public void loginAction(ActionEvent event) {
        String inputUsername = tfUsername.getText();
        String inputPassword = pfPassword.getText();

        System.out.println("Login creds:\t" + inputUsername + ":" + inputPassword);
        if(loginLogic.tryLogin(inputUsername, inputPassword)){
            //change views
            try{
                Parent root = null;
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

                if(inputUsername.equals("admin")){
                    root = FXMLLoader.load(getClass().getResource("../view/adminPage.fxml"));
                    System.out.println("Login admin");
                } else if(inputUsername.equals("event")){
                    root = FXMLLoader.load(getClass().getResource("../view/ec-eventPage.fxml"));
                    System.out.println("Login event coordinator");
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
