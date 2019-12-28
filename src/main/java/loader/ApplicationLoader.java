package loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to run the application
 */
public class ApplicationLoader extends Application {

    private Stage stage;
    private static ApplicationLoader instance;

    public ApplicationLoader(){
        instance = this;
    }

    public static ApplicationLoader getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoDashboard() {
        try {
            replaceSceneContent("../dashboard.fxml");
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSignup(){
        try {
            replaceSceneContent("../signup.fxml");
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("../login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(ApplicationLoader.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();

        if (scene == null) {
            scene = new Scene(page, 700, 450);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}
