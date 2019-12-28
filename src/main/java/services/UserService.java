package services;

import data_access.UserDA;

public class UserService {

    private UserDA userDA = new UserDA();

    public UserService(){

    }

    public boolean isExistingUser(String username, String password){
        return 1 == userDA.getUserByUsernameAndPassword(username, password);
    }
}
