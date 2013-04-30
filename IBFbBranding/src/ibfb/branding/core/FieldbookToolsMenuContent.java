/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class FieldbookToolsMenuContent extends MultiFileSystem {
    private static final String FIELDBOOK_FILE_MENU = "fieldbookToolsMenu.xml";
    private static FieldbookToolsMenuContent INSTANCE;
    
    public FieldbookToolsMenuContent() {
        INSTANCE = this;
        setPropagateMasks(true);
    }
    
    static boolean hasContent() {
        return INSTANCE.getDelegates().length > 0;
    }
    
    static void enable() {
        if (! hasContent()) {
            try {
                INSTANCE.setDelegates(new XMLFileSystem(BreedingManagerMenuContent.class.getResource(FIELDBOOK_FILE_MENU)));
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
    

