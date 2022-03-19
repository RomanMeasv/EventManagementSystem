package ems.gui.model;

import ems.be.EventCoordinator;
import ems.bll.AdminLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AdminModel {
    private AdminLogic adminLogic;
    private ObservableList<EventCoordinator> observableList;

    public AdminModel(){
        adminLogic = new AdminLogic();
        List<EventCoordinator> list = adminLogic.readEventCoordinators();
        observableList = FXCollections.observableList(list);
    }

    public ObservableList<EventCoordinator> getObservableList(){
        return observableList;
    }

    public void createEventCoordinator(EventCoordinator ec) throws Exception {
        EventCoordinator created = adminLogic.createEventCoordinator(ec);
        if(created != null){
            observableList.add(created);
        }
    }
}
