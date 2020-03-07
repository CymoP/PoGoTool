package services;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loader.ApplicationLoader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NavigationService is a service class for handling all navigational logic within the application
 */
public class NavigationService {

    private static final String LOGIN_FXML_FILE_LOCATION = "../view/login.fxml";
    private static final String SIGN_UP_FXML_FILE_LOCATION = "../view/signup.fxml";
    private static final String DASHBOARD_FXML_FILE_LOCATION = "../view/dashboard.fxml";
    private static final String BATTLE_SIMULATOR_FXML_FILE_LOCATION = "../view/battlesimulator.fxml";
    private static final String BATTLE_SIMULATOR_REPORT_FXML_FILE_LOCATION = "../view/battlesimulatorreport.fxml";
    private static final String USER_MAINTENANCE_FXML_FILE_LOCATION = "../view/usermaintenance.fxml";
    private static final String DATA_MAINTENANCE_FXML_FILE_LOCATION = "../view/datamaintenance.fxml";
    private static Stage reportStage;

    private NavigationService() {
    }

    /**
     * Navigate the user to the dashboard scene
     */
    public static void gotoDashboard() {
        try {
            ifExistsCloseReportStage();
            replaceSceneContent(DASHBOARD_FXML_FILE_LOCATION, 1200, 800);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigate the user to the signup scene
     */
    public static void gotoSignUp() {
        try {
            replaceSceneContent(SIGN_UP_FXML_FILE_LOCATION, 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigate the user to the login scene
     */
    public static void gotoLogin() {
        try {
            ifExistsCloseReportStage();
            replaceSceneContent(LOGIN_FXML_FILE_LOCATION, 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigate the user to the data maintenance scene
     */
    public static void gotoDataMaintenance() {
        try {
            replaceSceneContent(DATA_MAINTENANCE_FXML_FILE_LOCATION, 1200, 800);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigate the user to the user maintenance scene
     */
    public static void gotoUserMaintenance() {
        try {
            replaceSceneContent(USER_MAINTENANCE_FXML_FILE_LOCATION, 1200, 800);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navigate the user to the battle simulator scene
     */
    public static void gotoBattleSimulator() {
        try {
            replaceSceneContent(BATTLE_SIMULATOR_FXML_FILE_LOCATION, 1200, 800);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Opens the report window when using the battle simulator
     *
     * @throws IOException IOException thrown
     */
    public static void gotoBattleReport() throws IOException {
        Parent root = FXMLLoader.load(ApplicationLoader.class.getResource(BATTLE_SIMULATOR_REPORT_FXML_FILE_LOCATION));
        Scene scene = new Scene(root);

        if (reportStage == null) {
            reportStage = new Stage();
            reportStage.setResizable(false);
        }

        reportStage.setScene(scene);
        reportStage.show();
    }

    private static void ifExistsCloseReportStage() {
        if(reportStage != null){
            reportStage.close();
        }
    }

    private static void replaceSceneContent(String fxml, int width, int height) throws Exception {
        Parent page = FXMLLoader.load(ApplicationLoader.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = new Scene(page, width, height);

        ApplicationLoader.getStage().setScene(scene);
        ApplicationLoader.getStage().sizeToScene();
    }
}
