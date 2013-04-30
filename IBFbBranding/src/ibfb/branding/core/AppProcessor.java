package ibfb.branding.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.netbeans.api.sendopts.CommandException;
import org.netbeans.spi.sendopts.Env;
import org.netbeans.spi.sendopts.Option;
import org.netbeans.spi.sendopts.OptionProcessor;
import org.openide.awt.MenuBar;
import org.openide.filesystems.FileUtil;
import org.openide.util.lookup.ServiceProvider;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;

/**
 *
 * @author tmsg
 */

@ServiceProvider(service = OptionProcessor.class)
public class AppProcessor extends OptionProcessor {
    private static final String IBP_APP_LONG_NAME = "ibpApplication";
    
    private static final String BREEDING_MANAGER = "BreedingManager";
    private static final String FIELDBOOK_TOOLS = "IBFieldbookTools";

    private Option optionApp = Option.requiredArgument(Option.NO_SHORT_NAME,IBP_APP_LONG_NAME);
    
    @Override
    protected Set<Option> getOptions() {
        Set set = new HashSet();
        set.add(optionApp);
        return set;
    }

    @Override
    protected void process(Env env, Map<Option, String[]> maps) throws CommandException {
        if (maps.containsKey(optionApp)) {
            Object[] option = maps.get(optionApp);
            String command = option[0].toString();
            
            if (command != null && ! command.isEmpty()) {
               if (command.equals(BREEDING_MANAGER)) {
                   IBPApplication.CURRENT_APP = IBPApplication.BREEDING_MANAGER;
               } else if (command.equals(FIELDBOOK_TOOLS)) {
                   IBPApplication.CURRENT_APP = IBPApplication.FIELDBOOK_TOOLS;
               } else {
                  IBPApplication.CURRENT_APP = IBPApplication.BOTH_APPS; 
               }
            } else {
               IBPApplication.CURRENT_APP = IBPApplication.BOTH_APPS;
            }
        
        }
    }
    
}
