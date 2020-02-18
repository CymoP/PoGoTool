package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.NavigationService;
import services.UserService;

import java.sql.SQLException;

/**
 * SignUp is the class for controlling user interactions on the signup window to the model
 */
public class SignUpController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rePasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button submitButton;

    private UserService userService = new UserService();

    @FXML
    protected void handleSignUpSubmitButtonAction() throws SQLException {
        if (!userService.isExistingUser(usernameField.getText())) {
            if (passwordField.getText().equals(rePasswordField.getText())) {
                if (userService.createNewUser(usernameField.getText(), passwordField.getText())) {
                    NavigationService.gotoLogin();
                }
            }
        }
    }

    @FXML
    protected boolean verifyUserNameInputListener() throws SQLException {
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {

            try {
                if (userService.isExistingUser(usernameField.getText())) {
                    //Username already exists
                    usernameField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                } else {
                    usernameField.setStyle(" -fx-border-color: black;");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return userService.isExistingUser(usernameField.getText());
    }

    @FXML
    public void passwordFieldOneListener() {
        verifyPasswordsMatch(passwordField, rePasswordField);
    }

    @FXML
    public void passwordFieldTwoListener() {
        verifyPasswordsMatch(rePasswordField, passwordField);
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
