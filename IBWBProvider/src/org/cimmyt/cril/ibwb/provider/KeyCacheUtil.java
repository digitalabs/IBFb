/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class KeyCacheUtil {
    
    private static Map<TableEnum, Integer> cache = new HashMap<TableEnum, Integer>();
    
    public static Integer getKey(TableEnum tableName) {
        return cache.get(tableName);
    }
    
    public static void setKey(TableEnum tableName, Integer key) {
        cache.put(tableName, key);
    }
}
