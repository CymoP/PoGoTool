package model;

public class SelectedPokemon {

    private Pokemon basePokemon;
    private Double level;
    private int ivAttack;
    private int ivDefense;
    private int ivStamina;
    private Move selectedFastMove;
    private Move selectedChargeMove;

    public SelectedPokemon(Pokemon basePokemon, Double level, int ivAttack, int ivDefense, int ivStamina, Move selectedFastMove, Move selectedChargeMove){}

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

    public Move getSelectedFastMove() {
        return selectedFastMove;
    }

    public void setSelectedFastMove(Move selectedFastMove) {
        this.selectedFastMove = selectedFastMove;
    }

    public Move getSelectedChargeMove() {
        return selectedChargeMove;
    }

    public void setSelectedChargeMove(Move selectedChargeMove) {
        this.selectedChargeMove = selectedChargeMove;
    }
}
