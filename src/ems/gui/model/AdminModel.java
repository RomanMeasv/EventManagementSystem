package ems.gui.model;

import ems.be.EventCoordinator;
import ems.bll.AdminLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AdminModel {
    private AdminLogic adminLogic;
    private ObservableList<EventCoordinator> observableEventCoordinators;

    public AdminModel() throws Exception {
        adminLogic = new AdminLogic();
        List<EventCoordinator> list = adminLogic.readAllEventCoordinators();
        observableEventCoordinators = FXCollections.observableList(list);
    }

    public ObservableList<EventCoordinator> getObservableEventCoordinators() {
        return observableEventCoordinators;
    }

    public void createEventCoordinator(EventCoordinator ec) throws Exception {
        EventCoordinator created = adminLogic.createEventCoordinator(ec);
        if (created != null) {
            observableEventCoordinators.add(created);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws Exception {
        adminLogic.deleteEventCoordinator(ec);
        observableEventCoordinators.remove(ec);
    }
}
