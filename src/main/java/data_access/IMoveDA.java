package data_access;

import model.ChargedMove;
import model.FastMove;

import java.sql.SQLException;
import java.util.List;

public interface IMoveDA {

    /**
     * Returns a move by it's name
     *
     * @param moveName given move name
     * @return move object for the given name
     * @throws SQLException
     */
    FastMove getMoveByName(String moveName) throws SQLException;

    /**
     * Returns the list of fast moves for a given pokemon
     *
     * @param pokemonName given pokemon name
     * @param generation  given generation
     * @param type        given type
     * @return move list for a given pokemon
     * @throws SQLException
     */
    List<FastMove> getFastMoveListByPokemon(String pokemonName, int generation, String type) throws SQLException;

    /**
     * Returns the list of charged moves for a given pokemon
     *
     * @param pokemonName given pokemon name
     * @param generation  given generation
     * @param type        given type
     * @return move list for a given pokemon
     * @throws SQLException
     */
    List<ChargedMove> getChargedMoveListByPokemon(String pokemonName, int generation, String type) throws SQLException;
}
