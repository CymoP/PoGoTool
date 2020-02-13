package utils;

import model.SelectedPokemon;
import services.SelectedPokemonService;

import java.util.HashMap;
import java.util.Map;

public class BattleSimulatorReport {

    HashMap<Integer, String> battleSimulatorReport = new HashMap<>();
    int logCounter = 0;

    public BattleSimulatorReport() {

    }

    public void addPokemonDetailsToLog(int currentTurnCounter, SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {

        battleSimulatorReport.put(logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Name - " + opponentOne.getBasePokemon().getPokemonName());
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Attack Stat - " + SelectedPokemonService.getAttackStat(opponentOne));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Defense Stat - " + SelectedPokemonService.getDefenseStat(opponentOne));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Stamina Stat - " + SelectedPokemonService.getStaminaStat(opponentOne));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Fast Move - " + opponentOne.getSelectedFastMove().getMoveName());
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentOne - " + "Pokemon Charge Move - " + opponentOne.getSelectedChargedMove().getMoveName());

        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Name - " + opponentTwo.getBasePokemon().getPokemonName());
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Attack Stat - " + SelectedPokemonService.getAttackStat(opponentTwo));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Defense Stat - " + SelectedPokemonService.getDefenseStat(opponentTwo));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Stamina Stat - " + SelectedPokemonService.getStaminaStat(opponentTwo));
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Fast Move - " + opponentTwo.getSelectedFastMove().getMoveName());
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponentTwo - " + "Pokemon Charge Move - " + opponentTwo.getSelectedChargedMove().getMoveName());
    }

    public void addPokemonUsedChargedMove(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon){
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " used " + selectedPokemon.getSelectedChargedMove().getMoveName());
    }

    public void addPokemonUserFastMove(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon){
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " used " + selectedPokemon.getSelectedFastMove().getMoveName());
    }

    public void addPokemonWaitingForFastMoveIntermission(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon){
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for fast move intermission to end");
    }

    public void addPokemonWaitingForChargedMoveIntermission(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon){
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for charged move intermission to end");
    }

    public void addPokemonWinner(int currentTurnCounter, int opponentNumber){
        battleSimulatorReport.put(++logCounter, "Winner - Opponent " + opponentNumber + " has won on turn " + currentTurnCounter);
    }

    public void printOutput(){
        for (int keyCount = 0; keyCount < battleSimulatorReport.size(); keyCount++) {
            System.out.println(battleSimulatorReport.get(keyCount));
        }
    }
}
