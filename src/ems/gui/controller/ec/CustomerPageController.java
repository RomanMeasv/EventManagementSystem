package ems.gui.controller.ec;

import ems.be.Customer;
import ems.be.Event;
import ems.be.TicketType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    @FXML
    private TableView<Event> tvEvents;
    @FXML
    private TableView<TicketType> tvTicketTypes;
    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Event, String> tvColEvent;
    @FXML
    private TableColumn<TicketType, String> tvColTicketType;
    @FXML
    private TableColumn<Customer, String> tvColCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvColEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvColTicketType.setCellValueFactory(new PropertyValueFactory<>("description"));
        tvColCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Event> eventList = FXCollections.observableArrayList();
        ObservableList<TicketType> ticketTypeList = FXCollections.observableArrayList();
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        for (int i = 0; i < 100; i++) {
            Event e = new Event("Event" + i);
            TicketType t = new TicketType("TicketType" + i);
            Customer c = new Customer("Customer" + i);

            eventList.add(e);
            ticketTypeList.add(t);
            customerList.add(c);
        }

        tvEvents.setItems(eventList);
        tvTicketTypes.setItems(ticketTypeList);
        tvCustomers.setItems(customerList);

    }

    @FXML
    public void handleAdd(MouseEvent mouseEvent) {

    }

    @FXML
    public void handleRemove(MouseEvent mouseEvent) {

    }

    @FXML
    public void handleManage(MouseEvent mouseEvent) {

    }

    @FXML
    public void changeToEventPage(MouseEvent mouseEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../view/ec/ec-eventPage.fxml"));
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToTicketPage(MouseEvent mouseEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../view/ec/ec-ticketPage.fxml"));
            Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
