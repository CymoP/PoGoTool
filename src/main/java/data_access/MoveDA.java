package data_access;

import loader.ApplicationLoader;
import loader.DatabaseSingleton;
import model.Move;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveDA {

    private Connection connection = DatabaseSingleton.getInstance().getConnection();

    public Move getMoveByName(String moveName) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getMoveByNameSQL());
        verifyUserPrepareStatement.setString(1, moveName);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getMoveByNameSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if(result.first()){
            return mapMoveColumnsToObject(result);
        }

        return null;
    }

    public List<Move> getMoveListForPokemonByUsage(String pokemonName, int generation, String type, String moveUsage) throws SQLException {
        List<Move> moveList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getMoveListForPokemonByUsageSQL());
        verifyUserPrepareStatement.setString(1, pokemonName);
        verifyUserPrepareStatement.setInt(2, generation);
        verifyUserPrepareStatement.setString(3, type);
        verifyUserPrepareStatement.setString(4, moveUsage);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getMoveListForPokemonByUsageSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        if(result.first()){
            while(result.next()){
                moveList.add(mapMoveColumnsToObject(result));
            }
            return moveList;
        }

        return null;
    }

    private Move mapMoveColumnsToObject(ResultSet result) throws SQLException {
        return new Move(result.getString("moveName"),
                new Type(result.getString("typeName")),
                result.getString("moveUsage"),
                result.getInt("dps"),
                result.getInt("energyPvP"),
                result.getInt("castTime"),
                result.getInt("coolDown"),
                result.getInt("dpt"),
                result.getInt("ept"));
    }

    private String getMoveListForPokemonByUsageSQL() {
        return "SELECT MoveName AS moveName, " +
                "TypeName AS typeName, " +
                "MoveUsage AS moveUsage, " +
                "DPS AS dps, " +
                "EnergyPvP AS energyPvP, " +
                "CastTime AS castTime, " +
                "DPT AS dpt, " +
                "EPT AS ept, " +
                "CoolDown AS coolDown" +
                "FROM move " +
                "INNER JOIN PokemonMove pm ON move.MoveID = " +
                "(SELECT pm.MoveID " +
                "FROM pm " +
                "WHERE PokemonID = (" +
                "SELECT PokemonID " +
                "FROM Pokemon " +
                "WHERE PokemonName = ? " +
                "AND Generation = ? " +
                "AND TypeName = ?))" +
                "WHERE MoveUsage = ?";
    }

    private String getMoveByNameSQL() {
        return "SELECT MoveName AS moveName, " +
                "TypeName AS typeName, " +
                "MoveUsage AS moveUsage, " +
                "DPS AS dps, " +
                "EnergyPvP AS energyPvP, " +
                "CastTime AS castTime, " +
                "DPT AS dpt, " +
                "EPT AS ept, " +
                "CoolDown AS coolDown" +
                "FROM move " +
                "WHERE MoveName = ?";
    }
}
