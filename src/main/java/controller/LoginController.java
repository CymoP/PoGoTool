package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import loader.ApplicationLoader;
import services.UserService;


public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private UserService userService = new UserService();

    public LoginController(){

    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        if(userService.isExistingUser(usernameField.getText(), passwordField.getText())){
            ApplicationLoader.getInstance().gotoDashboard();
        }
    }

    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        ApplicationLoader.getInstance().gotoSignup();
    }
}
