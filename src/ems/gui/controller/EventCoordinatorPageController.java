package ems.gui.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                /* Disable cancel/apply buttons */
        /*btnApplyEvent.setDisable(true);
        btnCancelEvent.setDisable(true);
        btnApplyCustomer.setDisable(true);
        btnCancelCustomer.setDisable(true);
        btnApplyTicket.setDisable(true);
        btnCancelTicket.setDisable(true);*/

        //hide ticket creation/edit specific fields
        /*lblTicketUUID.setVisible(false);
        txfNoTickets.setVisible(false);
        chbTicketValidation.setVisible(false);*/
    }

    /*private void refreshTickets()
    {
        try{
            ticketModel.clearFilter();
            tbvOverviewTickets.getItems().clear();
            tbvOverviewTickets.getItems().addAll(ticketModel.getObservableTickets());
            tbvTicketTabTickets.getItems().clear();
            tbvTicketTabTickets.getItems().addAll(ticketModel.getObservableTickets());
        } catch (Exception ex){
            PopUp.showError(ex.getMessage());
        }
    }*/
}
