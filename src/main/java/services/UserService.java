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
    private static UserService instance = null;

    private UserService() {
    }

    /**
     * Singleton implementation - If instance exists use that, if not create one
     *
     * @return User service instance
     */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
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
     * @throws SQLException SQLException thrown
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

    /**
     * Creates a new user with the given details
     */
    public boolean createNewUser(String username, String password, String role) throws SQLException {
        return userDA.createNewUserByUsernameAndPasswordAndRole(username, password, role);
    }


    /**
     * Edits the role of a given user
     *
     * @param username the given username
     * @param role     the given role
     */
    public void editUserRole(String username, String role) throws SQLException {
        userDA.editUserRoleByUsername(username, role);
        loggedInUser = userDA.getUserByUsernameAndPassword(loggedInUser.getUsername(), loggedInUser.getPassword());
    }

    /**
     * Checks that the logged in user is an admin
     *
     * @return true|false whether logged in user is an admin
     */
    public boolean checkLoggedInUserIsAdmin() {
        return loggedInUser.getRole().equals("Admin");
    }

    /**
     * Sets loggedInUser to null to destroy previously logged in users information
     */
    public void logout() {
        loggedInUser = null;
    }
}
