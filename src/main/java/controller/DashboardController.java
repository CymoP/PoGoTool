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
    protected void handleBattleSimulatorComponentButtonAction() {
        NavigationService.gotoBattleSimulator();
    }
}
