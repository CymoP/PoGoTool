package loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSingleton {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "pokemondb";
    private static final String DATABASE_USER = "dbuser";
    private static final String DATABASE_PASSWORD = "B3@fcake9192";
    private static DatabaseSingleton instance = null;
    private Connection connection;

    private DatabaseSingleton() {
        makeJDBCConnection();
    }

    /**
     * Singleton implementation - If instance exists use that, if not create one
     *
     * @return Database connection instance
     */
    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }

        return instance;
    }

    /**
     * Returns the database connection
     *
     * @return Database connection
     */
    public Connection getConnection() {
        return connection;
    }

    private void makeJDBCConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, DATABASE_USER, DATABASE_PASSWORD);
            Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, "Connection made to " + DATABASE_URL + DATABASE_NAME + " " + new Timestamp(System.currentTimeMillis()));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
