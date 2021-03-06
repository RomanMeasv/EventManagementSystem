package ems.gui.controller.loginPage;

import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.bll.exceptions.DatabaseException;
import ems.gui.model.UserModel;
import ems.gui.view.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginPageController {

    public TextField txfUsername;
    public PasswordField psfPassword;

    UserModel userModel;

    public LoginPageController() {
        userModel = new UserModel();
    }

    public void handleLogin(ActionEvent event) {
        String username = txfUsername.getText();
        String password = psfPassword.getText();

        try {
            User loggedUser = userModel.tryLogin(username, password);

            if (loggedUser == null) {
                PopUp.showError("Wrong login credentials!");
                return;
            }

            try {
                boolean isAdmin = loggedUser.getClass().equals(Admin.class);
                boolean isEC = loggedUser.getClass().equals(EventCoordinator.class);

                Parent root = isAdmin ? FXMLLoader.load(getClass().getResource("/ems/gui/view/adminView.fxml")) :
                        isEC ? FXMLLoader.load(getClass().getResource("/ems/gui/view/eventCoordinatorView.fxml")) : null;

                if (root == null) {
                    return;
                }

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                PopUp.showError("Couldn't load page!");
                e.printStackTrace();
            }

        } catch (DatabaseException e) {
            PopUp.showError(e.getMessage());
        }
    }
}
