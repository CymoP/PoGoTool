package data_access;

import loader.ApplicationLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDA {

    public int getUserByUsernameAndPassword(String username, String password) throws SQLException {

        String sql = "SELECT COUNT(*) AS total FROM USER WHERE UserName = ? AND UserPassword = ?";

        PreparedStatement verifyUserPrepareStatement = ApplicationLoader.getConnection().prepareStatement(sql);
        verifyUserPrepareStatement.setString(1, username);
        verifyUserPrepareStatement.setString(2, password);

        ResultSet result = verifyUserPrepareStatement.executeQuery();
        result.first();

        return result.getInt("total");
    }
}
