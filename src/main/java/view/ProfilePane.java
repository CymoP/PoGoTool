package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProfilePane extends GridPane {

    public ProfilePane(){
        //styling
        this.setPadding(new Insets(80, 80, 80, 80));
        this.setVgap(15);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);

        //Username
        Label labelUserName = new Label("Username: ");
        TextField textFieldUserName = new TextField();
        textFieldUserName.setPromptText("Enter username here...");

        //Password
        Label labelPassword = new Label("Password: ");
        TextField textFieldPassword = new TextField();
        textFieldPassword.setPromptText("Enter password here...");

        this.add(labelUserName, 0 , 0);
        this.add(textFieldUserName, 1, 0);

        this.add(labelPassword, 0, 1);
        this.add(textFieldPassword, 1, 1);
    }

}
