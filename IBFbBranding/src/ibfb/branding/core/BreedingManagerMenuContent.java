package ibfb.branding.core;


import org.openide.filesystems.FileSystem;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author tmsg
 */
@ServiceProvider(service=FileSystem.class)
public class BreedingManagerMenuContent extends MultiFileSystem {
     private static final String BREEDING_MANAGER_FILE_MENU = "breedingManagerMenu.xml";
    private static BreedingManagerMenuContent INSTANCE;
    
    public BreedingManagerMenuContent() {
        INSTANCE = this;
        setPropagateMasks(true);
    }
    
    static boolean hasContent() {
        return INSTANCE.getDelegates().length > 0;
    }
    
    static void enable() {
        if (! hasContent()) {
            try {
                INSTANCE.setDelegates(new XMLFileSystem(BreedingManagerMenuContent.class.getResource(BREEDING_MANAGER_FILE_MENU)));
                INSTANCE.refresh(true);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    static void disable() {
        INSTANCE.setDelegates();
    }
}
