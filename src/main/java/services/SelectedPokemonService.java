package services;

import builder.CPMBuilder;
import model.SelectedPokemon;

import java.util.Map;

/**
 * SelectedPokemonService is a service class for the all SelectedPokemon business logic, see SelectedPokemon.java for more details on context
 */
public class SelectedPokemonService {

    private static Map<Double, Double> cpmMap = CPMBuilder.buildCPMMap();

    private SelectedPokemonService() {
    }

    /**
     * Returns the attack stat for the selected pokemon by taking into account base stats and IVs
     *
     * @param selectedPokemon given selected pokemon
     * @return derived attack stat
     */
    public static double getAttackStat(SelectedPokemon selectedPokemon) {
        int baseAttack = selectedPokemon.getBasePokemon().getBaseAttack();
        int ivAttack = selectedPokemon.getIvAttack();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseAttack + ivAttack);
    }

    /**
     * Returns the Defense stat for the selected pokemon by taking into account base stats and IVs
     *
     * @param selectedPokemon given selected pokemon
     * @return derived defense stat
     */
    public static double getDefenseStat(SelectedPokemon selectedPokemon) {
        int baseDefense = selectedPokemon.getBasePokemon().getBaseDefense();
        int ivDefense = selectedPokemon.getIvDefense();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseDefense + ivDefense);
    }

    /**
     * Returns the stamina stat for the selected pokemon by taking into account base stats and IVs
     *
     * @param selectedPokemon given selected pokemon
     * @return derived stamina stat
     */
    public static double getStaminaStat(SelectedPokemon selectedPokemon) {
        int baseStamina = selectedPokemon.getBasePokemon().getBaseStamina();
        int ivStamina = selectedPokemon.getIvStamina();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseStamina + ivStamina);
    }
}
