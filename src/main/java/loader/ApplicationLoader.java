package loader;

import javafx.application.Application;
import javafx.stage.Stage;
import services.NavigationService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to run the application
 */
public class ApplicationLoader extends Application {

    private static Stage stage;
    private static ApplicationLoader instance;

    public ApplicationLoader() {
        instance = this;
    }

    public static ApplicationLoader getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setResizable(false);
            NavigationService.gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
