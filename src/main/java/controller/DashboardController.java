package controller;

import javafx.fxml.FXML;
import services.NavigationService;

public class DashboardController {

    public DashboardController(){}

    @FXML
    protected void handleBattleSimulatorComponentButtonAction() {
        NavigationService.gotoBattleSimulator();
    }
}
