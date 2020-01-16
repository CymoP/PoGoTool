package services;

import builder.CPMBuilder;
import model.SelectedPokemon;

import java.util.Map;

public class SelectedPokemonService {

    private static Map<Double, Double> cpmMap = CPMBuilder.buildCPMMap();

    private SelectedPokemonService(){}

    public static double getAttackStat(SelectedPokemon selectedPokemon){
        int baseAttack = selectedPokemon.getBasePokemon().getBaseAttack();
        int ivAttack = selectedPokemon.getIvAttack();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseAttack + ivAttack);
    }

    public static double getDefenseStat(SelectedPokemon selectedPokemon){
        int baseDefense = selectedPokemon.getBasePokemon().getBaseDefense();
        int ivDefense = selectedPokemon.getIvDefense();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseDefense + ivDefense);
    }

    public static double getStaminaStat(SelectedPokemon selectedPokemon){
        int baseStamina = selectedPokemon.getBasePokemon().getBaseStamina();
        int ivStamina = selectedPokemon.getIvStamina();
        Double cpm = cpmMap.get(selectedPokemon.getLevel());

        return cpm * (baseStamina + ivStamina);
    }
}
