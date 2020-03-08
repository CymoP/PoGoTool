package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.RoleService;
import services.UserService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * UserMaintenanceController is the controller class for controlling user interactions on the User Maintenance window
 */
public class UserMaintenanceController implements Initializable {

    @FXML
    public TextField createUserUserNameField;

    @FXML
    public PasswordField createUserPasswordField;

    @FXML
    public ComboBox createUserRoleComboBox;

    @FXML
    public Button createUserButton;

    @FXML
    public TextField editUserUserNameField;

    @FXML
    public ComboBox editUserRoleComboBox;

    @FXML
    public Button editUserButton;

    @FXML
    public PasswordField createUserRePasswordField;

    private RoleService roleService = RoleService.getInstance();
    private UserService userService = UserService.getInstance();

    public UserMaintenanceController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupRoleData();
        autoSelectFirstRole();
    }

    @FXML
    public void handleCreateUserButton() throws SQLException {
        if (!userService.isExistingUser(createUserUserNameField.getText())) {
            if (createUserPasswordField.getText().equals(createUserRePasswordField.getText())) {
                userService.createNewUser(createUserUserNameField.getText(), createUserPasswordField.getText(), createUserRoleComboBox.getSelectionModel().getSelectedItem().toString());
            }
        }
    }

    @FXML
    public void handleEditUserRoleButton() throws SQLException {
        if (userService.isExistingUser(editUserUserNameField.getText())) {
            userService.editUserRole(editUserUserNameField.getText(), editUserRoleComboBox.getSelectionModel().getSelectedItem().toString());
        }
    }

    private void setupRoleData() {
        ObservableList roleList = FXCollections.observableList(roleService.getRoleList());

        createUserRoleComboBox.getItems().clear();
        editUserRoleComboBox.getItems().clear();

        createUserRoleComboBox.getItems().addAll(roleList);
        editUserRoleComboBox.getItems().addAll(roleList);
    }

    private void autoSelectFirstRole() {
        createUserRoleComboBox.getSelectionModel().selectFirst();
        editUserRoleComboBox.getSelectionModel().selectFirst();
    }
}
