package ibfb.germplasmlist.actions;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

@ActionID(category = "File",
id = "ibfb.germplasmlist.actions.CreateListAction")
@ActionRegistration(iconBase = "ibfb/germplasmlist/images/germplasmIcon16.png",
displayName = "#CTL_CreateListAction")
@ActionReferences({
    @ActionReference(path = "Menu/IBFieldbookTools", position = 1262),
    @ActionReference(path = "Toolbars/IBFieldbookTools", position = 200)
})

public class IBFbToolsCreateList extends CreateListAction {
    
}
