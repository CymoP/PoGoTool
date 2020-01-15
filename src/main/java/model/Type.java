package model;

import utils.BiHashMap;

import java.util.List;
import java.util.Map;

public class Type {

    private BiHashMap<String, String, List<String>> typeMap;

    public Type(String typeName, Map<String, List<String>> resistanceMap){}
}
