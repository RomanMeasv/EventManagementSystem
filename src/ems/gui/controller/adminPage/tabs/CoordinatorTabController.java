package ems.gui.controller.adminPage.tabs;

import com.google.zxing.qrcode.decoder.Mode;
import ems.be.Event;
import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.model.ModelFacade;
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


    public ListView <EventCoordinator> ltvCoordinators;
    public TextField txfFilterCoordinators;
    public TextField txfFilterEvents;
    public ComboBox cmbEvents;
    public TextField txfEventCoordinator;
    public Button btnCancelCoordinator;
    public Button btnApplyCoordianator;
    public ListView <Event>ltvCoordinatorsEvents;

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
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
    }


    public void handleAddEvent(ActionEvent event) {
    }

    public void handleRemoveEvent(ActionEvent event) {
    }

    public void handleFilterCoordinators(KeyEvent keyEvent) {
    }

    public void handleCancelCoordianator(ActionEvent event) {
    }

    public void handleApplyCoordianator(ActionEvent event) {
    }
}
