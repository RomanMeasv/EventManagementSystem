package ems.gui.controller.adminPage.tabs;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorTabController implements Initializable {


    public ListView<EventCoordinator> ltvCoordinators;
    public TextField txfFilterCoordinators;
    public ComboBox cmbEvents;
    public Button btnCancelCoordinator;
    public Button btnApplyCoordinator;
    public ListView<Event> ltvCoordinatorsEvents;
    public TextField txfName;
    public TextField txfPassword;
    public TextField txfFilterEvent;

    private ModelFacade facade;
    private EventCoordinatorModel eventCoordinatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        ltvCoordinators.setItems(eventCoordinatorModel.getAllEventCoordinators());

        ltvCoordinators.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCoordinator) -> {
            if (selectedCoordinator != null) {
                selectedCoordinatorListener(selectedCoordinator);
            }
        });
    }


    public void handleNewCoordianator(ActionEvent event) {
        setDisableApplyButtons(false);

        ltvCoordinators.getSelectionModel().clearSelection();

        clearFields();
    }

    public void handleRemoveCoordianator(ActionEvent event) {
        EventCoordinator coordinator = ltvCoordinators.getSelectionModel().getSelectedItem();
        try {
            if (coordinator == null) {
                return;
            }
            eventCoordinatorModel.deleteEventCoordinator(coordinator);

            setDisableApplyButtons(true);

            clearFields();

            ltvCoordinators.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }


    public void handleFilterCoordinators(KeyEvent keyEvent) {
        String query = txfFilterCoordinators.getText();
        try {
            ltvCoordinators.setItems(eventCoordinatorModel.filterEventCoordinators(query));
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleCancelCoordianator(ActionEvent event) {
        EventCoordinator coordinator = ltvCoordinators.getSelectionModel().getSelectedItem();
        if (coordinator == null) {
            clearFields();
            setDisableApplyButtons(true);
        } else {
            fillCoordinator(coordinator);
        }
    }

    public void handleApplyCoordianator(ActionEvent event) {
        EventCoordinator selectedCoordinator = ltvCoordinators.getSelectionModel().getSelectedItem();
        if (txfName.getText().isEmpty() ||
                txfPassword.getText().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return;
        }

        try {
            if (selectedCoordinator == null) { //it's a new customer
                eventCoordinatorModel.createEventCoordinator(
                        new EventCoordinator(
                                txfName.getText(),
                                txfPassword.getText()));
                //events need dao for this shiet

                setDisableApplyButtons(true);

                clearFields();
            } else { //it's an existing customer
                selectedCoordinator.setUsername(txfName.getText());
                selectedCoordinator.setPassword(txfPassword.getText());

                eventCoordinatorModel.updateEventCoordinator(selectedCoordinator);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        String query = txfFilterEvent.getText();
//        try {
//            ltvCoordinatorsEvents.setItems(eventCoordinatorModel.filterEventCoordinators(query));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void handleAddEvent(ActionEvent event) {
    }

    public void handleRemoveEvent(ActionEvent event) {
    }

    private void clearFields() {
        txfName.clear();
        txfPassword.clear();
    }

    private void fillCoordinator(EventCoordinator coordinator) {
        txfName.setText(coordinator.getUsername());
        txfPassword.setText(coordinator.getPassword());
    }

    private void setDisableApplyButtons(Boolean state) {
        btnCancelCoordinator.setDisable(state);
        btnApplyCoordinator.setDisable(state);
    }

    private void selectedCoordinatorListener(EventCoordinator selectedCoordinator) {
        setDisableApplyButtons(false);
        fillCoordinator(selectedCoordinator);
    }

}
