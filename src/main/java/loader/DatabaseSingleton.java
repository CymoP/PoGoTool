package loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSingleton {

    private static DatabaseSingleton instance = null;
    private Connection connection;


    private DatabaseSingleton(){
        makeJDBCConnection();
    }

    public static DatabaseSingleton getInstance(){
        if (instance == null){
            instance = new DatabaseSingleton();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void makeJDBCConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemondb", "dbuser", "B3@fcake9192");
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, "Connection made to " + "jdbc:mysql://localhost:3306/pokemondb" + " " + new Timestamp(System.currentTimeMillis()));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
