package org.cimmyt.cril.ibwb.provider.utils;

import org.apache.commons.lang.ArrayUtils;

public class ChadoSchemaUtil {
    public static final String STUDY = "STUDY";
    public static final String STUDY_NAME = "STUDY NAME";
    
    public static final String TRIAL_INSTANCE = "TRIAL INSTANCE"; 
    public static final String NURSERY = "NURSERY";
    
    public static final String GERMPLASM_ENTRY = "GERMPLASM ENTRY";
    public static final String GERMPLASM_ID = "GERMPLASM ID";
    
    public static final String FIELD_PLOT = "FIELD PLOT";
    public static final String REPLICATION_FACTOR = "REPLICATION FACTOR";
    public static final String BLOCKING_FACTOR = "BLOCKING FACTOR";
    
    public static final String ROW_IN_LAYOUT = "ROW IN LAYOUT";
    public static final String COLUMN_IN_LAYOUT = "COLUMN IN LAYOUT";
    
    public static final String[] TRIAL_ENVT_PROPS = {TRIAL_INSTANCE, NURSERY};
    public static final String[] TRIAL_DESIGN_PROPS = {FIELD_PLOT, REPLICATION_FACTOR, BLOCKING_FACTOR};
    public static final String[] GERMPLASM_PROPS = {GERMPLASM_ENTRY, GERMPLASM_ID};
    
    public static final int STUDY_VAR_TYPE = 1010;
    public static final int DATASET_VAR_TYPE = 1015;
    public static final int TRIAL_ENVT_VAR_TYPE = 1020;
    public static final int TRIAL_DESIGN_VAR_TYPE = 1030;
    public static final int GERMPLASM_VAR_TYPE = 1040;
    public static final int OBSERVATION_VARIATE_TYPE = 1043;
    
    
    public static int getStoredInVariableType(String property){
        int varType = TRIAL_DESIGN_VAR_TYPE;
        String compareStr = property.trim().toUpperCase();
        
        if (ArrayUtils.contains(TRIAL_ENVT_PROPS, compareStr)){
            varType = TRIAL_ENVT_VAR_TYPE;
            
        } else if (ArrayUtils.contains(TRIAL_DESIGN_PROPS, compareStr)){
            varType = TRIAL_DESIGN_VAR_TYPE;
            
        } else if (ArrayUtils.contains(GERMPLASM_PROPS, compareStr)){
            varType = GERMPLASM_VAR_TYPE;
        }
               
        return varType;
    }
    
}
