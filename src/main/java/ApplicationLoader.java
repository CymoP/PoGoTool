import controller.ProfileSetupController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import view.RootPane;

/**
 * Class used to run the application
 */
public class ApplicationLoader extends Application {

    private RootPane view;
    private User userModel;
    private ProfileSetupController controller;

    @Override
    public void init(){
        userModel = new User();
        view = new RootPane();
        controller = new ProfileSetupController(view, userModel);
    }

    public void start(Stage stage) throws Exception {
        stage.setMinHeight(500);
        stage.setMinWidth(500);

        stage.setTitle("Pogo Tool");
        stage.setScene(new Scene(view));
        stage.show();
    }
}
