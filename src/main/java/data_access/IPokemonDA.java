package data_access;

import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public interface IPokemonDA {

    /**
     * Returns a pokemon based off it's pokemon name, generation and type
     *
     * @param pokemonName given pokemon name
     * @param generation  given generation
     * @param type        given type
     * @return pokemon object for the given pokemon name, generation and type
     * @throws SQLException
     */
    Pokemon getPokemonByNameAndGenerationAndType(String pokemonName, int generation, String type) throws SQLException;

    /**
     * Returns the names of all the pokemon in the database
     *
     * @return List of names of all the pokemon in the database
     * @throws SQLException
     */
    List<String> getPokemonNameList() throws SQLException;


    /**
     * Returns all the pokemon from the database
     *
     * @return list of available pokemon
     * @throws SQLException
     */
    List<Pokemon> getAllPokemon() throws SQLException;
}
