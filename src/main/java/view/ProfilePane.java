package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProfilePane extends GridPane {

    private TextField usernameTextField;
    private TextField passwordTextField;
    private Button createAccountButton;
    private Button loginButton;
    private Label usernameLabel;
    private Label passwordLabel;

    public ProfilePane(){
        //styling
        this.setPadding(new Insets(80, 80, 80, 80));
        this.setVgap(15);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);

        setupProfilePane();

        this.add(usernameLabel, 0 , 0);
        this.add(usernameTextField, 1, 0);

        this.add(passwordLabel, 0, 1);
        this.add(passwordTextField, 1, 1);

        this.add(loginButton, 0, 2);
        this.add(createAccountButton, 1, 2);
    }

    private void setupProfilePane() {
        usernameLabel = new Label("Username: ");
        usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter username here...");

        passwordLabel = new Label("Password: ");
        passwordTextField = new TextField();
        passwordTextField.setPromptText("Enter password here...");

        loginButton = new Button("Login");
        loginButton.setPrefSize(100, 20);

        createAccountButton = new Button("Sign up");
        createAccountButton.setPrefSize(100, 20);
    }

    public String getUsernameInput(){
        return usernameTextField.getText();
    }

    public String getPasswordInput(){
        return passwordTextField.getText();
    }

    public void addCreateAccountHandler(EventHandler<ActionEvent> handler){
        createAccountButton.setOnAction(handler);
    }

    public void addLoginHandler(EventHandler<ActionEvent> handler){
        loginButton.setOnAction(handler);
    }
}
