package ibfb.germplasmlist.importing.wizard;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardDescriptor.Panel;

/**
 *
 * @author tmsg
 */
public class ImportGermplasmWizardIterator implements WizardDescriptor.Iterator<WizardDescriptor> {

    private int index;
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;
    private ImportGermplasmWizardPanel1 importGermplasmWizardPanel1 = new ImportGermplasmWizardPanel1();
    private ImportGermplasmWizardPanel2 importGermplasmWizardPanel2 = new ImportGermplasmWizardPanel2();
    private ImportGermplasmWizardPanel3 importGermplasmWizardPanel3 = new ImportGermplasmWizardPanel3();
    public List<Panel<WizardDescriptor>> getPanels() {
        if (panels == null) {
            panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
            panels.add(importGermplasmWizardPanel1);
            panels.add(importGermplasmWizardPanel2);
            panels.add(importGermplasmWizardPanel3);
            String[] steps = new String[panels.size()];
            for (int i = 0; i < panels.size(); i++) {
                Component c = panels.get(i).getComponent();
                // Default step name to component name of panel.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                    jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
                }
            }
        }
        return panels;
    }

    @Override
    public Panel<WizardDescriptor> current() {
       return  getPanels().get(index);
    }

    @Override
    public String name() {
        return index + 1 + " from " + getPanels().size();
    }

      @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        //if (current() instanceof ImportGermplasmVisualPanel1) {
        if (index == 0) {
            importGermplasmWizardPanel2.getComponent().setWorkbook(importGermplasmWizardPanel1.getComponent().getWorkbook());
            importGermplasmWizardPanel2.getComponent().setFileName(importGermplasmWizardPanel1.getComponent().getTxtFilePath().getText());
            importGermplasmWizardPanel2.getComponent().readGermplasmList();
            
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        
    }
}
