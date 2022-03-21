package ems.gui.model;

import ems.be.EventCoordinator;
import ems.bll.EventCoordinatorLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventCoordinatorModel {
    private EventCoordinatorLogic eventCoordinatorLogic;
    private ObservableList<EventCoordinator> observableEventCoordinators;

    public EventCoordinatorModel() throws Exception {
        eventCoordinatorLogic = new EventCoordinatorLogic();
        List<EventCoordinator> list = eventCoordinatorLogic.readAllEventCoordinators();
        observableEventCoordinators = FXCollections.observableList(list);
    }

    public ObservableList<EventCoordinator> getObservableEventCoordinators() {
        return observableEventCoordinators;
    }

    public void createEventCoordinator(EventCoordinator ec) throws Exception {
        EventCoordinator created = eventCoordinatorLogic.createEventCoordinator(ec);
        if (created != null) {
            observableEventCoordinators.add(created);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws Exception {
        eventCoordinatorLogic.deleteEventCoordinator(ec);
        observableEventCoordinators.remove(ec);
    }

    public void updateEventCoordinator(EventCoordinator oldEC, EventCoordinator updatedEC) throws Exception {
        eventCoordinatorLogic.updateEventCoordinator(updatedEC);
        observableEventCoordinators.set(observableEventCoordinators.indexOf(oldEC), updatedEC);
    }

    public void filterEventCoordinators(String query) throws Exception {
        List<EventCoordinator> filtered = eventCoordinatorLogic.filterEventCoordinators(query);
        observableEventCoordinators.setAll(filtered);
    }
}
