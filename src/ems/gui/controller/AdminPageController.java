package ems.gui.controller;

import ems.be.EventCoordinator;
import ems.gui.view.ECDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public TableView tbvCoordinators;
    public TableColumn colCoordinators;
    public TextField txfFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleFilter(ActionEvent event) {

    }

    public void handleCreate(MouseEvent mouseEvent) {
        ECDialog dialog = new ECDialog();
        Optional <EventCoordinator> result = dialog.showAndWait();

    }

    public void handleDelete(MouseEvent mouseEvent) {

    }

    public void handleUpdate(MouseEvent mouseEvent) {

    }
}
