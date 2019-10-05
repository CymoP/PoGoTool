import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RootPane;

/**
 * Class used to run the application
 */
public class ApplicationLoader extends Application {

    private RootPane view = new RootPane();

    @Override
    public void init(){

    }

    public void start(Stage stage) throws Exception {
        stage.setMinHeight(500);
        stage.setMinWidth(500);

        stage.setTitle("Pogo Tool");
        stage.setScene(new Scene(view));
        stage.show();
    }
}
