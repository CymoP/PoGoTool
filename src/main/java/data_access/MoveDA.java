package data_access;

import loader.ApplicationLoader;
import model.ChargedMove;
import model.FastMove;
import services.TypeService;
import utils.DatabaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveDA implements IMoveDA {

    private Connection connection = DatabaseSingleton.getInstance().getConnection();
    private TypeService typeService = TypeService.getInstance();

    public FastMove getMoveByName(String moveName) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getMoveByNameSQL());
        verifyUserPrepareStatement.setString(1, moveName);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getMoveByNameSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if (result.first()) {
            return mapFastMoveColumnsToObject(result);
        }

        return null;
    }

    public List<FastMove> getFastMoveListByPokemon(String pokemonName, int generation, String type) throws SQLException {
        List<FastMove> fastMoveList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getFastMoveListForPokemonSQL());
        verifyUserPrepareStatement.setString(1, pokemonName);
        verifyUserPrepareStatement.setInt(2, generation);
        verifyUserPrepareStatement.setString(3, type);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getFastMoveListForPokemonSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        if (result.first()) {
            do {
                fastMoveList.add(mapFastMoveColumnsToObject(result));
            }
            while (result.next());
            return fastMoveList;
        }

        return null;
    }

    public List<ChargedMove> getChargedMoveListByPokemon(String pokemonName, int generation, String type) throws SQLException {
        List<ChargedMove> chargedMoveList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getChargedMoveListForPokemonSQL());
        verifyUserPrepareStatement.setString(1, pokemonName);
        verifyUserPrepareStatement.setInt(2, generation);
        verifyUserPrepareStatement.setString(3, type);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getChargedMoveListForPokemonSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        if (result.first()) {
            do {
                chargedMoveList.add(mapChargedMoveColumnsToObject(result));
            }
            while (result.next());
            return chargedMoveList;
        }

        return null;
    }

    private FastMove mapFastMoveColumnsToObject(ResultSet result) throws SQLException {
        return new FastMove(result.getString("moveName"),
                typeService.getTypeByTypeName(result.getString("TypeName")),
                result.getInt("damagePvP"),
                result.getInt("energyPvp"),
                result.getInt("duration"));
    }

    private String getFastMoveListForPokemonSQL() {
        return "SELECT MoveName as moveName, TypeName as typeName, DamagePvP as damagePvP, EnergyPvP as energyPvp, Duration as duration\n" +
                "FROM fastmove\n" +
                "       INNER JOIN pokemonfastmove pfm ON fastmove.MoveID = (SELECT pfm.MoveID\n" +
                "                                                            WHERE PokemonID = (SELECT PokemonID\n" +
                "                                                                               FROM pokemon\n" +
                "                                                                               WHERE PokemonName = ?\n" +
                "                                                                                 AND Generation = ?\n" +
                "                                                                                 AND pokemon.TypeName = ?))";
    }

    private ChargedMove mapChargedMoveColumnsToObject(ResultSet result) throws SQLException {
        return new ChargedMove(result.getString("moveName"),
                typeService.getTypeByTypeName(result.getString("TypeName")),
                result.getInt("damagePvP"),
                result.getInt("energyPvp"));
    }

    private String getChargedMoveListForPokemonSQL() {
        return "SELECT MoveName as moveName, TypeName as typeName, DamagePvP as damagePvP, EnergyPvP as energyPvp\n" +
                "FROM chargedmove\n" +
                "       INNER JOIN pokemonchargedmove pcm ON chargedmove.MoveID = (SELECT pcm.MoveID\n" +
                "                                                            WHERE PokemonID = (SELECT PokemonID\n" +
                "                                                                               FROM pokemon\n" +
                "                                                                               WHERE PokemonName = ?\n" +
                "                                                                                 AND Generation = ?\n" +
                "                                                                                 AND pokemon.TypeName = ?))";
    }

    private String getMoveByNameSQL() {
        return "SELECT MoveName AS moveName " +
                "FROM move " +
                "WHERE MoveName = ?";
    }
}
