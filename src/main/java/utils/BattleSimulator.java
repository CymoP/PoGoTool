package utils;

import model.SelectedPokemon;
import services.SelectedPokemonService;

public class BattleSimulator {


    public BattleSimulator() {}

    public int calculateTimeToWin(SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {

        double opponentOneAttack = SelectedPokemonService.getAttackStat(opponentOne);
        double opponentOneDefense = SelectedPokemonService.getDefenseStat(opponentOne);
        double opponentOneStamina = SelectedPokemonService.getStaminaStat(opponentOne);
        int opponentOneEnergy = 0;

        double opponentTwoAttack = SelectedPokemonService.getAttackStat(opponentTwo);
        double opponentTwoDefense = SelectedPokemonService.getDefenseStat(opponentTwo);
        double opponentTwoStamina = SelectedPokemonService.getStaminaStat(opponentTwo);

        return 0;
    }
}
