/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsMethod;
import org.cimmyt.cril.ibwb.domain.Traits;

public class StandardVariableCache {
    private static Map<MeasuredInKey, Measuredin> cache = new HashMap<MeasuredInKey, Measuredin>();
    private static Map<Integer, Traits> traitCache = new HashMap<Integer, Traits>();
    private static Map<Integer, Scales> scaleCache = new HashMap<Integer, Scales>();
    private static Map<Integer, TmsMethod> methodCache = new HashMap<Integer, TmsMethod>();
    private static Set<PhenotypeKey> phenotypeCache = new HashSet<PhenotypeKey>();
    
    public static Measuredin getMeasuredIn(MeasuredInKey key) {
        return cache.get(key);
    }

    public static void putMeasuredIn(MeasuredInKey key, Measuredin value) {
        cache.put(key, value);
    }

    public static Traits getTrait(Integer key) {
        return traitCache.get(key);
    }
    
    public static void putTrait(Integer key, Traits trait) {
        traitCache.put(key, trait);
    }
    
    public static Scales getScale(Integer key) {
        return scaleCache.get(key);
    }
    
    public static void putScale(Integer key, Scales scale) {
        scaleCache.put(key, scale);
    }
    
    public static TmsMethod getMethod(Integer key) {
        return methodCache.get(key);
    }
    
    public static void putMethod(Integer key, TmsMethod method) {
        methodCache.put(key, method);
    }
    
    public static void putPhenotypeKey(PhenotypeKey key){
        phenotypeCache.add(key);
    }
    
    public static boolean phenotypeKeyExists(PhenotypeKey key){
        return phenotypeCache.contains(key);
    }
}
