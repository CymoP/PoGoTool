package controller;

import data_access.UserDA;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import services.UserService;

import java.net.URL;
import java.sql.SQLException;
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
    private UserDA userDA = new UserDA();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkAdminElseHideAdminComponents();
        checkCurrentUserProfileConfigurationOptionsSetDefaultSelected();
    }

    @FXML
    public void handleSubmitButton() throws SQLException {
        userDA.updateUserProfileConfigurationOptions(userService.getLoggedInUser().getUsername(), battleSimulatorCheckBox.isSelected(), tierListCheckBox.isSelected());
        userService.logout();
    }

    private void checkCurrentUserProfileConfigurationOptionsSetDefaultSelected() {
        battleSimulatorCheckBox.setSelected(userService.getLoggedInUser().getComponentList().get("battlesimulator"));
        tierListCheckBox.setSelected(userService.getLoggedInUser().getComponentList().get("tierlist"));
        userMaintenanceCheckBox.setSelected(userService.getLoggedInUser().getComponentList().get("usermaintenance"));
        dataMaintenanceCheckBox.setSelected(userService.getLoggedInUser().getComponentList().get("datamaintenance"));
    }

    private void checkAdminElseHideAdminComponents() {
        if (!userService.checkLoggedInUserIsAdmin()) {
            userMaintenanceCheckBox.setManaged(false);
            dataMaintenanceCheckBox.setManaged(false);
            userMaintenanceCheckBox.setVisible(false);
            dataMaintenanceCheckBox.setVisible(false);
        }
        else if (userService.checkLoggedInUserIsAdmin()) {
            battleSimulatorCheckBox.setDisable(true);
            tierListCheckBox.setDisable(true);
            userMaintenanceCheckBox.setDisable(true);
            dataMaintenanceCheckBox.setDisable(true);
        }
    }
}
