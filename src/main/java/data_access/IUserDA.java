package data_access;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDA {

    /**
     * Returns a user object by their username and password
     *
     * @param username given username
     * @param password given password
     * @return user object for the given username and password
     * @throws SQLException
     */
    User getUserByUsernameAndPassword(String username, String password) throws SQLException;

    /**
     * Creates a new user with a given username and password
     *
     * @param username given username
     * @param password given password
     * @return true|false based on successful user creation
     * @throws SQLException
     */
    boolean createNewUserByUsernameAndPassword(String username, String password) throws SQLException;


    /**
     * Creates a new user with a given username, password and role
     *
     * @param username given username
     * @param password given password
     * @param role given role
     * @return true|false based on successful user creation
     * @throws SQLException
     */
    boolean createNewUserByUsernameAndPasswordAndRole(String username, String password, String role) throws SQLException;

    /**
     * Returns all the roles found in the database
     *
     * @return the role list
     * @throws SQLException
     */
    List<String> getRoleList() throws SQLException;

    /**
     * Edits the role for the given user
     *
     * @param username given username for the user to be updated
     * @param role given role
     * @throws SQLException
     */
    void updateUserRoleByUsername(String username, String role) throws SQLException;

    /**
     * Updates the user profile configuration options for the logged in user
     *
     * @param username given username for the logged in user
     * @param battleSimulatorComponent true|false as to whether to display this component
     * @param tierListComponent true|false as to whether display this component
     * @throws SQLException
     */
    void updateUserProfileConfigurationOptions(String username, boolean battleSimulatorComponent, boolean tierListComponent) throws SQLException;
}
