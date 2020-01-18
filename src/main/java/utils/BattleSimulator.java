package utils;

import model.ChargedMove;
import model.FastMove;
import model.SelectedPokemon;
import model.Type;
import services.SelectedPokemonService;

import java.util.HashMap;
import java.util.Map;

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

    public int simulatorAlgorithm(SelectedPokemon opponentOne, SelectedPokemon opponentTwo) {

        Map<Integer, String> battleSimulatorLog = new HashMap<>();

        int currentTurnCounter = 0;

        double opponentOneStamina = SelectedPokemonService.getStaminaStat(opponentOne);
        int opponentOneEnergy = 0;

        double opponentTwoStamina = SelectedPokemonService.getStaminaStat(opponentTwo);
        int opponentTwoEnergy = 0;

        FastMove opponentOneFastMove = opponentOne.getSelectedFastMove();
        ChargedMove opponentOneChargedMove = opponentOne.getSelectedChargedMove();
        int opponentOneChargedMoveEnergyCost = opponentOneChargedMove.getEnergyPvP();
        int opponentOneFastMoveEnergyGain = opponentOneFastMove.getEnergyPvP();
        int opponentOneFastMoveDuration = opponentOneFastMove.getDuration();
        int opponentOneTurnCounter = 0;

        FastMove opponentTwoFastMove = opponentTwo.getSelectedFastMove();
        ChargedMove opponentTwoChargedMove = opponentTwo.getSelectedChargedMove();
        int opponentTwoChargedMoveEnergyCost = opponentTwoChargedMove.getEnergyPvP();
        int opponentTwoFastMoveEnergyGain = opponentTwoFastMove.getEnergyPvP();
        int opponentTwoFastMoveDuration = opponentTwoFastMove.getDuration();
        int opponentTwoTurnCounter = 0;

        int logCounter = 0;

        battleSimulatorLog.put(logCounter, "opponentOne - " + "Pokemon Name - " + opponentOne.getBasePokemon().getPokemonName());
        battleSimulatorLog.put(++logCounter, "opponentOne - " + "Pokemon Attack Stat - " + SelectedPokemonService.getAttackStat(opponentOne));
        battleSimulatorLog.put(++logCounter, "opponentOne - " + "Pokemon Defense Stat - " + SelectedPokemonService.getDefenseStat(opponentOne));
        battleSimulatorLog.put(++logCounter, "opponentOne - " + "Pokemon Stamina Stat - " + SelectedPokemonService.getStaminaStat(opponentOne));
        battleSimulatorLog.put(++logCounter, "opponentOne - " + "Pokemon Fast Move - " + opponentOne.getSelectedFastMove().getMoveName());
        battleSimulatorLog.put(++logCounter, "opponentOne - " + "Pokemon Charge Move - " + opponentOne.getSelectedChargedMove().getMoveName());

        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Name - " + opponentTwo.getBasePokemon().getPokemonName());
        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Attack Stat - " + SelectedPokemonService.getAttackStat(opponentTwo));
        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Defense Stat - " + SelectedPokemonService.getDefenseStat(opponentTwo));
        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Stamina Stat - " + SelectedPokemonService.getStaminaStat(opponentTwo));
        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Fast Move - " + opponentTwo.getSelectedFastMove().getMoveName());
        battleSimulatorLog.put(++logCounter, "opponentTwo - " + "Pokemon Charge Move - " + opponentTwo.getSelectedChargedMove().getMoveName());

        while (currentTurnCounter <= MAXIMUM_TURNS) {
            if (currentTurnCounter == opponentOneTurnCounter) {
                if (opponentOneChargedMoveEnergyCost == opponentOneEnergy) {
                    opponentTwoStamina -= calculateChargeMoveDamage(opponentOne, opponentTwo);
                    opponentOneEnergy -= opponentOneChargedMoveEnergyCost;
                    opponentOneTurnCounter += CHARGE_UP_DURATION;
                    battleSimulatorLog.put(++logCounter, "opponentOne - " + opponentOne.getBasePokemon().getPokemonName() + " used " + opponentOneChargedMove.getMoveName());
                } else {
                    opponentTwoStamina -= calculateFastMoveDamage(opponentOne, opponentTwo);
                    opponentOneEnergy += opponentOneFastMoveEnergyGain;
                    opponentOneTurnCounter += opponentOneFastMoveDuration;
                    battleSimulatorLog.put(++logCounter, "opponentOne - " + opponentOne.getBasePokemon().getPokemonName() + " used " + opponentOneFastMove.getMoveName());
                }
            }

            if (!(opponentTwoStamina > 0)) {
                break;
            }

            if (currentTurnCounter == opponentTwoTurnCounter) {
                if (opponentTwoChargedMoveEnergyCost == opponentTwoEnergy) {
                    opponentOneStamina -= calculateChargeMoveDamage(opponentTwo, opponentOne);
                    opponentTwoEnergy -= opponentTwoChargedMoveEnergyCost;
                    opponentTwoTurnCounter += CHARGE_UP_DURATION;
                    battleSimulatorLog.put(++logCounter, "opponentTwo - " + opponentTwo.getBasePokemon().getPokemonName() + " used " + opponentTwoFastMove.getMoveName());
                } else {
                    opponentOneStamina -= calculateFastMoveDamage(opponentTwo, opponentOne);
                    opponentTwoEnergy += opponentTwoFastMoveEnergyGain;
                    opponentTwoTurnCounter += opponentTwoFastMoveDuration;
                    battleSimulatorLog.put(++logCounter, "opponentTwo - " + opponentTwo.getBasePokemon().getPokemonName() + " used " + opponentTwoChargedMove.getMoveName());
                }
            }

            if (!(opponentOneStamina > 0)) {
                break;
            }

            currentTurnCounter++;
        }

        if (opponentOneStamina > 0) {
            battleSimulatorLog.put(++logCounter, "Winner - Opponent One has won");
        } else {
            battleSimulatorLog.put(++logCounter, "Winner - Opponent Two has won");
        }

        for (int keyCount = 0; keyCount < battleSimulatorLog.size(); keyCount++){
            System.out.println(battleSimulatorLog.get(keyCount));
        }

        return 0;
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
