package services;

import model.ChargedMove;
import model.FastMove;
import model.Pokemon;

public class MoveService {

    public MoveService() {
    }

    public FastMove getFastMoveDetailsByName(Pokemon pokemon, String moveName) {

        for (FastMove fastMove : pokemon.getFastMoveList()) {
            if (fastMove.getMoveName().equals(moveName)) {
                return fastMove;
            }
        }

        return null;
    }

    public ChargedMove getChargedMoveDetailsByName(Pokemon pokemon, String moveName) {

        for (ChargedMove chargedMove : pokemon.getChargedMoveList()) {
            if (chargedMove.getMoveName().equals(moveName)) {
                return chargedMove;
            }
        }

        return null;
    }
}
