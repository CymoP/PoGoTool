package model;

public class SelectedPokemon {

    private Pokemon basePokemon;
    private Double level;
    private int ivAttack;
    private int ivDefense;
    private int ivStamina;
    private FastMove selectedFastFastMove;
    private ChargedMove selectedChargeFastMove;

    public SelectedPokemon(Pokemon basePokemon, Double level, int ivAttack, int ivDefense, int ivStamina, FastMove selectedFastFastMove, ChargedMove selectedChargeFastMove) {
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

    public FastMove getSelectedFastFastMove() {
        return selectedFastFastMove;
    }

    public void setSelectedFastFastMove(FastMove selectedFastFastMove) {
        this.selectedFastFastMove = selectedFastFastMove;
    }

    public ChargedMove getSelectedChargeFastMove() {
        return selectedChargeFastMove;
    }

    public void setSelectedChargeFastMove(ChargedMove selectedChargeFastMove) {
        this.selectedChargeFastMove = selectedChargeFastMove;
    }
}
