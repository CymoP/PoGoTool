package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    private Button loginButton;

    @FXML
    public Button signUpButton;

    private UserService userService = UserService.getInstance();

    public LoginController() {
    }

    @FXML
    protected void handleLoginButtonAction() throws SQLException {
        if (userService.login(usernameField.getText(), passwordField.getText())) {
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
    protected void handleLoginButtonKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButton.fire();
            event.consume();
        }
    }

    @FXML
    protected void handleSignUpButtonAction() {
        NavigationService.gotoSignUp();
    }

    @FXML
    protected void handleSignUpButtonKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            signUpButton.fire();
            event.consume();
        }
    }
}
