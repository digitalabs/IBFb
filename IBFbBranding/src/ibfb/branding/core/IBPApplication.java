package ibfb.branding.core;

/**
 *
 * @author tmsg
 */
public class IBPApplication {
    public static final String BREEDING_MANAGER = "BREEDING_MANAGER";
    public static final String FIELDBOOK_TOOLS  = "FIELDBOOK_TOOLS";
    public static final String BOTH_APPS = "BOTH_APPS";
    
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
}
