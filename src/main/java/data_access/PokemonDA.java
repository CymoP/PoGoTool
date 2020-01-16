package data_access;

import loader.ApplicationLoader;
import services.TypeService;
import utils.DatabaseSingleton;
import model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokemonDA implements IPokemonDA {

    private Connection connection = DatabaseSingleton.getInstance().getConnection();
    private TypeService typeService = TypeService.getInstance();

    public Pokemon getPokemonByNameAndGenerationAndType(String pokemonName, int generation, String type) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getPokemonByNameAndGenerationAndTypeSQL());
        verifyUserPrepareStatement.setString(1, pokemonName);
        verifyUserPrepareStatement.setInt(2, generation);
        verifyUserPrepareStatement.setString(3, type);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getPokemonByNameAndGenerationAndTypeSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if (result.first()) {
            return new Pokemon(result.getString("PokemonName"),
                    result.getInt("Generation"),
                    typeService.getTypeByTypeName(result.getString("TypeName")),
                    typeService.getTypeByTypeName(result.getString("DualTypeName")),
                    result.getInt("BaseAttack"),
                    result.getInt("BaseDefense"),
                    result.getInt("BaseStamina"));
        }

        return null;
    }

    public List<String> getPokemonNameList() throws SQLException {
        List<String> pokemonNameList = new ArrayList<>();

        PreparedStatement verifyUserPrepareStatement = connection.prepareStatement(getPokemonNameSQL());

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.INFO, getPokemonNameSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();

        if (result.first()) {
            while (result.next()) {
                pokemonNameList.add(result.getString("PokemonName"));
            }
            return pokemonNameList;
        }

        return null;
    }

    private String getPokemonNameSQL() {
        return "SELECT pokemonName AS PokemonName" +
                "FROM Pokemon ";
    }

    private String getPokemonByNameAndGenerationAndTypeSQL() {
        return "SELECT pokemonName AS PokemonName, " +
                "generation AS Generation, " +
                "typename AS TypeName, " +
                "dualtype AS DualTypeName, " +
                "baseattack AS BaseAttack, " +
                "basedefense AS BaseDefense, " +
                "basestamina AS BaseStamina" +
                "FROM pokemon " +
                "WHERE pokemonname = ? " +
                "AND generation = ? " +
                "AND typename = ?";
    }
}
