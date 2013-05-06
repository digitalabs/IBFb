/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.lists.core.importwizard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class ImportGermplasmWizardPanel3 implements WizardDescriptor.Panel<WizardDescriptor>, DocumentListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private ImportGermplasmVisualPanel3 component;
    /**
     * is it current panel filled correctly
     */
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.

    @Override
    public ImportGermplasmVisualPanel3 getComponent() {
        if (component == null) {
            component = new ImportGermplasmVisualPanel3();
            component.getTxtListName().getDocument().addDocumentListener(this);
            component.getTxtDescription().getDocument().addDocumentListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return isValid;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        change();
    }

    private void change() {
        String listName = component.getTxtListName().getText();
        String description = component.getTxtDescription().getText();
        boolean isValidInput = (listName != null && !listName.isEmpty()) && (description != null && !description.isEmpty());
        
        if (isValidInput && ! listAlreadyExists(listName)) {
            isValid = true;
        } else {
            isValid = false;
        }
        fireChangeEvent();
    }

    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }

        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }
    
    private boolean listAlreadyExists(String listName) {
        boolean result = false;
        
        result = AppServicesProxy.getDefault().appServices().existGermplasmListName(listName);
        if (result) {
           DialogUtil.displayError(NbBundle.getMessage(ImportGermplasmWizardPanel3.class, "ImportGermplasmVisualPanel3.listAlreadyExists")); 
        }
        
        return result;
    }
}
