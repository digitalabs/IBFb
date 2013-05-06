/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.lists.core.importwizard;

import ibfb.lists.core.importwizard.ImportGermplasmWizardIterator;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class ImportList {

    private static boolean OPEN_GERMPLASM = true;
    private static boolean DONT_OPEN_GERMPLASM = true;
    private static ResourceBundle bundle = NbBundle.getBundle(ImportList.class);

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
            addNewGermplasmList(listnmsToCreate, listDataEntries);
            listCreated = true;
        }
        return listCreated;
    }

    private static void addNewGermplasmList(Listnms listnmsToCreate, List<Listdata> listDataEntries) {
        // add list to database
        AppServicesProxy.getDefault().appServices().addListnms(listnmsToCreate);


        for (Listdata listdata : listDataEntries) {
            // assign listid to each listdata
            listdata.getListdataPK().setListid(listnmsToCreate.getListid());
        }

        // finally save the list to database
        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnmsToCreate, listDataEntries, listnmsToCreate.getListuid());
    }
}