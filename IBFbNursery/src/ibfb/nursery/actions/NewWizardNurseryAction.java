package ibfb.nursery.actions;

import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.mainwizard.NurseryWizardIterator;
import ibfb.nursery.quickcreation.JDQuickCreation;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/*@ActionID(category = "BreedingManager",
id = "ibfb.nursery.actions.NewWizardNurseryAction")
@ActionRegistration(iconBase = "ibfb/nursery/images/newNursery.png",
displayName = "#CTL_NewWizardNurseryAction")
@ActionReferences({
    @ActionReference(path = "Menu/BreedingManager", position = 400),
    @ActionReference(path = "Toolbars/BreedingManager", position = 400)
})*/
public final class NewWizardNurseryAction extends SystemAction {//implements ActionListener {

    private ResourceBundle bundle = NbBundle.getBundle(NewWizardNurseryAction.class);
    //private final Study context;
    private JDQuickCreation quick;
    private ImageIcon icono = ImageUtilities.loadImageIcon("ibfb/nursery/images/newNursery.png", false);

    public NewWizardNurseryAction() {
        putValue(NAME, bundle.getString("NewWizardNurseryAction.newNursery"));
        putValue(SMALL_ICON, icono);
//        setEnabled(Boolean.TRUE);
//        this.context = null;
    }

    public NewWizardNurseryAction(Study context) {
        putValue(NAME, bundle.getString("NewWizardNurseryAction.newNursery"));
//        this.context = context;
//        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (SelectedStudy.selected.getStudyid() == null) {
            DialogUtil.displayError(bundle.getString("NewNurseryAction.selectAStudy"));
        } else {
            runWizard();
        }

    }

    public boolean existeNursery(String nursery) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
            for (TopComponent t : opened) {
                if (t.getName().equals(nursery)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }

    private void runWizard() {


        changeCursorWaitStatus(true);
        if (existeTopComponent("Nursery - " + SelectedStudy.selected.getStudy())) {
            int opc = JOptionPane.showConfirmDialog(null, bundle.getString("NewWizardNurseryAction.alreadyExists"), bundle.getString("NewWizardNurseryAction.caution"), JOptionPane.YES_NO_OPTION);
            if (opc == 0) {
                NurseryEditorTopComponent nurseryEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals("Nursery - " + SelectedStudy.selected.getStudy())) {
                        nurseryEditor = (NurseryEditorTopComponent) t;

                        nurseryEditor.close();
                    }
                }
                nurseryEditor.setStudy(SelectedStudy.selected);
                runNurseryWizard(nurseryEditor);

            }

        } else {

            NurseryEditorTopComponent nurseryEditor = new NurseryEditorTopComponent();
            nurseryEditor.setStudy(SelectedStudy.selected);
            nurseryEditor.setName("Nursery - " + SelectedStudy.selected.getStudy());

            runNurseryWizard(nurseryEditor);

        }
        changeCursorWaitStatus(false);

    }

    @SuppressWarnings("unchecked")
    public void runNurseryWizard(NurseryEditorTopComponent nurseryEditor) {

        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }

        NurseryWizardIterator.nurseryTopComponent = nurseryEditor;
        nurseryEditor.setMainProperties(SelectedStudy.selected);
        WizardDescriptor.Iterator iterator = new NurseryWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle(bundle.getString("NewWizardNurseryAction.wizardTitle"));
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        changeCursorWaitStatus(false);

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            nurseryEditor.addChecks();
            nurseryEditor.fillObservationsData();

            if (NurseryWizardIterator.existenConstantes) {

                nurseryEditor.jTabbedPane1.setEnabledAt(2, true);

            } else {
                nurseryEditor.jTabbedPane1.setEnabledAt(2, false);
            }


            if (NurseryWizardIterator.existenConditions) {
                nurseryEditor.jTabbedPane1.setEnabledAt(1, true);
            } else {
                nurseryEditor.jTabbedPane1.setEnabledAt(1, false);
            }


            nurseryEditor.open();
            nurseryEditor.requestActive();





        }

    }

    private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public boolean existeTopComponent(String topName) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(topName)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }

    @Override
    public String getName() {
        return "New Nursery...";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
