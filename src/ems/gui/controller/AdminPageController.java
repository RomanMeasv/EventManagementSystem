package ems.gui.controller;

import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.view.dialogs.ECDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public TableView<EventCoordinator> tbvCoordinators;
    public TableColumn<EventCoordinator, String> colCoordinators;
    public TextField txfFilter;

    private EventCoordinatorModel eventCoordinatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventCoordinatorModel = new EventCoordinatorModel();
            colCoordinators.setCellValueFactory(new PropertyValueFactory<>("username"));
            tbvCoordinators.setItems(eventCoordinatorModel.getObservableEventCoordinators());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleCreate(MouseEvent mouseEvent) {
        try {
            ECDialog dialog = new ECDialog();
            Optional<EventCoordinator> result = dialog.showAndWait();
            if (result.isPresent()) {
                eventCoordinatorModel.createEventCoordinator(result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {
        try {
            EventCoordinator selected = tbvCoordinators.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventCoordinatorModel.deleteEventCoordinator(selected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdate(MouseEvent mouseEvent) {
        try{
            EventCoordinator oldEC = tbvCoordinators.getSelectionModel().getSelectedItem();
            if (oldEC != null) {
                ECDialog dialog = new ECDialog();
                dialog.setFields(oldEC);
                Optional<EventCoordinator> result = dialog.showAndWait();
                if(result.isPresent()){
                    EventCoordinator updatedEC = result.get();
                    updatedEC.setId(oldEC.getId());
                    eventCoordinatorModel.updateEventCoordinator(oldEC, updatedEC);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void handleFilter(KeyEvent event) {
        try{
            String query = txfFilter.getText();
            eventCoordinatorModel.filterEventCoordinators(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
