package data_access;

import loader.ApplicationLoader;
import model.User;
import utils.DatabaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDA implements IUserDA {

    private Connection connection = DatabaseSingleton.getInstance().getConnection();

    public boolean getUserByUsername(String username) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getUserByUsernameSQL());
        verifyUserPrepareStatement.setString(1, username);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getUserByUsernameSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        return result.first();
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getUserByUsernameAndPasswordSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getUserByUsernameAndPasswordSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if (result.first()) {
            return new User(result.getString("username"), result.getString("password"), result.getString("rolename"));
        }

        return null;
    }

    public boolean createNewUserByUsernameAndPassword(String username, String password) throws SQLException {
        createNewUser(username, password);
        addRoleToNewUser(username, password);

        return false;
    }

    public boolean createNewUserByUsernameAndPasswordAndRole(String username, String password, String role) throws SQLException {
        createNewUser(username, password);
        addRoleToNewUser(username, password, role);

        return false;
    }

    public List<String> getRoleList() throws SQLException {
        List<String> roleList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getAllRoleSQL());

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getAllRoleSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        if (result.first()) {
            do {
                roleList.add(result.getString("roleName"));
            }
            while (result.next());
            return roleList;
        }

        return null;
    }

    public void editUserRoleByUsername(String username, String role) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(editUserRoleByUsernameSQL());
        addRoleToUserPrepareStatement.setString(1, role);
        addRoleToUserPrepareStatement.setString(2, username);
        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, editUserRoleByUsernameSQL());

        addRoleToUserPrepareStatement.execute();
    }

    private void createNewUser(String username, String password) throws SQLException {
        PreparedStatement createNewUserPrepareStatement = connection.prepareStatement(createNewUserByUsernameAndPasswordSQL());
        createNewUserPrepareStatement.setString(1, username);
        createNewUserPrepareStatement.setString(2, password);
        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, createNewUserByUsernameAndPasswordSQL());

        createNewUserPrepareStatement.execute();
    }

    private String editUserRoleByUsernameSQL() {
        return "UPDATE UserRole " +
                "SET RoleID = (SELECT RoleID FROM Role WHERE RoleName = ?) " +
                "WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?)";
    }

    private boolean addRoleToNewUser(String username, String password, String role) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(addRoleToNewUserSQL());
        addRoleToUserPrepareStatement.setString(1, role);
        addRoleToUserPrepareStatement.setString(2, username);
        addRoleToUserPrepareStatement.setString(3, password);
        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, addRoleToNewUserSQL());

        return addRoleToUserPrepareStatement.execute();
    }

    private String addRoleToNewUserSQL() {
        return "INSERT INTO UserRole(RoleID, UserID) " +
                "VALUES ((SELECT RoleID FROM Role WHERE RoleName = ?), " +
                "(SELECT UserID FROM User WHERE UserName = ? AND UserPassword = ?))";
    }

    private String getAllRoleSQL() {
        return "SELECT RoleName as roleName " +
                "FROM Role";
    }

    private boolean addRoleToNewUser(String username, String password) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(addUserRoleToNewUserSQL());
        addRoleToUserPrepareStatement.setString(1, username);
        addRoleToUserPrepareStatement.setString(2, password);
        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, addUserRoleToNewUserSQL());

        return addRoleToUserPrepareStatement.execute();
    }

    private String createNewUserByUsernameAndPasswordSQL() {
        return "INSERT INTO User (username, userpassword) " +
                "VALUES (?, ?)";
    }

    private String addUserRoleToNewUserSQL() {
        return "INSERT INTO UserRole(RoleID, UserID) " +
                "VALUES ((SELECT RoleID FROM Role WHERE RoleName = 'User'), " +
                "(SELECT UserID FROM User WHERE UserName = ? AND UserPassword = ?))";
    }

    private String getUserByUsernameSQL() {
        return "SELECT Username AS username, " +
                "UserPassword AS password, " +
                "r.RoleName AS rolename " +
                "FROM User u " +
                "INNER JOIN UserRole ur ON u.UserID = ur.UserID " +
                "INNER JOIN Role r ON ur.RoleID = r.RoleID " +
                "WHERE UserName = ? ";
    }

    private String getUserByUsernameAndPasswordSQL() {
        return "SELECT Username AS username, " +
                "UserPassword AS password, " +
                "r.RoleName AS rolename " +
                "FROM User u " +
                "INNER JOIN UserRole ur ON u.UserID = ur.UserID " +
                "INNER JOIN Role r ON ur.RoleID = r.RoleID " +
                "WHERE UserName = ? " +
                "AND UserPassword = ?";
    }
}
