package services;

import data_access.MoveDA;
import data_access.PokemonDA;
import data_access.TypeDA;
import model.Pokemon;

import java.sql.SQLException;

public class PokemonService {

    private PokemonDA pokemonDA = new PokemonDA();
    private MoveDA moveDA = new MoveDA();
    private Pokemon pokemon;

    public Pokemon getPokemonDetails(String pokemonName, int generation, String typeName) throws SQLException {

        pokemon = pokemonDA.getPokemonByNameAndGenerationAndType(pokemonName, generation, typeName);
        pokemon.setMovesetList(moveDA.getMoveListForPokemonByUsage(pokemonName, generation, typeName, "Fast"));
        pokemon.setMovesetList(moveDA.getMoveListForPokemonByUsage(pokemonName, generation, typeName, "Charge"));

        return pokemon;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }
}
