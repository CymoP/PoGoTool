package model;

public class SelectedPokemon {

    private Pokemon basePokemon;
    private Double level;
    private int ivAttack;
    private int ivDefense;
    private int ivStamina;
    private FastMove selectedFastMove;
    private ChargedMove selectedChargedMove;

    public SelectedPokemon(Pokemon basePokemon, Double level, int ivAttack, int ivDefense, int ivStamina, FastMove selectedFastMove, ChargedMove selectedChargedMove) {
        this.basePokemon = basePokemon;
        this.level = level;
        this.ivAttack = ivAttack;
        this.ivDefense = ivDefense;
        this.ivStamina = ivStamina;
        this.selectedFastMove = selectedFastMove;
        this.selectedChargedMove = selectedChargedMove;
    }

    public Pokemon getBasePokemon() {
        return basePokemon;
    }

    public void setBasePokemon(Pokemon basePokemon) {
        this.basePokemon = basePokemon;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public int getIvAttack() {
        return ivAttack;
    }

    public void setIvAttack(int ivAttack) {
        this.ivAttack = ivAttack;
    }

    public int getIvDefense() {
        return ivDefense;
    }

    public void setIvDefense(int ivDefense) {
        this.ivDefense = ivDefense;
    }

    public int getIvStamina() {
        return ivStamina;
    }

    public void setIvStamina(int ivStamina) {
        this.ivStamina = ivStamina;
    }

    public FastMove getSelectedFastMove() {
        return selectedFastMove;
    }

    public void setSelectedFastMove(FastMove selectedFastMove) {
        this.selectedFastMove = selectedFastMove;
    }

    public ChargedMove getSelectedChargedMove() {
        return selectedChargedMove;
    }

    public void setSelectedChargedMove(ChargedMove selectedChargedMove) {
        this.selectedChargedMove = selectedChargedMove;
    }
}
