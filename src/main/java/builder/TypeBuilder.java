package builder;

import utils.BiHashMap;

import java.util.ArrayList;
import java.util.List;

public class TypeBuilder {

    private static final String RESISTANCE = "resistance";
    private static final String IMMUNITY = "immunity";
    private static final String WEAKNESS = "weakness";
    private static final String TYPE_NAME_NORMAL = "normal";
    private static final String TYPE_NAME_FIGHTING = "fighting";
    private static final String TYPE_NAME_FLYING = "flying";
    private static final String TYPE_NAME_POISON = "poison";
    private static final String TYPE_NAME_GROUND = "ground";
    private static final String TYPE_NAME_ROCK = "rock";
    private static final String TYPE_NAME_BUG = "bug";
    private static final String TYPE_NAME_GHOST = "ghost";
    private static final String TYPE_NAME_STEEL = "steel";
    private static final String TYPE_NAME_FIRE = "fire";
    private static final String TYPE_NAME_WATER = "water";
    private static final String TYPE_NAME_GRASS = "grass";
    private static final String TYPE_NAME_ELECTRIC = "electric";
    private static final String TYPE_NAME_PSYCHIC = "psychic";
    private static final String TYPE_NAME_ICE = "ice";
    private static final String TYPE_NAME_DRAGON = "dragon";
    private static final String TYPE_NAME_DARK = "dark";
    private static final String TYPE_NAME_FAIRY = "fairy";
    private static BiHashMap<String, String, List<String>> typeMap;

    private TypeBuilder(){}

    public static BiHashMap<String, String, List<String>> buildTypeMap(){
        typeMap = new BiHashMap<>();

        addTypeData(TYPE_NAME_NORMAL, buildNormalTypeResistanceList(), buildNormalTypeImmunityList(), buildNormalTypeWeaknessList());
        addTypeData(TYPE_NAME_FIGHTING, buildFightingTypeResistanceList(), buildFightingTypeImmunityList(), buildFightingTypeWeaknessList());
        addTypeData(TYPE_NAME_FLYING, buildFlyingTypeResistanceList(), buildFlyingTypeImmunityList(), buildFlyingTypeWeaknessList());
        addTypeData(TYPE_NAME_POISON, buildPoisonTypeResistanceList(), buildPoisonTypeImmunityList(), buildPoisonTypeWeaknessList());
        addTypeData(TYPE_NAME_GROUND, buildGroundTypeResistanceList(), buildGroundTypeImmunityList(), buildGroundTypeWeaknessList());
        addTypeData(TYPE_NAME_ROCK, buildRockTypeResistanceList(), buildRockTypeImmunityList(), buildRockTypeWeaknessList());
        addTypeData(TYPE_NAME_BUG, buildBugTypeResistanceList(), buildBugTypeImmunityList(), buildBugTypeWeaknessList());
        addTypeData(TYPE_NAME_GHOST, buildGhostTypeResistanceList(), buildGhostTypeImmunityList(), buildGhostTypeWeaknessList());
        addTypeData(TYPE_NAME_STEEL, buildSteelTypeResistanceList(), buildSteelTypeImmunityList(), buildSteelTypeWeaknessList());
        addTypeData(TYPE_NAME_FIRE, buildFireTypeResistanceList(), buildFireTypeImmunityList(), buildFireTypeWeaknessList());
        addTypeData(TYPE_NAME_WATER, buildWaterTypeResistanceList(), buildWaterTypeImmunityList(), buildWaterTypeWeaknessList());
        addTypeData(TYPE_NAME_GRASS, buildGrassTypeResistanceList(), buildGrassTypeImmunityList(), buildGrassTypeWeaknessList());
        addTypeData(TYPE_NAME_ELECTRIC, buildElectricTypeResistanceList(), buildElectricTypeImmunityList(), buildElectricTypeWeaknessList());
        addTypeData(TYPE_NAME_PSYCHIC, buildPsychicTypeResistanceList(), buildPsychicTypeImmunityList(), buildPsychicTypeWeaknessList());
        addTypeData(TYPE_NAME_ICE, buildIceTypeResistanceList(), buildIceTypeImmunityList(), buildIceTypeWeaknessList());
        addTypeData(TYPE_NAME_DRAGON, buildDragonTypeResistanceList(), buildDragonTypeImmunityList(), buildDragonTypeWeaknessList());
        addTypeData(TYPE_NAME_DARK, buildDarkTypeResistanceList(), buildDarkTypeImmunityList(), buildDarkTypeWeaknessList());
        addTypeData(TYPE_NAME_FAIRY, buildFairyTypeResistanceList(), buildFairyTypeImmunityList(), buildFairyTypeWeaknessList());

        return typeMap;
    }

    private static void addTypeData(String typeName, List<String> resistanceList, List<String> immunityList, List<String> weaknessList) {
        typeMap.put(typeName, RESISTANCE, resistanceList);
        typeMap.put(typeName, IMMUNITY, immunityList);
        typeMap.put(typeName, WEAKNESS, weaknessList);
    }

    private static List<String> buildNormalTypeWeaknessList() {
        List<String> normalTypeWeaknessList = new ArrayList<>();
        normalTypeWeaknessList.add(TYPE_NAME_FIGHTING);

        return normalTypeWeaknessList;
    }

    private static List<String> buildFightingTypeWeaknessList() {
        List<String> fightingTypeWeaknessList = new ArrayList<>();
        fightingTypeWeaknessList.add(TYPE_NAME_FLYING);
        fightingTypeWeaknessList.add(TYPE_NAME_PSYCHIC);
        fightingTypeWeaknessList.add(TYPE_NAME_FAIRY);

        return fightingTypeWeaknessList;
    }

    private static List<String> buildFlyingTypeWeaknessList() {
        List<String> flyingTypeWeaknessList = new ArrayList<>();
        flyingTypeWeaknessList.add(TYPE_NAME_ROCK);
        flyingTypeWeaknessList.add(TYPE_NAME_ELECTRIC);
        flyingTypeWeaknessList.add(TYPE_NAME_ICE);

        return flyingTypeWeaknessList;
    }

    private static List<String> buildPoisonTypeWeaknessList() {
        List<String> poisonTypeWeaknessList = new ArrayList<>();
        poisonTypeWeaknessList.add(TYPE_NAME_GROUND);
        poisonTypeWeaknessList.add(TYPE_NAME_PSYCHIC);

        return poisonTypeWeaknessList;
    }

    private static List<String> buildGroundTypeWeaknessList() {
        List<String> groundTypeWeaknessList = new ArrayList<>();
        groundTypeWeaknessList.add(TYPE_NAME_WATER);
        groundTypeWeaknessList.add(TYPE_NAME_GRASS);
        groundTypeWeaknessList.add(TYPE_NAME_ICE);

        return groundTypeWeaknessList;
    }

    private static List<String> buildRockTypeWeaknessList() {
        List<String> rockTypeWeaknessList = new ArrayList<>();
        rockTypeWeaknessList.add(TYPE_NAME_FIGHTING);
        rockTypeWeaknessList.add(TYPE_NAME_GROUND);
        rockTypeWeaknessList.add(TYPE_NAME_STEEL);
        rockTypeWeaknessList.add(TYPE_NAME_WATER);
        rockTypeWeaknessList.add(TYPE_NAME_GRASS);

        return rockTypeWeaknessList;
    }

    private static List<String> buildBugTypeWeaknessList() {
        List<String> bugTypeWeaknessList = new ArrayList<>();
        bugTypeWeaknessList.add(TYPE_NAME_FLYING);
        bugTypeWeaknessList.add(TYPE_NAME_ROCK);
        bugTypeWeaknessList.add(TYPE_NAME_FIRE);

        return bugTypeWeaknessList;
    }

    private static List<String> buildGhostTypeWeaknessList() {
        List<String> ghostTypeWeaknessList = new ArrayList<>();
        ghostTypeWeaknessList.add(TYPE_NAME_GHOST);
        ghostTypeWeaknessList.add(TYPE_NAME_DARK);

        return ghostTypeWeaknessList;
    }

    private static List<String> buildSteelTypeWeaknessList() {
        List<String> steelTypeWeaknessList = new ArrayList<>();
        steelTypeWeaknessList.add(TYPE_NAME_FIGHTING);
        steelTypeWeaknessList.add(TYPE_NAME_GROUND);
        steelTypeWeaknessList.add(TYPE_NAME_FIRE);

        return steelTypeWeaknessList;
    }

    private static List<String> buildFireTypeWeaknessList() {
        List<String> fireTypeWeaknessList = new ArrayList<>();
        fireTypeWeaknessList.add(TYPE_NAME_GROUND);
        fireTypeWeaknessList.add(TYPE_NAME_ROCK);
        fireTypeWeaknessList.add(TYPE_NAME_WATER);

        return fireTypeWeaknessList;
    }

    private static List<String> buildWaterTypeWeaknessList() {
        List<String> waterTypeWeaknessList = new ArrayList<>();
        waterTypeWeaknessList.add(TYPE_NAME_GRASS);
        waterTypeWeaknessList.add(TYPE_NAME_ELECTRIC);

        return waterTypeWeaknessList;
    }

    private static List<String> buildGrassTypeWeaknessList() {
        List<String> grassTypeWeaknessList = new ArrayList<>();
        grassTypeWeaknessList.add(TYPE_NAME_FLYING);
        grassTypeWeaknessList.add(TYPE_NAME_POISON);
        grassTypeWeaknessList.add(TYPE_NAME_BUG);
        grassTypeWeaknessList.add(TYPE_NAME_FIRE);
        grassTypeWeaknessList.add(TYPE_NAME_ICE);

        return grassTypeWeaknessList;
    }

    private static List<String> buildElectricTypeWeaknessList() {
        List<String> electricTypeWeaknessList = new ArrayList<>();
        electricTypeWeaknessList.add(TYPE_NAME_GROUND);

        return electricTypeWeaknessList;
    }

    private static List<String> buildPsychicTypeWeaknessList() {
        List<String> psychicTypeWeaknessList = new ArrayList<>();
        psychicTypeWeaknessList.add(TYPE_NAME_BUG);
        psychicTypeWeaknessList.add(TYPE_NAME_GHOST);
        psychicTypeWeaknessList.add(TYPE_NAME_DARK);

        return psychicTypeWeaknessList;
    }

    private static List<String> buildIceTypeWeaknessList() {
        List<String> iceTypeWeaknessList = new ArrayList<>();
        iceTypeWeaknessList.add(TYPE_NAME_FIGHTING);
        iceTypeWeaknessList.add(TYPE_NAME_ROCK);
        iceTypeWeaknessList.add(TYPE_NAME_STEEL);
        iceTypeWeaknessList.add(TYPE_NAME_FIRE);

        return iceTypeWeaknessList;
    }

    private static List<String> buildDragonTypeWeaknessList() {
        List<String> dragonTypeWeaknessList = new ArrayList<>();
        dragonTypeWeaknessList.add(TYPE_NAME_ICE);
        dragonTypeWeaknessList.add(TYPE_NAME_DRAGON);
        dragonTypeWeaknessList.add(TYPE_NAME_FAIRY);

        return dragonTypeWeaknessList;
    }

    private static List<String> buildDarkTypeWeaknessList() {
        List<String> darkTypeWeaknessList = new ArrayList<>();
        darkTypeWeaknessList.add(TYPE_NAME_FIGHTING);
        darkTypeWeaknessList.add(TYPE_NAME_BUG);
        darkTypeWeaknessList.add(TYPE_NAME_FAIRY);

        return darkTypeWeaknessList;
    }

    private static List<String> buildFairyTypeWeaknessList() {
        List<String> fairyTypeWeaknessList = new ArrayList<>();
        fairyTypeWeaknessList.add(TYPE_NAME_POISON);
        fairyTypeWeaknessList.add(TYPE_NAME_STEEL);

        return fairyTypeWeaknessList;
    }

    private static List<String> buildFairyTypeResistanceList() {
        List<String> fairyTypeImmunityList = new ArrayList<>();
        fairyTypeImmunityList.add(TYPE_NAME_DRAGON);

        return fairyTypeImmunityList;
    }

    private static List<String> buildFairyTypeImmunityList() {
        List<String> fairyTypeResistanceList = new ArrayList<>();
        fairyTypeResistanceList.add(TYPE_NAME_FIGHTING);
        fairyTypeResistanceList.add(TYPE_NAME_BUG);
        fairyTypeResistanceList.add(TYPE_NAME_DARK);

        return fairyTypeResistanceList;
    }

    private static List<String> buildDarkTypeImmunityList() {
        List<String> darkTypeImmunityList = new ArrayList<>();
        darkTypeImmunityList.add(TYPE_NAME_PSYCHIC);

        return darkTypeImmunityList;
    }

    private static List<String> buildDarkTypeResistanceList() {
        List<String> darkTypeResistanceList = new ArrayList<>();
        darkTypeResistanceList.add(TYPE_NAME_DARK);
        darkTypeResistanceList.add(TYPE_NAME_GHOST);

        return darkTypeResistanceList;
    }

    private static List<String> buildDragonTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildDragonTypeResistanceList() {
        List<String> dragonTypeResistanceList = new ArrayList<>();
        dragonTypeResistanceList.add(TYPE_NAME_FIRE);
        dragonTypeResistanceList.add(TYPE_NAME_WATER);
        dragonTypeResistanceList.add(TYPE_NAME_GRASS);
        dragonTypeResistanceList.add(TYPE_NAME_ELECTRIC);

        return dragonTypeResistanceList;
    }

    private static List<String> buildIceTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildIceTypeResistanceList() {
        List<String> iceTypeResistanceList = new ArrayList<>();
        iceTypeResistanceList.add(TYPE_NAME_ICE);

        return iceTypeResistanceList;
    }

    private static List<String> buildPsychicTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildPsychicTypeResistanceList() {
        List<String> psychicTypeResistanceList = new ArrayList<>();
        psychicTypeResistanceList.add(TYPE_NAME_FIGHTING);
        psychicTypeResistanceList.add(TYPE_NAME_PSYCHIC);

        return psychicTypeResistanceList;
    }

    private static List<String> buildElectricTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildElectricTypeResistanceList() {
        List<String> electricTypeResistanceList = new ArrayList<>();
        electricTypeResistanceList.add(TYPE_NAME_FLYING);
        electricTypeResistanceList.add(TYPE_NAME_STEEL);
        electricTypeResistanceList.add(TYPE_NAME_ELECTRIC);

        return electricTypeResistanceList;
    }

    private static List<String> buildGrassTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildGrassTypeResistanceList() {
        List<String> grassTypeResistanceList = new ArrayList<>();
        grassTypeResistanceList.add(TYPE_NAME_GROUND);
        grassTypeResistanceList.add(TYPE_NAME_WATER);
        grassTypeResistanceList.add(TYPE_NAME_GRASS);
        grassTypeResistanceList.add(TYPE_NAME_ELECTRIC);

        return grassTypeResistanceList;
    }

    private static List<String> buildWaterTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildWaterTypeResistanceList() {
        List<String> waterTypeResistanceList = new ArrayList<>();
        waterTypeResistanceList.add(TYPE_NAME_STEEL);
        waterTypeResistanceList.add(TYPE_NAME_FIRE);
        waterTypeResistanceList.add(TYPE_NAME_WATER);
        waterTypeResistanceList.add(TYPE_NAME_ICE);

        return waterTypeResistanceList;
    }

    private static List<String> buildFireTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildFireTypeResistanceList() {
        List<String> fireTypeResistanceList = new ArrayList<>();
        fireTypeResistanceList.add(TYPE_NAME_STEEL);
        fireTypeResistanceList.add(TYPE_NAME_BUG);
        fireTypeResistanceList.add(TYPE_NAME_FIRE);
        fireTypeResistanceList.add(TYPE_NAME_GRASS);
        fireTypeResistanceList.add(TYPE_NAME_FAIRY);
        fireTypeResistanceList.add(TYPE_NAME_ICE);
        return fireTypeResistanceList;
    }

    private static List<String> buildSteelTypeImmunityList() {
        List<String> steelTypeImmunityList = new ArrayList<>();
        steelTypeImmunityList.add(TYPE_NAME_POISON);

        return steelTypeImmunityList;
    }

    private static List<String> buildSteelTypeResistanceList() {
        List<String> steelTypeResistanceList = new ArrayList<>();
        steelTypeResistanceList.add(TYPE_NAME_NORMAL);
        steelTypeResistanceList.add(TYPE_NAME_FLYING);
        steelTypeResistanceList.add(TYPE_NAME_ROCK);
        steelTypeResistanceList.add(TYPE_NAME_BUG);
        steelTypeResistanceList.add(TYPE_NAME_STEEL);
        steelTypeResistanceList.add(TYPE_NAME_GRASS);
        steelTypeResistanceList.add(TYPE_NAME_PSYCHIC);
        steelTypeResistanceList.add(TYPE_NAME_ICE);
        steelTypeResistanceList.add(TYPE_NAME_DRAGON);
        steelTypeResistanceList.add(TYPE_NAME_FAIRY);

        return steelTypeResistanceList;
    }

    private static List<String> buildGhostTypeImmunityList() {
        List<String> ghostTypeImmunityList = new ArrayList<>();
        ghostTypeImmunityList.add(TYPE_NAME_NORMAL);
        ghostTypeImmunityList.add(TYPE_NAME_FIGHTING);

        return ghostTypeImmunityList;
    }

    private static List<String> buildGhostTypeResistanceList() {
        List<String> ghostTypeResistanceList = new ArrayList<>();
        ghostTypeResistanceList.add(TYPE_NAME_POISON);
        ghostTypeResistanceList.add(TYPE_NAME_BUG);

        return ghostTypeResistanceList;
    }

    private static List<String> buildBugTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildBugTypeResistanceList() {
        List<String> bugTypeResistanceList = new ArrayList<>();
        bugTypeResistanceList.add(TYPE_NAME_FIGHTING);
        bugTypeResistanceList.add(TYPE_NAME_GROUND);
        bugTypeResistanceList.add(TYPE_NAME_GRASS);

        return bugTypeResistanceList;
    }

    private static List<String> buildRockTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildRockTypeResistanceList() {
        List<String> rockTypeResistanceList = new ArrayList<>();
        rockTypeResistanceList.add(TYPE_NAME_NORMAL);
        rockTypeResistanceList.add(TYPE_NAME_FLYING);
        rockTypeResistanceList.add(TYPE_NAME_POISON);
        rockTypeResistanceList.add(TYPE_NAME_FIRE);

        return rockTypeResistanceList;
    }

    private static List<String> buildGroundTypeImmunityList() {
        List<String> groundTypeImmunityList = new ArrayList<>();
        groundTypeImmunityList.add(TYPE_NAME_ELECTRIC);

        return groundTypeImmunityList;
    }

    private static List<String> buildGroundTypeResistanceList() {
        List<String> poisonTypeResistanceList = new ArrayList<>();
        poisonTypeResistanceList.add(TYPE_NAME_POISON);
        poisonTypeResistanceList.add(TYPE_NAME_ROCK);

        return poisonTypeResistanceList;
    }

    private static List<String> buildPoisonTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildPoisonTypeResistanceList() {
        List<String> poisonTypeResistanceList = new ArrayList<>();
        poisonTypeResistanceList.add(TYPE_NAME_FIGHTING);
        poisonTypeResistanceList.add(TYPE_NAME_ROCK);
        poisonTypeResistanceList.add(TYPE_NAME_GRASS);
        poisonTypeResistanceList.add(TYPE_NAME_FAIRY);

        return poisonTypeResistanceList;
    }

    private static List<String> buildFlyingTypeImmunityList() {
        List<String> flyingTypeImmunityList = new ArrayList<>();
        flyingTypeImmunityList.add(TYPE_NAME_GROUND);

        return flyingTypeImmunityList;
    }

    private static List<String> buildFlyingTypeResistanceList() {
        List<String> flyingTypeResistanceList = new ArrayList<>();
        flyingTypeResistanceList.add(TYPE_NAME_FIGHTING);
        flyingTypeResistanceList.add(TYPE_NAME_BUG);
        flyingTypeResistanceList.add(TYPE_NAME_GRASS);

        return flyingTypeResistanceList;
    }

    private static List<String> buildFightingTypeImmunityList() {
        return new ArrayList<>();
    }

    private static List<String> buildFightingTypeResistanceList() {
        List<String> fightingTypeResistanceList = new ArrayList<>();
        fightingTypeResistanceList.add(TYPE_NAME_ROCK);
        fightingTypeResistanceList.add(TYPE_NAME_BUG);
        fightingTypeResistanceList.add(TYPE_NAME_DARK);

        return fightingTypeResistanceList;
    }

    private static List<String> buildNormalTypeImmunityList() {
        List<String> normalTypeResistanceList = new ArrayList<>();
        normalTypeResistanceList.add(TYPE_NAME_GHOST);

        return normalTypeResistanceList;
    }

    private static List<String> buildNormalTypeResistanceList() {
        return new ArrayList<>();
    }
}
