package services;

import data_access.UserDA;

import java.sql.SQLException;

public class UserService {

    private UserDA userDA = new UserDA();

    public UserService(){

    }

    public boolean isExistingUser(String username, String password) throws SQLException {
        return 1 == userDA.getUserByUsernameAndPassword(username, password);
    }
}
