package services;

import builder.TypeBuilder;
import utils.BiHashMap;

import java.util.List;
import java.util.Map;

public class TypeService {

    private static final String RESISTANCE = "resistance";
    private static final String IMMUNITY = "immunity";
    private static TypeService instance;
    private static BiHashMap<String, String, List<String>> typeMap;

    private TypeService() {
        typeMap = TypeBuilder.buildTypeMap();
    }

    /**
     * Singleton implementation - If instance exists use that, if not create one
     *
     * @return Database connection instance
     */
    public static TypeService getInstance() {
        if (instance == null) {
            instance = new TypeService();
        }

        return instance;
    }

    public Map<String, List<String>> getType(String typeName) {
        return typeMap.get(typeName);
    }

    public List<String> getTypeResistanceList(String typeName) {
        return typeMap.get(typeName, RESISTANCE);
    }

    public List<String> getTypeImmunityList(String typeName) {
        return typeMap.get(typeName, IMMUNITY);
    }
}
