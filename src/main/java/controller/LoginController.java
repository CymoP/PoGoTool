package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.NavigationService;
import services.UserService;

import java.sql.SQLException;

/**
 * LoginController is the class for controlling user interactions on the login window to the model
 */
public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private UserService userService = new UserService();

    public LoginController() {}

    @FXML
    protected void handleLoginButtonAction() throws SQLException {
        if (userService.isExistingUser(usernameField.getText(), passwordField.getText())) {
            NavigationService.gotoDashboard();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText("Try again...");
            alert.setContentText("Review input data then try again");

            alert.showAndWait();
        }
    }

    @FXML
    protected void handleSignUpButtonAction() {NavigationService.gotoSignup();
    }
}
