package data_access;

import loader.ApplicationLoader;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDA {

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {

        PreparedStatement verifyUserPrepareStatement = ApplicationLoader.getConnection().prepareStatement(getUserByUsernameAndPasswordSQL());
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, getUserByUsernameAndPasswordSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if(result.first()){
            return new User(result.getString("username"), result.getString("password"), result.getString("rolename"));
        }

        return null;
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
