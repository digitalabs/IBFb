package ibfb.germplasmlist.importing.actions;

import ibfb.germplasmlist.importing.wizard.ImportGermplasmVisualPanel1;
import ibfb.germplasmlist.importing.wizard.ImportGermplasmWizardIterator;
import ibfb.studyexplorer.explorer.listNames.ListDataWindowTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

@ActionID(category = "BreedingManager",
id = "ibfb.germplasmlist.importing.wizard.ImportGermplasmListAction")
@ActionRegistration(iconBase="ibfb/germplasmlist/images/import.png", displayName = "#CTL_ImportGermplasmListAction")
@ActionReferences({
    @ActionReference(path = "Menu/BreedingManager", position = 100),
    @ActionReference(path = "Toolbars/BreedingManager", position = 100)
})
public class ImportGermplasmListAction implements ActionListener {

    private static boolean OPEN_GERMPLASM = true;
    private static boolean DONT_OPEN_GERMPLASM = true;
    private static ResourceBundle bundle = NbBundle.getBundle(ImportGermplasmVisualPanel1.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        ImportGermplasmWizardIterator importWizard = new ImportGermplasmWizardIterator();

        WizardDescriptor wiz = new WizardDescriptor(importWizard);
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(bundle.getString("ImportGermplasmWizard.title"));

        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            Listnms listnmsToCreate = importWizard.getImportGermplasmWizardPanel3().getComponent().getListnms();
            System.out.println(listnmsToCreate.toString());
            List<Listdata> listDataEntries = importWizard.getImportGermplasmWizardPanel2().getComponent().getListDataEntries();
            addNewGermplasmList(listnmsToCreate, listDataEntries, OPEN_GERMPLASM);
        }

    }

    /**
     * 
     * @return 
     */
    public static boolean listCreatedFromWizard() {
        boolean listCreated = false;
        ImportGermplasmWizardIterator importWizard = new ImportGermplasmWizardIterator();

        WizardDescriptor wiz = new WizardDescriptor(importWizard);
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(bundle.getString("ImportGermplasmWizard.title"));

        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            Listnms listnmsToCreate = importWizard.getImportGermplasmWizardPanel3().getComponent().getListnms();
            System.out.println(listnmsToCreate.toString());
            List<Listdata> listDataEntries = importWizard.getImportGermplasmWizardPanel2().getComponent().getListDataEntries();
            addNewGermplasmList(listnmsToCreate, listDataEntries, DONT_OPEN_GERMPLASM);
            listCreated=true;
        }
        return listCreated;
    }

    private static void addNewGermplasmList(Listnms listnmsToCreate, List<Listdata> listDataEntries, boolean openListAtFinal) {
        // add list to database
        AppServicesProxy.getDefault().appServices().addListnms(listnmsToCreate);


        for (Listdata listdata : listDataEntries) {
            // assign listid to each listdata
            listdata.getListdataPK().setListid(listnmsToCreate.getListid());
        }

        // finally save the list to database
        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnmsToCreate, listDataEntries, listnmsToCreate.getListuid());

        if (openListAtFinal) {
            openRecentList(listnmsToCreate);
        }

    }

    private static void openRecentList(Listnms listnms) {
        ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);
        if (ldwtc == null) {
            ldwtc = new ListDataWindowTopComponent(listnms);
        }
        ldwtc.open();
        ldwtc.requestActive();

    }
}
