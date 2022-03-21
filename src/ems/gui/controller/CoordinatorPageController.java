package ems.gui.controller;

import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.CreateEventDialog;
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

public class CoordinatorPageController implements Initializable {
    private EventModel  eventModel;
    public TextField txfFilter;
    public TableView tbvEvents;
    public TableColumn colEvents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        colEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEvents.setItems(eventModel.getObservableAllEvents());
    }

    public void handleCreate(MouseEvent mouseEvent) {
        CreateEventDialog dialog = new CreateEventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response ->{
            try{
                eventModel.createEvent(response);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void handleDelete(MouseEvent mouseEvent) {
    }

    public void handleUpdate(MouseEvent mouseEvent) {

    }

    public void handleFilter(KeyEvent keyEvent) {
        
    }


}
