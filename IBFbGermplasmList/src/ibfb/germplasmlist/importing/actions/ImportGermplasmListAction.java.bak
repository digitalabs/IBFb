/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.germplasmlist.importing.actions;

import ibfb.germplasmlist.importing.wizard.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "BreedingManager",
id = "ibfb.germplasmlist.importing.wizard.ImportGermplasmListAction")
@ActionRegistration(displayName = "#CTL_ImportGermplasmListAction")
@ActionReferences({
    @ActionReference(path = "Menu/BreedingManager", position = 100),
    @ActionReference(path = "Toolbars/BreedingManager", position = 100)
})
public class ImportGermplasmListAction implements ActionListener {

    private ResourceBundle bundle = NbBundle.getBundle(ImportGermplasmVisualPanel1.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        ImportGermplasmWizardIterator importGermplasmWizardIterator = new ImportGermplasmWizardIterator();

        WizardDescriptor wiz = new WizardDescriptor(importGermplasmWizardIterator);

        //wiz.setOptions(new Object[]{NotifyDescriptor.CANCEL_OPTION, WizardDescriptor.FINISH_OPTION});

        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(bundle.getString("ImportGermplasmWizard.title"));


        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
        }

    }
}
