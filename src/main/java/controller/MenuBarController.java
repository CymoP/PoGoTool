package controller;

import javafx.fxml.FXML;
import services.NavigationService;
import services.UserService;

public class MenuBarController {

    private UserService userService = UserService.getInstance();

    @FXML
    public void handleReturnToDashboard(){
        NavigationService.gotoDashboard();
    }

    @FXML
    public void handleLogOut() {
        userService.logout();
        NavigationService.gotoLogin();
    }

    @FXML
    public void handleSetup() {
        NavigationService.gotoProfileSetup();
    }
}
