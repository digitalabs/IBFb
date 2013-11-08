/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class ValidValuesCache {
    
    private static Map<Integer, Set<String>> categoricalMap = new HashMap<Integer, Set<String>>();
    private static Map<Integer, Double[]> numericRangeMap = new HashMap<Integer, Double[]>();
    private static Map<String, Integer> variateMap = new HashMap<String, Integer>();
    private static Map<String, Integer> valuesMap = new HashMap<String, Integer>();

    public static Integer getVariateIdByPsm(String property, String scale, String method) {
        String key = property + "-" + scale + "-" + method;
        return variateMap.get(key);
    }
    
    public static Set<String> getCategoricalValues(Integer varId) {
        return categoricalMap.get(varId);
    }
    
    public static Double[] getNumericRange(Integer varId) {
        return numericRangeMap.get(varId);
    }
    
    public static Integer getValueId(Integer varId, String value) {
        String key = varId.toString() + "--" + value.toLowerCase();
        return valuesMap.get(key);
    }
    
    public static void putVariateIdByPsm(String property, String scale, String method, Integer varId) {
        String key = property + "-" + scale + "-" + method;
        variateMap.put(key, varId);
    }
    
    public static void putCategoricalValues(Integer varId, Set<String> validValues) {
        categoricalMap.put(varId, validValues);
    }
    
    public static void putNumericRange(Integer varId, Double[] range) {
        numericRangeMap.put(varId, range);
    }
    
    public static void putValueId(Integer varId, String value, Integer valueId) {
        String key = varId.toString() + "--" + value.toLowerCase();
        valuesMap.put(key, valueId);
    }
}
