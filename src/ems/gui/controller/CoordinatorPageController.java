package ems.gui.controller;

import ems.be.Event;
import ems.gui.view.dialogs.CreateEventDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class CoordinatorPageController {
    public TextField txfFilter;
    public TableView tbvEvents;
    public TableColumn colEvents;

    public void handleCreate(MouseEvent mouseEvent) {
        CreateEventDialog dialog = new CreateEventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response ->{
            try{
                
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
