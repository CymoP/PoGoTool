package services;

import data_access.PokemonDA;
import model.ChargedMove;
import model.FastMove;
import model.Pokemon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonService {

    private List<Pokemon> pokemonList;

    public PokemonService() throws SQLException {
        PokemonDA pokemonDA = new PokemonDA();
        pokemonList = pokemonDA.getAllPokemon();
    }

    public Pokemon getPokemonByName(String pokemonName) {

        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getPokemonName().equals(pokemonName)) {
                return pokemon;
            }
        }

        return new Pokemon();
    }

    public List<String> getAllPokemonNamesList() {
        List<String> pokemonNameList = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            pokemonNameList.add(pokemon.getPokemonName());
        }

        return pokemonNameList;
    }

    public List<String> getPokemonFastMoveNameList(Pokemon pokemon) {
        List<String> pokemonFastMoveNameList = new ArrayList<>();

        for (FastMove fastMove : pokemon.getFastMoveList()) {
            pokemonFastMoveNameList.add(fastMove.getMoveName());
        }

        return pokemonFastMoveNameList;
    }

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
