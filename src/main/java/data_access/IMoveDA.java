package data_access;

import model.Move;

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
    Move getMoveByName(String moveName) throws SQLException;

    /**
     * Returns the list of moves for a given pokemon
     *
     * @param pokemonName given pokemon name
     * @param generation given generation
     * @param type given type
     * @param moveUsage given move usage - fast|charge
     * @return move list for a given pokemon
     * @throws SQLException
     */
    List<Move> getMoveListForPokemonByUsage(String pokemonName, int generation, String type, String moveUsage) throws SQLException;
}
