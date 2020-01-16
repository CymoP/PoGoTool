package services;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import loader.ApplicationLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationService {

    private static final String LOGIN_FXML_FILE_LOCATION = "../view/login.fxml";
    private static final String SIGNUP_FXML_FILE_LOCATION = "../view/signup.fxml";
    private static final String DASHBOARD_FXML_FILE_LOCATION = "../view/dashboard.fxml";

    private NavigationService() {
    }

    public static void gotoDashboard() {
        try {
            replaceSceneContent(DASHBOARD_FXML_FILE_LOCATION, 800, 600);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gotoSignup() {
        try {
            replaceSceneContent(SIGNUP_FXML_FILE_LOCATION, 400, 400);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gotoLogin() {
        try {
            replaceSceneContent(LOGIN_FXML_FILE_LOCATION, 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Parent replaceSceneContent(String fxml, int width, int height) throws Exception {
        Parent page = (Parent) FXMLLoader.load(ApplicationLoader.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = new Scene(page, width, height);

        ApplicationLoader.getStage().setScene(scene);
        ApplicationLoader.getStage().sizeToScene();

        return page;
    }
}
