package data_access;

import loader.ApplicationLoader;
import loader.DatabaseSingleton;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDA implements IUserDA{

    private Connection connection = DatabaseSingleton.getInstance().getConnection();

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getUserByUsernameAndPasswordSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getUserByUsernameAndPasswordSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if(result.first()){
            return new User(result.getString("username"), result.getString("password"), result.getString("rolename"));
        }

        return null;
    }

    public boolean createNewUserByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(createNewUserByUsernameAndPasswordSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        verifyUserPrepareStatement = connection.prepareStatement(addRoleToNewUserSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        return true;
    }

    private String createNewUserByUsernameAndPasswordSQL() {
        return "INSERT INTO USER (username, userpassword) " +
                "VALUES (?, ?)";
    }

    private String addRoleToNewUserSQL() {
        return "INSERT INTO UserRole(RoleID, UserID) " +
                "VALUES ((SELECT RoleID FROM Role WHERE RoleName = 'User'), " +
                "(SELECT UserID FROM User WHERE UserName = ? AND UserPassword = ?))";
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
