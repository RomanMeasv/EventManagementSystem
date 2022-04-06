package ems.gui.controller;


import ems.be.Event;
import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.model.ModelFacade;
import ems.gui.view.dialogs.ECDialog;
import ems.gui.view.util.PopUp;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {


    public ListView ltvOverviewEvents;
    public TextField txfFilterEvents;
    public TextField txfFilterECs;
    public Label lbl;

    private ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleCreate(MouseEvent mouseEvent) {

    }

    public void handleDelete(MouseEvent mouseEvent) {

    }

    public void handleUpdate(MouseEvent mouseEvent) {

    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            FilteredList<Event> filteredEvents = facade.getFilteredEvents(query);
            ltvOverviewEvents.setItems(filteredEvents);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }


    public void handleLogout(MouseEvent mouseEvent) {

//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("../view/loginView.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
