package ems.gui.model;

import ems.be.EventCoordinator;
import ems.bll.EventCoordinatorLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class EventCoordinatorModel {
    private EventCoordinatorLogic eventCoordinatorLogic;
    private ObservableList<EventCoordinator> allEventCoordinators;

    public EventCoordinatorModel() throws Exception {
        eventCoordinatorLogic = new EventCoordinatorLogic();
        List<EventCoordinator> list = eventCoordinatorLogic.readAllEventCoordinators();
        allEventCoordinators = FXCollections.observableList(list);
    }

    public ObservableList<EventCoordinator> getAllEventCoordinators() {
        return allEventCoordinators;
    }

    public void createEventCoordinator(EventCoordinator ec) throws Exception {
        EventCoordinator created = eventCoordinatorLogic.createEventCoordinator(ec);
        if (created != null) {
            allEventCoordinators.add(created);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws Exception {
        eventCoordinatorLogic.deleteEventCoordinator(ec);
        allEventCoordinators.remove(ec);
    }

    public void updateEventCoordinator(EventCoordinator oldEC, EventCoordinator updatedEC) throws Exception {
        eventCoordinatorLogic.updateEventCoordinator(updatedEC);
        allEventCoordinators.set(allEventCoordinators.indexOf(oldEC), updatedEC);
    }

    public FilteredList<EventCoordinator> filterEventCoordinators(String query) throws Exception {
        return  eventCoordinatorLogic.filterEventCoordinators(query, allEventCoordinators);

    }
}
