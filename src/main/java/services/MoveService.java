package services;

import model.ChargedMove;
import model.FastMove;
import model.Pokemon;

/**
 * MoveService is a service class for all Move business logic
 */
public class MoveService {

    public MoveService() {
    }

    /**
     * Returns the fast move object for the given move name for a given pokemon
     *
     * @param pokemon given pokemon
     * @param moveName given fast move name
     * @return fast move object
     */
    public FastMove getFastMoveDetailsByName(Pokemon pokemon, String moveName) {

        for (FastMove fastMove : pokemon.getFastMoveList()) {
            if (fastMove.getMoveName().equals(moveName)) {
                return fastMove;
            }
        }

        return null;
    }

    /**
     * Returns the charged move object for the given move name for a given pokemon
     *
     * @param pokemon given pokemon
     * @param moveName given charged move name
     * @return charged move object
     */
    public ChargedMove getChargedMoveDetailsByName(Pokemon pokemon, String moveName) {

        for (ChargedMove chargedMove : pokemon.getChargedMoveList()) {
            if (chargedMove.getMoveName().equals(moveName)) {
                return chargedMove;
            }
        }

        return null;
    }
}
