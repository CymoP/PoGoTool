package data_access;

import loader.ApplicationLoader;
import model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeDA {

    public Type getTypeByName(String typeName) throws SQLException {
        String sql = getTypeByNameSQL();

        PreparedStatement verifyUserPrepareStatement = ApplicationLoader.getConnection().prepareStatement(sql);
        verifyUserPrepareStatement.setString(1, typeName);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, sql);
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if(result.first()){
            return new Type(
                    result.getString("typeName"),
                    result.getInt("multiplierBug"),
                    result.getInt("multiplierDark"),
                    result.getInt("multiplierDragon"),
                    result.getInt("multiplierElectric"),
                    result.getInt("multiplierFairy"),
                    result.getInt("multiplierFighting"),
                    result.getInt("multiplierFire"),
                    result.getInt("multiplierFlying"),
                    result.getInt("multiplierGhost"),
                    result.getInt("multiplierGrass"),
                    result.getInt("multiplierGround"),
                    result.getInt("multiplierIce"),
                    result.getInt("multiplierNormal"),
                    result.getInt("multiplierPoison"),
                    result.getInt("multiplierPsychic"),
                    result.getInt("multiplierRock"),
                    result.getInt("multiplierSteel"),
                    result.getInt("multiplierWater")
            );
        }

        return null;
    }

    private String getTypeByNameSQL() {
        return "SELECT TypeName AS typeName, " +
                "MultiplierBug AS multiplierBug, " +
                "MultiplierDark AS multiplierDark, " +
                "MultiplierDragon AS multiplierDragon, " +
                "MultiplierElectric AS multiplierElectric, " +
                "MultiplierFairy AS multiplierFairy, " +
                "MultiplierFighting AS multiplierFighting, " +
                "MultiplierFire AS multiplierFire, " +
                "MultiplierFlying AS multiplierFlying, " +
                "MultiplierGhost AS multiplierGhost, " +
                "MultiplierGrass AS multiplierGrass, " +
                "MultiplierGround AS multiplierGround, " +
                "MultiplierIce AS multiplierIce, " +
                "multiplierNormal AS multiplierNormal, " +
                "multiplierPoison AS multiplierPoison, " +
                "multiplierPsychic AS multiplierPsychic, " +
                "multiplierRock AS multiplierRock, " +
                "multiplierSteel AS multiplierSteel, " +
                "multiplierWater AS multiplierWater, " +
                "FROM Type " +
                "WHERE TypeName = ?";
    }
}
