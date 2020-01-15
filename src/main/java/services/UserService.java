package services;

import data_access.UserDA;
import model.User;

import java.sql.SQLException;

public class UserService {

    private UserDA userDA = new UserDA();
    private User loggedInUser;

    public UserService() {
    }

    public boolean isExistingUser(String username, String password) throws SQLException {
        loggedInUser = userDA.getUserByUsernameAndPassword(username, password);

        if (loggedInUser != null) {
            loggedInUser.setLoggedInUser(true);
            return true;
        }

        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
