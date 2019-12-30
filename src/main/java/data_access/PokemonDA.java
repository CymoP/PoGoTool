package data_access;

import loader.ApplicationLoader;
import model.Pokemon;
import model.Type;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokemonDA {

    private TypeDA typeDA = new TypeDA();

    public Pokemon getPokemonByNameAndGenerationAndType(String pokemonName, int generation, String type) throws SQLException {
        PreparedStatement verifyUserPrepareStatement = ApplicationLoader.getConnection().prepareStatement(getPokemonByNameAndGenerationAndTypeSQL());
        verifyUserPrepareStatement.setString(1, pokemonName);
        verifyUserPrepareStatement.setInt(2, generation);
        verifyUserPrepareStatement.setString(3, type);

        Logger.getLogger(ApplicationLoader.class.getName()).log(Level.SEVERE, getPokemonByNameAndGenerationAndTypeSQL());
        ResultSet result = verifyUserPrepareStatement.executeQuery();
        if(result.first()){
            return new Pokemon(result.getString("PokemonName"),
                    result.getInt("Generation"),
                    typeDA.getTypeByName(result.getString("TypeName")),
                    typeDA.getTypeByName(result.getString("DualTypeName")),
                    result.getInt("BaseAttack"),
                    result.getInt("BaseDefence"),
                    result.getInt("BaseStamina"));
        }

        return null;
    }

    private String getPokemonByNameAndGenerationAndTypeSQL() {
        return "SELECT pokemonName AS PokemonName, " +
                "generation AS Generation, " +
                "typename AS TypeName, " +
                "dualtype AS DualTypeName, " +
                "baseattack AS BaseAttack, " +
                "basedefence AS BaseDefence, " +
                "basestamina AS BaseStamina" +
                "FROM pokemon " +
                "WHERE pokemonname = ? " +
                "AND generation = ? " +
                "AND typename = ?";
    }
}
