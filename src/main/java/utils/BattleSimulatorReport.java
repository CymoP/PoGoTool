package utils;

import model.SelectedPokemon;
import services.SelectedPokemonService;

import java.util.HashMap;

/**
 * BattleSimulatorReportController is a class for displaying the details of BattleSimulator report in a readable and useful way
 */
public class BattleSimulatorReport {

    private static BattleSimulatorReport instance;
    private static HashMap<Integer, String> battleSimulatorReport = new HashMap<>();
    private String output;
    private static int logCounter;

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

        battleSimulatorReport.clear();
        logCounter = 0;

        return instance;
    }


    /**
     * Adds initial pokemon details to the report log
     *
     * @param opponentOne Selected pokemon one
     * @param opponentTwo Selected pokemon two
     */
    public void addPokemonDetailsToLog(SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {
        battleSimulatorReport.put(logCounter, "Pokemon One Details");
        buildPokemonDetails(opponentOne);

        battleSimulatorReport.put(++logCounter, "Pokemon Two Details");
        buildPokemonDetails(opponentTwo);
    }

    /**
     * Adds using charge move details to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param attackingPokemon   Attacking pokemon
     * @param defendingPokemon   Defending pokemon
     * @param currentHealth      Current health of defending pokemon
     */
    public void addPokemonUsedChargedMove(int currentTurnCounter, SelectedPokemon attackingPokemon, SelectedPokemon defendingPokemon, double currentHealth) {
        String output = "Turn context = " + currentTurnCounter + " | " +
                attackingPokemon.getBasePokemon().getPokemonName() + " used " + attackingPokemon.getSelectedChargedMove().getMoveName() + " | " +
                defendingPokemon.getBasePokemon().getPokemonName() + " current health " + currentHealth;

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Adds using fast move details to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param attackingPokemon   Attacking pokemon
     * @param defendingPokemon   Defending pokemon
     * @param currentHealth      Current health of defending pokemon
     */
    public void addPokemonUsedFastMove(int currentTurnCounter, SelectedPokemon attackingPokemon, SelectedPokemon defendingPokemon, double currentHealth) {
        String output = "Turn context = " + currentTurnCounter + " | " +
                attackingPokemon.getBasePokemon().getPokemonName() + " used " + attackingPokemon.getSelectedFastMove().getMoveName() + " | " +
                defendingPokemon.getBasePokemon().getPokemonName() + " current health " + currentHealth;

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Adds fast move intermission wait times to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonWaitingForFastMoveIntermission(int currentTurnCounter, SelectedPokemon selectedPokemon) {
        String output = "Turn context = " + currentTurnCounter + " | " +
                selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for fast move intermission to end";

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Adds charge move intermission wait times to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulatio
     * @param selectedPokemon    Selected pokemon
     */
    public void addPokemonWaitingForChargedMoveIntermission(int currentTurnCounter, SelectedPokemon selectedPokemon) {
        String output = "Turn context = " + currentTurnCounter + " | " +
                selectedPokemon.getBasePokemon().getPokemonName() + " is waiting for charged move intermission to end";

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Adds pokemon used a shield to block a charge move to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param attackingPokemon   the attacking pokemon
     * @param defendingPokemon   the defending pokemon
     */
    public void addPokemonUsedShield(int currentTurnCounter, SelectedPokemon attackingPokemon, SelectedPokemon defendingPokemon) {
        String output = "Turn Context = " + currentTurnCounter + " | " +
                defendingPokemon.getBasePokemon().getPokemonName() + " blocked " + attackingPokemon.getSelectedChargedMove().getMoveName() + " using a shield";

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Adds the winner of the simulation to the log
     *
     * @param currentTurnCounter Current turn context within the battle simulation
     * @param winner             The pokemon which won
     */
    public void addPokemonWinner(int currentTurnCounter, SelectedPokemon winner) {
        String output = "Winner - " + winner.getBasePokemon().getPokemonName() +
                " has won on turn " + currentTurnCounter;

        battleSimulatorReport.put(++logCounter, output);
    }

    /**
     * Gets the summary for a simulation
     *
     * @return the final entry to the battle report which denotes the winner
     */
    public String getSummary() {
        return battleSimulatorReport.get(battleSimulatorReport.size() - 1);
    }

    /**
     * Returns the log for the battle simulation
     *
     * @return the log for the battle simulation
     */
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
            output.append(battleSimulatorReport.get(keyCount));
            output.append("\n");
        }

        this.output = output.toString();
    }

    private void buildPokemonDetails(SelectedPokemon selectedPokemon) {
        battleSimulatorReport.put(++logCounter, "Pokemon Name - " + selectedPokemon.getBasePokemon().getPokemonName());
        battleSimulatorReport.put(++logCounter, "Pokemon Attack Stat - " + SelectedPokemonService.getAttackStat(selectedPokemon));
        battleSimulatorReport.put(++logCounter, "Pokemon Defense Stat - " + SelectedPokemonService.getDefenseStat(selectedPokemon));
        battleSimulatorReport.put(++logCounter, "Pokemon Stamina Stat - " + SelectedPokemonService.getStaminaStat(selectedPokemon));
        battleSimulatorReport.put(++logCounter, "Pokemon Fast Move - " + selectedPokemon.getSelectedFastMove().getMoveName());
        battleSimulatorReport.put(++logCounter, "Pokemon Charge Move - " + selectedPokemon.getSelectedChargedMove().getMoveName());
    }
}
