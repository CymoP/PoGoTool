package services;

import builder.TypeBuilder;
import model.Type;
import utils.BiHashMap;

import java.util.List;

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

    public Type getTypeByTypeName(String typeName) {
        return new Type(typeName, typeMap.get(typeName));
    }

    public List<String> getTypeResistanceList(String typeName) {
        return typeMap.get(typeName, RESISTANCE);
    }

    public List<String> getTypeImmunityList(String typeName) {
        return typeMap.get(typeName, IMMUNITY);
    }
}
