package model;

import java.util.List;

public class Pokemon {

    private String pokemonName;
    private Integer generation;
    private Type pokemonType;
    private Type pokemonDualType;
    private Integer baseAttack;
    private Integer baseDefense;
    private Integer baseStamina;
    private List<Moveset> movesetList;

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

    public List<Moveset> getMovesetList() {
        return movesetList;
    }

    public void setMovesetList(List<Moveset> movesetList) {
        this.movesetList = movesetList;
    }
}
