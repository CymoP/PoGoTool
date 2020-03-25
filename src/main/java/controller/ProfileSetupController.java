package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSetupController implements Initializable {

    @FXML
    public Button submitButton;

    @FXML
    public CheckBox battleSimulatorCheckBox;

    @FXML
    public CheckBox userMaintenanceCheckBox;

    @FXML
    public CheckBox dataMaintenanceCheckBox;

    @FXML
    public CheckBox tierListCheckBox;

    private UserService userService = UserService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkAdminElseHideAdminComponents();
    }

    private void checkAdminElseHideAdminComponents() {
        if (!userService.checkLoggedInUserIsAdmin()) {
            userMaintenanceCheckBox.setManaged(false);
            dataMaintenanceCheckBox.setManaged(false);
            userMaintenanceCheckBox.setVisible(false);
            dataMaintenanceCheckBox.setVisible(false);
        }
    }

    public void handleSubmitButton() {
    }
}
