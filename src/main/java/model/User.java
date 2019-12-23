package model;

import data_access.UserDA;

public class User {

    private String username;
    private String password;
    private String role;
    private UserDA userDA = new UserDA();

    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isExistingUser(String username, String password){
        return 1 == userDA.getUserByUsernameAndPassword(username, password);
    }
}
