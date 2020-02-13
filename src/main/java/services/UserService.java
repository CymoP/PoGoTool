package services;

import data_access.UserDA;
import model.User;

import java.sql.SQLException;

/**
 * UserService is a service class for all User related business logic
 */
public class UserService {

    private UserDA userDA = new UserDA();
    private User loggedInUser;

    public UserService() {
    }

    /**
     * Checks to see whether the given username currently exists
     *
     * @param username the given Username
     * @return true|false depending on whether or not the user exists
     * @throws SQLException
     */
    public boolean isExistingUser(String username) throws SQLException {
        return userDA.getUserByUsername(username);
    }

    /**
     * Checks to whether the given user exists if so return user object
     *
     * @param username the given Username
     * @param password the given Password
     * @return the given user in object format
     * @throws SQLException
     */
    public boolean isExistingUser(String username, String password) throws SQLException {
        loggedInUser = userDA.getUserByUsernameAndPassword(username, password);

        if (loggedInUser != null) {
            loggedInUser.setLoggedInUser(true);
            return true;
        }

        return false;
    }

    /**
     * Returns the user object for the logged on user
     *
     * @return the logged on user
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Creates a new user with the given details
     */
    public boolean createNewUser(String username, String password) throws SQLException {
        return userDA.createNewUserByUsernameAndPassword(username, password);
    }
}
