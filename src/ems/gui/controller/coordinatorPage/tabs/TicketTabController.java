package ems.gui.controller.coordinatorPage.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import ems.bll.util.QRCodeGenerator;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TicketTabController implements Initializable {

    public ListView<Ticket> ltvTickets;
    public TextField txfFilterTickets;

    public TextField txfFilterEvents;
    public ComboBox<Event> cmbEvents;

    public TextField txfFilterTicketTypes;
    public ComboBox<String> cmbTicketTypes;

    public TextField txfFilterCustomers;
    public ComboBox<Customer> cmbCustomers;

    public TextField txfNoTickets;
    public Label lblTicketUUID;
    public CheckBox chbTicketValidation;

    public Button btnCancelTicket;
    public Button btnApplyTicket;

    public AnchorPane apnTicketPreview;
    public Label lblEventName, lblStartDate, lblEndDate, lblLocation, lblTicketType;
    public ImageView imgQRCode;

    private ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        //setting up the page
        clearTicketPreview();
        txfNoTickets.setVisible(false);
        lblTicketUUID.setVisible(false);
        chbTicketValidation.setVisible(false);

        ltvTickets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedTicket) -> {
            if (selectedTicket != null) {
                selectedTicketListener(selectedTicket);
                loadTicketPreview(selectedTicket);
            }
            else
            {
                clearTicketComboBoxFilters();
                clearTicketComboBoxSelection();
                clearTicketPreview();
            }
        });

        ltvTickets.setItems(facade.getAllTickets());
    }

    public void handleFilterTickets(KeyEvent keyEvent) {
        try {
            String query = txfFilterTickets.getText();
            ltvTickets.setItems(facade.getFilteredTickets(query));
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewTicket(ActionEvent event) {
        setDisableApplyButtons(false);

        ltvTickets.getSelectionModel().clearSelection();

        clearTicketComboBoxFilters();
        clearTicketComboBoxSelection();
        setUpFieldsForNewTicket();
    }

    public void handleRemoveTicket(ActionEvent event) {
        try {
            Ticket selectedTicket = ltvTickets.getSelectionModel().getSelectedItem();
            if (selectedTicket == null)
                return;

            facade.deleteTicket(selectedTicket);

            setDisableApplyButtons(true);

            clearTicketComboBoxSelection();
            clearTicketComboBoxFilters();

            ltvTickets.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            if (query.isEmpty()) {
                cmbEvents.hide();
                return;
            }

            cmbEvents.setItems(facade.getFilteredEvents(query));

            cmbEvents.hide();
            cmbEvents.setVisibleRowCount(20);
            cmbEvents.show();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void showTicketTypesForEvent(ActionEvent event) {
        if (cmbEvents.getSelectionModel().getSelectedItem() != null)
            cmbTicketTypes.setItems(
                    FXCollections.observableArrayList(
                            cmbEvents.getSelectionModel().getSelectedItem().getTicketTypes()));
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
        //TODO
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = txfFilterCustomers.getText();
            if (query.isEmpty()) {
                cmbCustomers.hide();
                return;
            }

            cmbCustomers.setItems(facade.getFilteredCustomers(query));

            cmbCustomers.hide();
            cmbCustomers.setVisibleRowCount(20);
            cmbCustomers.show();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleCancelTicket(ActionEvent event) {
        Ticket selectedTicket = ltvTickets.getSelectionModel().getSelectedItem();
        if (selectedTicket == null) {
            clearTicketComboBoxSelection();
            setDisableApplyButtons(true);
        } else {
            fillTicketDetails(selectedTicket);
        }
        clearTicketComboBoxFilters();
    }

    public void handleApplyTicket(ActionEvent event) {
        //check if all necessary fields are filled
        Event selectedEvent = cmbEvents.getSelectionModel().getSelectedItem();
        String selectedTicketType = cmbTicketTypes.getSelectionModel().getSelectedItem();
        Customer selectedCustomer = cmbCustomers.getSelectionModel().getSelectedItem();

        if (selectedEvent == null ||
                selectedTicketType == null ||
                selectedCustomer == null) {
            PopUp.showError("Please provide all necessary fields");
            return;
        }

        Ticket selectedTicket = ltvTickets.getSelectionModel().getSelectedItem();
        if (selectedTicket == null) { //it's a new ticket

            //check if no of tickets is a number
            int noTickets;
            try {
                noTickets = Integer.parseInt(txfNoTickets.getText());
            } catch (Exception e) {
                PopUp.showError("Please provide a valid number for the number of tickets!");
                return;
            }

            //check if no of tickets is a number between 1 and 100
            if (noTickets < 1 || noTickets > 100) {
                PopUp.showError("Please provide a number between 1 and 100!");
                return;
            }

            //create new tickets from fields
            try {
                for (int i = 0; i < noTickets; i++) {
                    facade.createTicket(
                            new Ticket(selectedEvent,
                                    selectedTicketType,
                                    selectedCustomer));
                }
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
                e.printStackTrace();
            }

            clearTicketComboBoxSelection();
            clearTicketComboBoxFilters();
            setDisableApplyButtons(true);
        } else { //TODO: update the ticket
            selectedTicket.setValid(chbTicketValidation.isSelected());
            selectedTicket.setTicketType(selectedTicketType);
            try {
                facade.updateTicket(selectedTicket);
                ltvTickets.getSelectionModel().select(selectedTicket); //select the updated ticket, so the right side doesnt get cleared
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
                e.printStackTrace();
            }
        }
        clearTicketComboBoxFilters();
    }

    private void selectedTicketListener(Ticket selectedTicket) {
        if (selectedTicket == null) {
            return;
        }

        clearTicketComboBoxFilters();
        fillTicketDetails(selectedTicket);
        setDisableApplyButtons(false);
        setTicketCreationLabels(false);
    }

    private void setDisableApplyButtons(boolean value) {
        btnApplyTicket.setDisable(value);
        btnCancelTicket.setDisable(value);
    }

    private void setTicketCreationLabels(boolean value) {
        txfNoTickets.setVisible(value);
        lblTicketUUID.setVisible(!value);
        chbTicketValidation.setVisible(!value);
    }

    private void clearTicketComboBoxFilters() {
        txfFilterEvents.clear();
        txfFilterTicketTypes.clear();
        txfFilterCustomers.clear();
    }

    private void fillTicketDetails(Ticket ticket) {
        //fill up ComboBoxes
        cmbEvents.setItems(FXCollections.observableArrayList(ticket.getEvent()));
        cmbTicketTypes.setItems(FXCollections.observableArrayList(ticket.getEvent().getTicketTypes()));
        cmbCustomers.setItems(FXCollections.observableArrayList(ticket.getCustomer()));

        cmbEvents.getSelectionModel().select(0);
        cmbTicketTypes.getSelectionModel().select(ticket.getEvent().getTicketTypes().indexOf(ticket.getTicketType()));
        cmbCustomers.getSelectionModel().select(0);

        lblTicketUUID.setText(ticket.getUuid().toString());
        chbTicketValidation.setSelected(ticket.isValid());
    }

    private void clearTicketComboBoxSelection() {
        cmbEvents.getSelectionModel().clearSelection();
        cmbTicketTypes.getSelectionModel().clearSelection();
        cmbCustomers.getSelectionModel().clearSelection();
    }

    private void setUpFieldsForNewTicket() {
        cmbEvents.setItems(facade.getAllEvents());
        cmbTicketTypes.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        cmbCustomers.setItems(facade.getAllCustomers());

        setTicketCreationLabels(true);

        //default value = 1
        txfNoTickets.setText("1");
    }

    private void loadTicketPreview(Ticket ticket) {
        lblEventName.setText(ticket.getEvent().getName());
        lblTicketType.setText(ticket.getTicketType());
        lblStartDate.setText(ticket.getEvent().getStart().toLocalDate().toString() + " " + ticket.getEvent().getStart().toLocalTime().toString());
        lblEndDate.setText(ticket.getEvent().getEnd().toLocalDate().toString() + " " + ticket.getEvent().getEnd().toLocalTime().toString());
        lblLocation.setText(ticket.getEvent().getLocation());

        try {
            Image qrCode = SwingFXUtils.toFXImage(QRCodeGenerator.generateQRCodeImage(ticket.getUuid().toString()), null);
            imgQRCode.setImage(qrCode);
        } catch (Exception e) {
            PopUp.showError("Unable to generate barcode for image!");
            e.printStackTrace();
        }
    }

    public void handleSaveTickets(ActionEvent event) {
        if (ltvTickets.getItems().isEmpty()) {
            return;
        }

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Save tickets to...");
        chooser.setInitialDirectory(new File("C:\\"));
        File directory = chooser.showDialog(null);
        if (directory == null)
            return;

        for (Ticket ticket : ltvTickets.getItems()) {
            loadTicketPreview(ticket);
            WritableImage ticketSnapshot = apnTicketPreview.snapshot(null, null);
            BufferedImage bImage = SwingFXUtils.fromFXImage(ticketSnapshot, null);
            try {
                ImageIO.write(bImage, "png", new File(directory + "/" + ticket.getUuid().toString() + ".png"));
            } catch (Exception e) {
                PopUp.showError("Could not save ticket!");
            }
        }
        clearTicketComboBoxFilters();
        clearTicketComboBoxSelection();
        clearTicketPreview();
    }

    public void handleSaveTicket(ActionEvent event) {
        if (ltvTickets.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save ticket to...");
        chooser.setInitialDirectory(new File("C:\\"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        chooser.setInitialFileName(ltvTickets.getSelectionModel().getSelectedItem().getUuid().toString());

        File file = chooser.showSaveDialog(null);
        if (file == null)
            return;

        WritableImage ticketSnapshot = apnTicketPreview.snapshot(null, null);
        BufferedImage bImage = SwingFXUtils.fromFXImage(ticketSnapshot, null);
        try {
            ImageIO.write(bImage, "png", file);
        } catch (Exception e) {
            PopUp.showError("Could not save ticket!");
        }
    }

    private void clearTicketPreview()
    {
        lblEventName.setText("");
        lblTicketType.setText("");
        lblStartDate.setText("");
        lblEndDate.setText("");
        lblLocation.setText("");
        imgQRCode.setImage(null);
    }
}
