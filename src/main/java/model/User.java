package model;

public class User {

    private String username;
    private String password;
    private String role;
    private boolean loggedInUser;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role, boolean loggedInUser) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.loggedInUser = loggedInUser;
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

    public boolean isLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(boolean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
