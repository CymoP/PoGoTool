package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import loader.ApplicationLoader;
import services.UserService;

import java.sql.SQLException;


public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private UserService userService = new UserService();

    public LoginController(){

    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws SQLException {
        if(userService.isExistingUser(usernameField.getText(), passwordField.getText())){
            ApplicationLoader.getInstance().gotoDashboard();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText("Try again...");
            alert.setContentText("Review input data then try again");

            alert.showAndWait();
        }
    }

    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        ApplicationLoader.getInstance().gotoSignup();
    }
}
