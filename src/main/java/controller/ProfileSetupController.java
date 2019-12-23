package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.User;
import view.ProfilePane;
import view.RootPane;

public class ProfileSetupController {

    private RootPane view;
    private User model;
    private ProfilePane profilePane;

    public ProfileSetupController(RootPane view, User model){
        this.view = view;
        this.model = model;
        this.profilePane = view.getProfilePane();

        this.attachEventHandlers();
        //        this.attachBindings();
    }

    private void attachEventHandlers() {
        profilePane.addCreateAccountHandler(new CreateAccountHandler());
        profilePane.addLoginHandler(new LoginHandler());
    }

    private class CreateAccountHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){

        String output = profilePane.getUsernameInput();
        output += profilePane.getPasswordInput();
        System.out.println(output);
        }
    }

    private class LoginHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){

            if(model.isExistingUser(profilePane.getUsernameInput(), profilePane.getPasswordInput())){
                view.changeTab(1);
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Account Not Recognized");
                errorAlert.setContentText("Try again....");
                errorAlert.showAndWait();
            }
        }
    }
}
