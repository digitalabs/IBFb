package org.cimmyt.cril.ibwb.commongui;

/**
 *
 * @author tmsg
 */
public class AppConstants {
    public static final boolean SHOW_IMPORT_GERMPLASM = false;
    
    public static final boolean ENABLE_TMS_BUTTONS = false;
    
    private static final String BREEDING_MANAGER = "BREEDING_MANAGER";
    private static final String FIELDBOOK_TOOLS  = "FIELDBOOK_TOOLS";
    private static final String BOTH_APPS = "BOTH_APPS";
    
    public static String CURRENT_APP = BOTH_APPS;
    
    public static boolean onlyBreeding() {
        return CURRENT_APP.equals(BREEDING_MANAGER);
    }

    public static boolean onlyFieldbook() {
        return CURRENT_APP.equals(FIELDBOOK_TOOLS);
    }
    
    public static boolean bothApps() {
        return CURRENT_APP.equals(BOTH_APPS);
    }

    public static void setCurrentApp(String appName) {
        CURRENT_APP = appName;
    }
    
}
