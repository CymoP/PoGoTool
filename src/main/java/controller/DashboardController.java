package controller;

import javafx.fxml.FXML;
import services.NavigationService;

public class DashboardController {

    /**
     * DashboardController is the class for controlling user interactions on the dashboard window to the model
     */
    public DashboardController() {
    }

    @FXML
    public void handleBattleSimulatorComponentButtonAction() {
        NavigationService.gotoBattleSimulator();
    }

    @FXML
    public void handleUserMaintenanceComponentButtonAction() {
        NavigationService.gotoUserMaintenance();
    }

    @FXML
    public void handleDataMaintenanceComponentButtonAction() {
        NavigationService.gotoDataMaintenance();
    }
}
