/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

/**
 *
 * @author tippsgo
 */
public enum TableEnum {
 
    EXPERIMENT_PROJECT("nd_experiment_project");
    
    private String tableName;
    
    private TableEnum(String tableName) {
        this.tableName = tableName;
    }
    
    public String getName() {
        return tableName;
    }
}
