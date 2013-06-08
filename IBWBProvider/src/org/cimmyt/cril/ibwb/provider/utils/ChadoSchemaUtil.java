package org.cimmyt.cril.ibwb.provider.utils;

import ibfb.domain.core.Workbook;
import org.apache.commons.lang.ArrayUtils;

public class ChadoSchemaUtil {
//    public static final String STUDY = "STUDY";
//    public static final String STUDY_NAME = "STUDY NAME";
//    
//    public static final String TRIAL_INSTANCE = "TRIALINSTANCE"; 
//    public static final String TRIAL_INSTANCE_NUMBER = "TRIALINSTANCENUMBER"; 
//    
//    public static final String NURSERY = "NURSERY";
//    public static final String NURSERY_NUMBER = "NURSERYNUMBER";
//    
//    public static final String GERMPLASM_ENTRY = "GERMPLASMENTRY";
//    public static final String GERMPLASM_ENTRY_NUMBER = "GERMPLASMENTRYNUMBER";
//    public static final String GERMPLASM_ENTRY_NESTED_NUMBER = "GERMPLASMENTRYNESTEDNUMBER";
//    public static final String GERMPLASM_ENTRY_CODE = "GERMPLASMENTRYCODE";
//    
//    public static final String GERMPLASM_ID = "GERMPLASM ID";
//    public static final String GERMPLASM_ID_DBCV = "GERMPLASMIDDBCV";
//    public static final String GERMPLASM_ID_DBID = "GERMPLASMIDDBID";
//    
//    public static final String CROSS_HISTORY = "CROSSHISTORY";
//    public static final String CROSS_HISTORY_NAME = "CROSSHISTORYNAME";
//    
//    public static final String SEED_SOURCE = "SEEDSOURCE";
//    public static final String SEED_SOURCE_NAME = "SEEDSOURCENAME";
//    
//    public static final String FIELD_PLOT = "FIELDPLOT";
//    public static final String FIELD_PLOT_NUMBER = "FIELDPLOTNUMBER";
//    public static final String FIELD_PLOT_NESTED_NUMBER = "FIELDPLOTNEUPSTEDNUMBER";
//        
//    public static final String REPLICATION_FACTOR = "REPLICATIONFACTOR";
//    public static final String REPLICATION_FACTOR_NUMBER = "REPLICATIONFACTORNUMBER";
//    
//    public static final String BLOCKING_FACTOR = "BLOCKINGFACTOR";
//    public static final String BLOCKING_FACTOR_NUMBER = "BLOCKINGFACTORNUMBER";
//    
//    public static final String ROW_IN_LAYOUT = "ROWINLAYOUT";
//    public static final String ROW_IN_LAYOUT_NUMBER = "ROWINLAYOUTNUMBER";
//    
//    public static final String COLUMN_IN_LAYOUT = "COLUMNINLAYOUT";
//    public static final String COLUMN_IN_LAYOUT_NUMBER = "COLUMNINLAYOUTNUMBER";
//  
    public static final int STUDY_VAR_TYPE = 1010;
    public static final int DATASET_VAR_TYPE = 1015;
    public static final int TRIAL_ENVT_VAR_TYPE = 1020;
    public static final int TRIAL_DESIGN_VAR_TYPE = 1030;
    public static final int GERMPLASM_VAR_TYPE = 1040;
    public static final int GERMPLASM_ENTRY_NUMBER_VAR_TYPE = 1041;
    public static final int GERMPLASM_GID_VAR_TYPE = 1042;
    public static final int GERMPLASM_DESIG_VAR_TYPE = 1046;
    public static final int GERMPLASM_ENTRY_CODE_VAR_TYPE = 1047;
    
    public static final int OBSERVATION_VARIATE_TYPE = 1043;
    public static final int CATEGORICAL_VARIATE_TYPE = 1048;
    
    public static final int DEFAULT_TRAIT_GROUP = 1050;
    
    public static final String[] TRIAL_ENVT_PROPS = {
        Workbook.TRIAL_INSTANCE, Workbook.NURSERY
    };
    public static final String[] TRIAL_ENVT_PROPS_SCALES = {
        Workbook.TRIAL_INSTANCE_NUMBER, Workbook.NURSERY_NUMBER
    };
    
    public static final String[] TRIAL_DESIGN_PROPS = {
        Workbook.FIELD_PLOT, Workbook.REPLICATION_FACTOR, Workbook.BLOCKING_FACTOR, Workbook.REPLICATION,  Workbook.BLOCK
    };
    public static final String[] TRIAL_DESIGN_PROPS_SCALES = {
        Workbook.FIELD_PLOT_NUMBER, Workbook.FIELD_PLOT_NESTED_NUMBER, 
        Workbook.REPLICATION_NUMBER,  Workbook.REPLICATION_FACTOR_NUMBER, 
        Workbook.BLOCK_NUMBER, Workbook.BLOCKING_FACTOR_NUMBER, Workbook.BLOCK_NESTED_NUMBER, Workbook.BLOCKING_FACTOR_NESTED_NUMBER
    };
    
    public static final String[] GERMPLASM_PROPS = {
        Workbook.GERMPLASM_ENTRY, Workbook.SEED_SOURCE, Workbook.CROSS_HISTORY 
    };
    public static final String[] GERMPLASM_PROPS_SCALES = {
    	Workbook.GERMPLASM_ENTRY, Workbook.GERMPLASM_ID, Workbook.GERMPLASM_ENTRY_CODE, Workbook.GERMPLASM_ENTRY_NUMBER, Workbook.GERMPLASM_ENTRY_NESTED_NUMBER,
        Workbook.GERMPLASM_ID_DBCV, Workbook.GERMPLASM_ID_DBID
    };
    
    public static final int[] VARIATE_TYPES = {
        CATEGORICAL_VARIATE_TYPE, OBSERVATION_VARIATE_TYPE
    };

//    private static final int INIT_VAR_TYPE = -1;
    /**
     * Get stored in variable type from combination of property and scale.
     * If no match from property-scale combination, perform matching by
     * property name.
     * 
     * @param property
     * @param scale
     * @return 
     */ 
    public static int getStoredInVariableType(String property, String scale){
        int varType = -1;
        String compareStr = Workbook.getStringWithOutBlanks(property + scale);
        
        if (ArrayUtils.contains(TRIAL_ENVT_PROPS_SCALES, compareStr)){
            varType = TRIAL_ENVT_VAR_TYPE;
            
        } else if (ArrayUtils.contains(TRIAL_DESIGN_PROPS_SCALES, compareStr)){
            varType = TRIAL_DESIGN_VAR_TYPE;
            
        } else if (ArrayUtils.contains(GERMPLASM_PROPS_SCALES, compareStr)){
        	if("DBCV".equals(scale)) {
        		varType = GERMPLASM_DESIG_VAR_TYPE;
        	} else if("DBID".equals(scale)) {
        		varType = GERMPLASM_GID_VAR_TYPE;
        	} else if("NUMBER".equals(scale)) {
        		varType = GERMPLASM_ENTRY_NUMBER_VAR_TYPE;
        	} else if("CODE".equals(scale)) {
        		varType = GERMPLASM_ENTRY_CODE_VAR_TYPE;
        	} else {
        		varType = GERMPLASM_VAR_TYPE;
        	}
            
        }
        
        if (varType == -1){
            varType = getStoredInVariableType(property);
        }
               
        return varType;
    }
    
    public static int getStoredInVariableType(String property){
        int varType = TRIAL_DESIGN_VAR_TYPE;
        String compareStr = Workbook.getStringWithOutBlanks(property);
        
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
