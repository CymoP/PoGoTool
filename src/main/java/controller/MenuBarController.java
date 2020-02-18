package controller;

import javafx.fxml.FXML;
import services.NavigationService;

public class MenuBarController {

    @FXML
    public void handleReturnToDashboard(){
        NavigationService.gotoDashboard();
    }
}
