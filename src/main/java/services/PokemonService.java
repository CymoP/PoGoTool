package services;

import data_access.PokemonDA;
import model.ChargedMove;
import model.FastMove;
import model.Pokemon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PokemonService is a service class for all Pokemon business logic
 *
 */
public class PokemonService {

    private List<Pokemon> pokemonList;

    public PokemonService() throws SQLException {
        PokemonDA pokemonDA = new PokemonDA();
        pokemonList = pokemonDA.getAllPokemon();
    }

    /**
     * Returns a pokemon object from the pokemon list by it's name
     *
     * @param pokemonName given pokemon name
     * @return pokemon object for given name
     */
    public Pokemon getPokemonByName(String pokemonName) {

        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getPokemonName().equals(pokemonName)) {
                return pokemon;
            }
        }

        return new Pokemon();
    }

    /**
     * Returns the list of all the name of all the pokemon in the database
     *
     * @return pokemon name list
     */
    public List<String> getAllPokemonNamesList() {
        List<String> pokemonNameList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            pokemonNameList.add(pokemon.getPokemonName());
        }

        return pokemonNameList;
    }

    /**
     * Returns the fast move list for a given pokemon
     *
     * @param pokemon given pokemon
     * @return pokemon fast move list
     */
    public List<String> getPokemonFastMoveNameList(Pokemon pokemon) {
        List<String> pokemonFastMoveNameList = new ArrayList<>();

        for (FastMove fastMove : pokemon.getFastMoveList()) {
            pokemonFastMoveNameList.add(fastMove.getMoveName());
        }

        return pokemonFastMoveNameList;
    }

    /**
     * Returns the charged move list for a given pokemon
     *
     * @param pokemon given pokemon
     * @return pokemon charged move list
     */
    public List<String> getPokemonChargedMoveNameList(Pokemon pokemon) {
        List<String> pokemonChargedMoveNameList = new ArrayList<>();

        for (ChargedMove chargedMove : pokemon.getChargedMoveList()) {
            pokemonChargedMoveNameList.add(chargedMove.getMoveName());
        }

        return pokemonChargedMoveNameList;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
