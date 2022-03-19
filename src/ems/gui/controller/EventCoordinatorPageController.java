package ems.gui.controller;

import ems.be.Event;
import ems.gui.view.Dialogs.CreateMovieDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {

    public TableView<Event> tvEvents;
    public TableColumn<Event, String> tvColEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvColEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));

        ObservableList<Event> list = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            Event e = new Event("e"+i);
            list.add(e);
        }

        tvEvents.setItems(list);

    }

    public void handleAddEvent() {
        CreateMovieDialog createMovieDialog = new CreateMovieDialog();
        Optional<Event> result = createMovieDialog.showAndWait();


    }
}
