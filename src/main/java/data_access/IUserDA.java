package data_access;

import model.User;

import java.sql.SQLException;

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
     * @return true/false based on successful user creation
     * @throws SQLException
     */
    boolean createNewUserByUsernameAndPassword(String username, String password) throws SQLException;
}
