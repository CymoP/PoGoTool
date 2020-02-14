package utils;

import model.SelectedPokemon;
import services.SelectedPokemonService;

import java.util.HashMap;

/**
 * BattleSimulatorReportController is a class for displaying the details of BattleSimulator report in a readable and useful way
 */
public class BattleSimulatorReport {

    private static BattleSimulatorReport instance;
    private HashMap<Integer, String> battleSimulatorReport = new HashMap<>();
    private String output;
    private int logCounter;

    private BattleSimulatorReport() {

    }

    /**
     * Singleton implementation - If instance exists use that, if not create one
     *
     * @return Database connection instance
     */
    public static BattleSimulatorReport getInstance() {
        if (instance == null) {
            instance = new BattleSimulatorReport();
        }

        return instance;
    }


    /**
     * Adds initial pokemon details to the report log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentOne        Selected pokemon one
     * @param opponentTwo        Selected pokemon two
     */
    public void addPokemonDetailsToLog(int currentTurnCounter, SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {
        battleSimulatorReport.clear();
        logCounter = 0;

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

    /**
     * Adds using charge move details to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentNumber     Which pokemon is being logged
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonUsedChargedMove(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon) {
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " used " + selectedPokemon.getSelectedChargedMove().getMoveName());
    }

    /**
     * Adds using fast move details to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentNumber     Which pokemon is being logged
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonUserFastMove(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon) {
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " used " + selectedPokemon.getSelectedFastMove().getMoveName());
    }

    /**
     * Adds fast move intermission wait times to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentNumber     Which pokemon is being logged
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonWaitingForFastMoveIntermission(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon) {
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for fast move intermission to end");
    }

    /**
     * Adds charge move intermission wait times to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentNumber     Which pokemon is being logged
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonWaitingForChargedMoveIntermission(int currentTurnCounter, int opponentNumber, SelectedPokemon selectedPokemon) {
        battleSimulatorReport.put(++logCounter, "Turn context = " + currentTurnCounter + "| opponent " + opponentNumber + " - " + selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for charged move intermission to end");
    }

    /**
     * Adds the winner of the simulation to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param opponentNumber     Which pokemon is being logged
     */
    public void addPokemonWinner(int currentTurnCounter, int opponentNumber) {
        battleSimulatorReport.put(++logCounter, "Winner - Opponent " + opponentNumber + " has won on turn " + currentTurnCounter);
    }

    public String getOutput() {
        return output;
    }

    /**
     * Prints the results of the simulation to the console
     */
    public void setOutput() {
        StringBuilder output = new StringBuilder();
        this.output = "";

        for (int keyCount = 0; keyCount < battleSimulatorReport.size(); keyCount++) {
            System.out.println(battleSimulatorReport.get(keyCount));
            output.append(battleSimulatorReport.get(keyCount));
            output.append("\n");
        }

        this.output = output.toString();
    }
}
