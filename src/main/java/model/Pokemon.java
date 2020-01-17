package model;

import java.util.List;

/**
 * POJO encapsulating data about a Pokemon
 */
public class Pokemon {

    private String pokemonName;
    private Integer generation;
    private Type pokemonType;
    private Type pokemonDualType;
    private Integer baseAttack;
    private Integer baseDefense;
    private Integer baseStamina;
    private List<FastMove> fastMoveList;
    private List<ChargedMove> chargedMoveList;

    public Pokemon() {
    }

    public Pokemon(String pokemonName, Integer generation, Type pokemonType, Type pokemonDualType, Integer baseAttack, Integer baseDefense, Integer baseStamina, List<FastMove> fastMoveList, List<ChargedMove> chargedMoveList) {
        this.pokemonName = pokemonName;
        this.generation = generation;
        this.pokemonType = pokemonType;
        this.pokemonDualType = pokemonDualType;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseStamina = baseStamina;
        this.fastMoveList = fastMoveList;
        this.chargedMoveList = chargedMoveList;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Type getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(Type pokemonType) {
        this.pokemonType = pokemonType;
    }

    public Type getPokemonDualType() {
        return pokemonDualType;
    }

    public void setPokemonDualType(Type pokemonDualType) {
        this.pokemonDualType = pokemonDualType;
    }

    public Integer getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(Integer baseAttack) {
        this.baseAttack = baseAttack;
    }

    public Integer getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(Integer baseDefense) {
        this.baseDefense = baseDefense;
    }

    public Integer getBaseStamina() {
        return baseStamina;
    }

    public void setBaseStamina(Integer baseStamina) {
        this.baseStamina = baseStamina;
    }

    public List<FastMove> getFastMoveList() {
        return fastMoveList;
    }

    public void setFastMoveList(List<FastMove> fastMoveList) {
        this.fastMoveList = fastMoveList;
    }

    public List<ChargedMove> getChargedMoveList() {
        return chargedMoveList;
    }

    public void setChargedMoveList(List<ChargedMove> chargedMoveList) {
        this.chargedMoveList = chargedMoveList;
    }
}
