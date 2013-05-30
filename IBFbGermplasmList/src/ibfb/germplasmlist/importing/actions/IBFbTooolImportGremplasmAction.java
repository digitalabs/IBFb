
package ibfb.germplasmlist.importing.actions;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "IBFieldbookTools",
id = "ibfb.germplasmlist.importing.wizard.ImportGermplasmListAction")
@ActionRegistration(iconBase="ibfb/germplasmlist/images/import.png",displayName = "#CTL_ImportGermplasmListAction")
@ActionReferences({
    @ActionReference(path = "Menu/IBFieldbookTools", position = 100 ),
    @ActionReference(path = "Toolbars/IBFieldbookTools", position = 100)
})
public class IBFbTooolImportGremplasmAction extends ImportGermplasmListAction{
    
}
