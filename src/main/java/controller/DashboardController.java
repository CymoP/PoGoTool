package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import services.NavigationService;
import services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * DashboardController is the class for controlling user interactions on the dashboard window to the model
 */
public class DashboardController implements Initializable {

    @FXML
    public Button userMaintenanceButton;

    @FXML
    public Button dataMaintenanceButton;

    private UserService userService = UserService.getInstance();

    public DashboardController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!userService.checkLoggedInUserIsAdmin()) {
            userMaintenanceButton.setManaged(false);
            dataMaintenanceButton.setManaged(false);
            userMaintenanceButton.setVisible(false);
            dataMaintenanceButton.setVisible(false);
        }
    }

    /**
     * Handler for the load battle simulator component button
     */
    @FXML
    public void handleBattleSimulatorComponentButtonAction() {
        NavigationService.gotoBattleSimulator();
    }

    /**
     * Handler for the load user maintenance component button
     */
    @FXML
    public void handleUserMaintenanceComponentButtonAction() {
        NavigationService.gotoUserMaintenance();
    }

    /**
     * Handler for the load data maintenance component button
     */
    @FXML
    public void handleDataMaintenanceComponentButtonAction() {
        NavigationService.gotoDataMaintenance();
    }

}
