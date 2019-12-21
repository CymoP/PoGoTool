package model;

public class Moveset {

    private Move fastMove;
    private Move chargedMove;

    public Move getFastMove() {
        return fastMove;
    }

    public void setFastMove(Move fastMove) {
        this.fastMove = fastMove;
    }

    public Move getChargedMove() {
        return chargedMove;
    }

    public void setChargedMove(Move chargedMove) {
        this.chargedMove = chargedMove;
    }
}
