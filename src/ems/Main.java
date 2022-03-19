package ems;

import ems.dal.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/loginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Management System");
        primaryStage.show();

        UserDAO u = new UserDAO();
        System.out.println(u.getAllEventCoordinators().size());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
