package ems.gui.controller;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.gui.model.AdminModel;
import ems.gui.view.ECDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public TableView<EventCoordinator> tbvCoordinators;
    public TableColumn<EventCoordinator, String> colCoordinators;
    public TextField txfFilter;

    private AdminModel adminModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new AdminModel();
            colCoordinators.setCellValueFactory(new PropertyValueFactory<>("username"));
            tbvCoordinators.setItems(adminModel.getObservableEventCoordinators());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleFilter(ActionEvent event) {

    }

    public void handleCreate(MouseEvent mouseEvent) {
        try {
            ECDialog dialog = new ECDialog();
            Optional<EventCoordinator> result = dialog.showAndWait();
            if (result.isPresent()) {
                adminModel.createEventCoordinator(result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        try {
            adminModel.deleteEventCoordinator(tbvCoordinators.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdate(MouseEvent mouseEvent) {

    }
}
