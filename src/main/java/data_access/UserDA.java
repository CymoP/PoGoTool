package data_access;

import model.User;
import utils.DatabaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDA implements IUserDA {

    private Connection connection = DatabaseSingleton.getInstance().getConnection();

    public boolean getUserByUsername(String username) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getUserByUsernameSQL());
        verifyUserPrepareStatement.setString(1, username);

        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, getUserByUsernameSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        return result.first();
    }

    public void updateUserProfileConfigurationOptions(String username, boolean battleSimulatorComponent, boolean tierListComponent) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(updateUserProfileConfigurationOptionsSQL());
        verifyUserPrepareStatement.setBoolean(1, battleSimulatorComponent);
        verifyUserPrepareStatement.setBoolean(2, tierListComponent);
        verifyUserPrepareStatement.setString(3, username);

        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, updateUserProfileConfigurationOptionsSQL());
        verifyUserPrepareStatement.execute();
    }

    private String updateUserProfileConfigurationOptionsSQL() {
        return "UPDATE ConfigurationOptions\n" +
                "SET BattleSimulator = ?, TierList = ?\n" +
                "WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?)";
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getUserByUsernameAndPasswordSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, getUserByUsernameAndPasswordSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if (result.first()) {
            return new User(result.getString("username"),
                    result.getString("password"),
                    result.getString("rolename"),
                    userComponentMap(result));
        }

        return null;
    }

    public void createNewUserByUsernameAndPassword(String username, String password) throws SQLException {
        createNewUser(username, password);
        addRoleToNewUser(username, password);
        addDefaultConfigurationOptionsToNewUser(username);
    }

    public void createNewUserByUsernameAndPasswordAndRole(String username, String password, String role) throws SQLException {
        createNewUser(username, password);
        addRoleToNewUser(username, password, role);
        addDefaultConfigurationOptionsToNewUser(username);
    }

    public List<String> getRoleList() throws SQLException {
        List<String> roleList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getAllRoleSQL());

        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, getAllRoleSQL());
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

    public void updateUserRoleByUsername(String username, String role) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(updateUserRoleByUsernameSQL());
        addRoleToUserPrepareStatement.setString(1, role);
        addRoleToUserPrepareStatement.setString(2, username);
        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, updateUserRoleByUsernameSQL());

        addRoleToUserPrepareStatement.execute();
    }

    private void createNewUser(String username, String password) throws SQLException {
        PreparedStatement createNewUserPrepareStatement = connection.prepareStatement(createNewUserByUsernameAndPasswordSQL());
        createNewUserPrepareStatement.setString(1, username);
        createNewUserPrepareStatement.setString(2, password);
        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, createNewUserByUsernameAndPasswordSQL());

        createNewUserPrepareStatement.execute();
    }

    private Map<String, Boolean> userComponentMap(ResultSet result) throws SQLException {
        Map<String, Boolean> componentList = new HashMap<>();

        componentList.put("battlesimulator", result.getBoolean("battlesimulator"));
        componentList.put("tierlist", result.getBoolean("tierlist"));
        componentList.put("usermaintenance", result.getBoolean("usermaintenance"));
        componentList.put("datamaintenance", result.getBoolean("datamaintenance"));

        return componentList;
    }

    private boolean addRoleToNewUser(String username, String password, String role) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(addRoleToNewUserSQL());
        addRoleToUserPrepareStatement.setString(1, role);
        addRoleToUserPrepareStatement.setString(2, username);
        addRoleToUserPrepareStatement.setString(3, password);
        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, addRoleToNewUserSQL());

        return addRoleToUserPrepareStatement.execute();
    }

    private boolean addRoleToNewUser(String username, String password) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(addUserRoleToNewUserSQL());
        addRoleToUserPrepareStatement.setString(1, username);
        addRoleToUserPrepareStatement.setString(2, password);
        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, addUserRoleToNewUserSQL());

        return addRoleToUserPrepareStatement.execute();
    }

    private boolean addDefaultConfigurationOptionsToNewUser(String username) throws SQLException {
        PreparedStatement addRoleToUserPrepareStatement = connection.prepareStatement(addUserDefaultConfigurationOptionsSQL());
        addRoleToUserPrepareStatement.setString(1, username);
        Logger.getLogger(UserDA.class.getName()).log(Level.INFO, addUserDefaultConfigurationOptionsSQL());

        return addRoleToUserPrepareStatement.execute();
    }

    private String addUserDefaultConfigurationOptionsSQL() {
        return "INSERT INTO ConfigurationOptions (UserID)\n" +
                "VALUES ((SELECT UserID FROM User WHERE UserName = ?))";
    }

    private String updateUserRoleByUsernameSQL() {
        return "UPDATE UserRole " +
                "SET RoleID = (SELECT RoleID FROM Role WHERE RoleName = ?) " +
                "WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?)";
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
        return "SELECT Username     AS username,\n" +
                "       UserPassword AS password,\n" +
                "       r.RoleName   AS rolename,\n" +
                "       co.BattleSimulator AS battlesimulator,\n" +
                "       co.tierlist AS tierlist,\n" +
                "       co.UserMaintenance AS usermaintenance,\n" +
                "       co.DataMaintenance AS datamaintenance\n" +
                "FROM User u\n" +
                "       INNER JOIN UserRole ur ON u.UserID = ur.UserID\n" +
                "       INNER JOIN Role r ON ur.RoleID = r.RoleID\n" +
                "       INNER JOIN ConfigurationOptions co ON u.UserID = co.UserID\n" +
                "WHERE u.UserName = ?\n" +
                "  AND u.UserPassword = ?";
    }
}
