package utils;

import java.util.HashMap;
import java.util.Map;

public class ColourChooser {

    private static Map<String, String> colourMap = new HashMap<>();
    private static ColourChooser instance = null;

    /**
     * Source:-  http://www.epidemicjohto.com/t882-type-colors-hex-colors
     */
    private ColourChooser() {
        colourMap = new HashMap<>();
        colourMap.put("normal", "#A8A77A");
        colourMap.put("fire", "#EE8130");
        colourMap.put("water", "#6390F0");
        colourMap.put("electric", "#F7D02C");
        colourMap.put("grass", "#7AC74C");
        colourMap.put("ice", "#96D9D6");
        colourMap.put("fighting", "#C22E28");
        colourMap.put("poison", "#A33EA1");
        colourMap.put("ground", "#E2BF65");
        colourMap.put("flying", "#A98FF3");
        colourMap.put("psychic", "#F95587");
        colourMap.put("bug", "#A6B91A");
        colourMap.put("rock", "#B6A136");
        colourMap.put("ghost", "#735797");
        colourMap.put("dragon", "#6F35FC");
        colourMap.put("dark", "#705746");
        colourMap.put("steel", "#B7B7CE");
        colourMap.put("fairy", "#D685AD");
    }

    public static ColourChooser getInstance() {
        if (instance == null) {
            instance = new ColourChooser();
        }

        return instance;
    }


    public String chooseColour(String typeName) {
        return colourMap.get(typeName);
    }
}
