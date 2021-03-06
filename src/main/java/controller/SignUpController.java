package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import services.NavigationService;
import services.UserService;

import java.sql.SQLException;

/**
 * SignUpController is the class for controlling user interactions on the signup window to the model
 */
public class SignUpController {

    @FXML
    public Button backButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rePasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button submitButton;

    private UserService userService = UserService.getInstance();

    @FXML
    protected void handleSignUpSubmitButtonAction() throws SQLException {
        if (!userService.checkUserExists(usernameField.getText())) {
            if (passwordField.getText().equals(rePasswordField.getText())) {
                userService.createNewUser(usernameField.getText(), passwordField.getText());
                if (userService.login(usernameField.getText(), passwordField.getText())) {
                    NavigationService.gotoProfileSetup();
                }
            }
        }
    }

    @FXML
    public void handleSignUpBackButtonAction() {
        NavigationService.gotoLogin();
    }

    @FXML
    protected boolean verifyUserNameInputListener() throws SQLException {
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {

            try {
                if (userService.checkUserExists(usernameField.getText())) {
                    //Username already exists
                    usernameField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                } else {
                    usernameField.setStyle(" -fx-border-color: black;");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return userService.checkUserExists(usernameField.getText());
    }

    @FXML
    public void passwordFieldOneListener() {
        verifyPasswordsMatch(passwordField, rePasswordField);
    }

    @FXML
    public void passwordFieldTwoListener() {
        verifyPasswordsMatch(rePasswordField, passwordField);
    }

    @FXML
    protected void handleSubmitButtonKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submitButton.fire();
            event.consume();
        }
    }

    private void verifyPasswordsMatch(PasswordField givenPasswordFieldOne, PasswordField givenPasswordFieldTwo) {
        givenPasswordFieldOne.textProperty().addListener((observable, oldValue, newValue) -> {

            if (checkInvalidPasswordInput(givenPasswordFieldOne, givenPasswordFieldTwo)) {
                givenPasswordFieldOne.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                givenPasswordFieldTwo.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
            } else {
                givenPasswordFieldOne.setStyle(" -fx-border-color: black;");
                givenPasswordFieldTwo.setStyle(" -fx-border-color: black;");
            }
        });
    }

    private boolean checkInvalidPasswordInput(PasswordField givenPasswordFieldOne, PasswordField givenPasswordFieldTwo) {
        return !givenPasswordFieldOne.getText().equals(givenPasswordFieldTwo.getText()) || givenPasswordFieldOne.getText().equals("") || givenPasswordFieldTwo.getText().equals("");
    }
}
