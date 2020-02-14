package utils;

import model.ChargedMove;
import model.FastMove;
import model.SelectedPokemon;
import model.Type;
import services.SelectedPokemonService;

/**
 * BattleSimulator is a class used to determining the winner of a battle
 */
public class BattleSimulator {

    private static final int MAXIMUM_TURNS = 240;
    private static final int CHARGE_UP_DURATION = 8;
    private static final double STAB_MULTIPLIER = 1.2;
    private static final double BASE_DAMAGE_MULTIPLIER = 0.5;
    private static final double IMMUNITY_MULTIPLIER = 0.390625;
    private static final double RESISTANCE_MULTIPLIER = 0.625;
    private static final double WEAKNESS_MULTIPLIER = 1.6;

    public BattleSimulator() {
    }

    /**
     * Derives the winner of the battle using user input
     *
     * @param opponentOne selected pokemon one
     * @param opponentTwo selected pokemon two
     */
    public int simulatorAlgorithm(SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {
        BattleSimulatorReport battleSimulatorReport = BattleSimulatorReport.getInstance();

        int currentTurnCounter = 1;

        double opponentOneStamina = SelectedPokemonService.getStaminaStat(opponentOne);
        int opponentOneEnergy = 0;

        double opponentTwoStamina = SelectedPokemonService.getStaminaStat(opponentTwo);
        int opponentTwoEnergy = 0;

        FastMove opponentOneFastMove = opponentOne.getSelectedFastMove();
        ChargedMove opponentOneChargedMove = opponentOne.getSelectedChargedMove();
        int opponentOneChargedMoveEnergyCost = opponentOneChargedMove.getEnergyPvP();
        int opponentOneFastMoveEnergyGain = opponentOneFastMove.getEnergyPvP();
        int opponentOneFastMoveDuration = opponentOneFastMove.getDuration();
        int opponentOneTurnCounter = 1;

        FastMove opponentTwoFastMove = opponentTwo.getSelectedFastMove();
        ChargedMove opponentTwoChargedMove = opponentTwo.getSelectedChargedMove();
        int opponentTwoChargedMoveEnergyCost = opponentTwoChargedMove.getEnergyPvP();
        int opponentTwoFastMoveEnergyGain = opponentTwoFastMove.getEnergyPvP();
        int opponentTwoFastMoveDuration = opponentTwoFastMove.getDuration();
        int opponentTwoTurnCounter = 1;

        battleSimulatorReport.addPokemonDetailsToLog(currentTurnCounter, opponentOne, opponentTwo);

        boolean chargeMoveIntermissionState = false;

        while (currentTurnCounter <= MAXIMUM_TURNS) {
            while (!chargeMoveIntermissionState) {
                if (checkActionAvailable(currentTurnCounter, opponentOneTurnCounter)) {
                    if (checkChargedMoveAvailable(opponentOneEnergy, opponentOneChargedMoveEnergyCost)) {
                        opponentTwoStamina -= calculateChargeMoveDamage(opponentOne, opponentTwo);
                        opponentOneEnergy -= opponentOneChargedMoveEnergyCost;
                        opponentOneTurnCounter += CHARGE_UP_DURATION;
                        opponentTwoTurnCounter = opponentOneTurnCounter;
                        chargeMoveIntermissionState = true;
                        battleSimulatorReport.addPokemonUsedChargedMove(currentTurnCounter, 1, opponentOne);
                    } else {
                        opponentTwoStamina -= calculateFastMoveDamage(opponentOne, opponentTwo);
                        opponentOneEnergy += opponentOneFastMoveEnergyGain;
                        opponentOneTurnCounter += opponentOneFastMoveDuration;
                        battleSimulatorReport.addPokemonUserFastMove(currentTurnCounter, 1, opponentOne);
                    }
                } else {
                    battleSimulatorReport.addPokemonWaitingForFastMoveIntermission(currentTurnCounter, 1, opponentOne);
                }

                //break if opponent one has won
                if (!(opponentTwoStamina > 0)) {
                    battleSimulatorReport.addPokemonWinner(currentTurnCounter, 1);
                    battleSimulatorReport.setOutput();

                    return 0;
                }

                if (checkActionAvailable(currentTurnCounter, opponentTwoTurnCounter)) {
                    if (checkChargedMoveAvailable(opponentTwoEnergy, opponentTwoChargedMoveEnergyCost)) {
                        opponentOneStamina -= calculateChargeMoveDamage(opponentTwo, opponentOne);
                        opponentTwoEnergy -= opponentTwoChargedMoveEnergyCost;
                        opponentTwoTurnCounter += CHARGE_UP_DURATION;
                        opponentOneTurnCounter += opponentTwoTurnCounter;
                        chargeMoveIntermissionState = true;
                        battleSimulatorReport.addPokemonUsedChargedMove(currentTurnCounter, 2, opponentTwo);
                    } else {
                        opponentOneStamina -= calculateFastMoveDamage(opponentTwo, opponentOne);
                        opponentTwoEnergy += opponentTwoFastMoveEnergyGain;
                        opponentTwoTurnCounter += opponentTwoFastMoveDuration;
                        battleSimulatorReport.addPokemonUserFastMove(currentTurnCounter, 2, opponentTwo);
                    }
                } else {
                    battleSimulatorReport.addPokemonWaitingForFastMoveIntermission(currentTurnCounter, 2, opponentTwo);
                }

                //break if opponent two has won
                if (!(opponentOneStamina > 0)) {
                    battleSimulatorReport.addPokemonWinner(currentTurnCounter, 2);
                    battleSimulatorReport.setOutput();

                    return 0;
                }

                currentTurnCounter++;
            }
            while (chargeMoveIntermissionState) {

                if (checkActionAvailable(currentTurnCounter, opponentOneTurnCounter) && checkActionAvailable(currentTurnCounter, opponentTwoTurnCounter)) {
                    chargeMoveIntermissionState = false;
                } else {
                    battleSimulatorReport.addPokemonWaitingForChargedMoveIntermission(currentTurnCounter, 1, opponentOne);
                    battleSimulatorReport.addPokemonWaitingForChargedMoveIntermission(currentTurnCounter, 2, opponentTwo);

                    currentTurnCounter++;
                }
            }
        }
        return 0;
    }

    private boolean checkActionAvailable(int currentTurnCounter, int pokemonContextTurnCounter) {
        return currentTurnCounter == pokemonContextTurnCounter;
    }

    private boolean checkChargedMoveAvailable(int currentEnergy, int chargedMoveEnergyCost) {
        return currentEnergy >= chargedMoveEnergyCost;
    }

    private double calculateFastMoveDamage(SelectedPokemon attackerPokemon, SelectedPokemon defenderPokemon) {
        FastMove fastMove = attackerPokemon.getSelectedFastMove();
        return calculateDamage(attackerPokemon, defenderPokemon, fastMove.getDamagePvP(), fastMove.getType());
    }

    private double calculateChargeMoveDamage(SelectedPokemon attackerPokemon, SelectedPokemon defenderPokemon) {
        ChargedMove chargeMove = attackerPokemon.getSelectedChargedMove();
        return calculateDamage(attackerPokemon, defenderPokemon, chargeMove.getDamagePvP(), chargeMove.getType());
    }

    private double calculateDamage(SelectedPokemon attackerPokemon, SelectedPokemon defenderPokemon, double moveDamagePvP, Type moveType) {
        double attackerAttackStat = SelectedPokemonService.getAttackStat(attackerPokemon);
        double defenderDefenseStat = SelectedPokemonService.getDefenseStat(defenderPokemon);
        double attackByDefenseMultiplier = attackerAttackStat / defenderDefenseStat;
        Type selectedPokemonType = attackerPokemon.getBasePokemon().getPokemonType();
        Type selectedPokemonDualType = attackerPokemon.getBasePokemon().getPokemonDualType();
        Type defenderPokemonType = defenderPokemon.getBasePokemon().getPokemonType();
//        TO-DO Defender DualType Functionality
//        Type defenderPokemonDualType = defenderPokemon.getBasePokemon().getPokemonDualType();

        //Apply base multiplier
        moveDamagePvP = moveDamagePvP * BASE_DAMAGE_MULTIPLIER;

        //Apply attack by defense multiplier
        moveDamagePvP = moveDamagePvP * attackByDefenseMultiplier;

        //Apply STAB bonus
        if (moveType == selectedPokemonType || moveType == selectedPokemonDualType) {
            moveDamagePvP = moveDamagePvP * STAB_MULTIPLIER;
        }

        //Apply resistance multiplier
        if (defenderPokemonType.getResistanceMap().get("immunity").contains(moveType.getTypeName())) {
            moveDamagePvP = moveDamagePvP * IMMUNITY_MULTIPLIER;
        } else if (defenderPokemonType.getResistanceMap().get("resistance").contains(moveType.getTypeName())) {
            moveDamagePvP = moveDamagePvP * RESISTANCE_MULTIPLIER;
        } else if (defenderPokemonType.getResistanceMap().get("weakness").contains(moveType.getTypeName())) {
            moveDamagePvP = moveDamagePvP * WEAKNESS_MULTIPLIER;
        }

        return Math.floor(moveDamagePvP) + 1;
    }
}
