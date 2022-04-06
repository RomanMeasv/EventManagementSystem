package ems.gui.controller.adminPage.tabs;

import com.google.zxing.qrcode.decoder.Mode;
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
    public TextField txfEventCoordinator;
    public Button btnCancelCoordinator;
    public Button btnApplyCoordinator;
    public ListView<Event> ltvCoordinatorsEvents;
    public TextField txfName;

    private ModelFacade facade;
    private EventCoordinatorModel eventCoordinatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ltvCoordinators.setItems(eventCoordinatorModel.getAllEventCoordinators());

    }


    public void handleNewCoordianator(ActionEvent event) {
    }

    public void handleRemoveCoordianator(ActionEvent event) {
        EventCoordinator coordinator = ltvCoordinators.getSelectionModel().getSelectedItem();
        try {
            eventCoordinatorModel.deleteEventCoordinator(coordinator);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }


    public void handleFilterCoordinators(KeyEvent keyEvent) {
        String query = txfEventCoordinator.getText();
        try {
            ltvCoordinators.setItems(eventCoordinatorModel.filterEventCoordinators(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCancelCoordianator(ActionEvent event) {
    }

    public void handleApplyCoordianator(ActionEvent event) {
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
    }

    public void handleAddEvent(ActionEvent event) {
    }

    public void handleRemoveEvent(ActionEvent event) {
    }

    private void clearFields(){
        txfName.clear();
    }
}
