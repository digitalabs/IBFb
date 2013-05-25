package org.cimmyt.cril.ibwb.provider.utils;

import org.apache.commons.lang.ArrayUtils;

public class ChadoSchemaUtil {
    public static final String STUDY = "STUDY";
    public static final String STUDY_NAME = "STUDY NAME";
    
    public static final String TRIAL_INSTANCE = "TRIALINSTANCE"; 
    public static final String TRIAL_INSTANCE_NUMBER = "TRIALINSTANCENUMBER"; 
    
    public static final String NURSERY = "NURSERY";
    public static final String NURSERY_NUMBER = "NURSERYNUMBER";
    
    public static final String GERMPLASM_ENTRY = "GERMPLASMENTRY";
    public static final String GERMPLASM_ENTRY_NUMBER = "GERMPLASMENTRYNUMBER";
    public static final String GERMPLASM_ENTRY_NESTED_NUMBER = "GERMPLASMENTRYNESTEDNUMBER";
    public static final String GERMPLASM_ENTRY_CODE = "GERMPLASMENTRYCODE";
    
    public static final String GERMPLASM_ID = "GERMPLASM ID";
    public static final String GERMPLASM_ID_DBCV = "GERMPLASMIDDBCV";
    public static final String GERMPLASM_ID_DBID = "GERMPLASMIDDBID";
    
    public static final String CROSS_HISTORY = "CROSSHISTORY";
    public static final String CROSS_HISTORY_NAME = "CROSSHISTORYNAME";
    
    public static final String SEED_SOURCE = "SEEDSOURCE";
    public static final String SEED_SOURCE_NAME = "SEEDSOURCENAME";
    
    public static final String FIELD_PLOT = "FIELDPLOT";
    public static final String FIELD_PLOT_NUMBER = "FIELDPLOTNUMBER";
    public static final String FIELD_PLOT_NESTED_NUMBER = "FIELDPLOTNEUPSTEDNUMBER";
        
    public static final String REPLICATION_FACTOR = "REPLICATIONFACTOR";
    public static final String REPLICATION_FACTOR_NUMBER = "REPLICATIONFACTORNUMBER";
    
    public static final String BLOCKING_FACTOR = "BLOCKINGFACTOR";
    public static final String BLOCKING_FACTOR_NUMBER = "BLOCKINGFACTORNUMBER";
    
    public static final String ROW_IN_LAYOUT = "ROWINLAYOUT";
    public static final String ROW_IN_LAYOUT_NUMBER = "ROWINLAYOUTNUMBER";
    
    public static final String COLUMN_IN_LAYOUT = "COLUMNINLAYOUT";
    public static final String COLUMN_IN_LAYOUT_NUMBER = "COLUMNINLAYOUTNUMBER";
    
    public static final String[] TRIAL_ENVT_PROPS = {TRIAL_INSTANCE, NURSERY_NUMBER};
    public static final String[] TRIAL_DESIGN_PROPS = {FIELD_PLOT, REPLICATION_FACTOR, BLOCKING_FACTOR};
    public static final String[] GERMPLASM_PROPS = {GERMPLASM_ENTRY, GERMPLASM_ID};
    
    public static final int STUDY_VAR_TYPE = 1010;
    public static final int DATASET_VAR_TYPE = 1015;
    public static final int TRIAL_ENVT_VAR_TYPE = 1020;
    public static final int TRIAL_DESIGN_VAR_TYPE = 1030;
    public static final int GERMPLASM_VAR_TYPE = 1040;
    public static final int OBSERVATION_VARIATE_TYPE = 1043;
    
     public static final int DEFAULT_TRAIT_GROUP = 1050;
    
    
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
