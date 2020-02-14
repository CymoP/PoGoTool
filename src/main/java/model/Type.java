package model;

import java.util.List;
import java.util.Map;

/**
 * Type POJO for details regarding pokemon types
 */
public class Type {

    private String typeName;
    private Map<String, List<String>> resistanceMap;

    public Type(String typeName, Map<String, List<String>> resistanceMap) {
        this.typeName = typeName;
        this.resistanceMap = resistanceMap;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Map<String, List<String>> getResistanceMap() {
        return resistanceMap;
    }

    public void setResistanceMap(Map<String, List<String>> resistanceMap) {
        this.resistanceMap = resistanceMap;
    }
}
