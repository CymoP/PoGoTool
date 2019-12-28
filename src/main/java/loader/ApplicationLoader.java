package loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to run the application
 */
public class ApplicationLoader extends Application {

    private static Connection connection;
    private Stage stage;
    private static ApplicationLoader instance;

    public ApplicationLoader() {
        instance = this;
    }

    public static ApplicationLoader getInstance() {
        return instance;
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            makeJDBCConnection();
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoDashboard() {
        try {
            replaceSceneContent("../view/dashboard.fxml", 800, 600);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSignup() {
        try {
            replaceSceneContent("../view/signup.fxml", 400, 400);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("../view/login.fxml", 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Parent replaceSceneContent(String fxml, int width, int height) throws Exception {
        Parent page = (Parent) FXMLLoader.load(ApplicationLoader.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = new Scene(page, width, height);

        stage.setScene(scene);
        stage.sizeToScene();

        return page;
    }

    private static void makeJDBCConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemondb", "dbuser", "B3@fcake9192");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
