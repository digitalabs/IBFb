/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.lists.report;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public final class LabelReportWizardIterator implements WizardDescriptor.Iterator<WizardDescriptor> {

    private LabelReportWizardPanel1 labelReportWizardPanel1 = new LabelReportWizardPanel1();
    // Example of invoking this wizard:
    // @ActionID(category="...", id="...")
    // @ActionRegistration(displayName="...")
    // @ActionReference(path="Menu/...")
    // public static ActionListener run() {
    //     return new ActionListener() {
    //         @Override public void actionPerformed(ActionEvent e) {
    //             WizardDescriptor wiz = new WizardDescriptor(new LabelReportWizardIterator());
    //             // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
    //             // {1} will be replaced by WizardDescriptor.Iterator.name()
    //             wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
    //             wiz.setTitle("...dialog title...");
    //             if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
    //                 ...do something...
    //             }
    //         }
    //     };
    // }

    public static void launchLabelReportWizard(List<List<Object>> records, List<String> fields) {
        LabelReportWizardIterator lrwi = new LabelReportWizardIterator(fields);
        WizardDescriptor wiz = new WizardDescriptor(lrwi);

        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
        // {1} will be replaced by WizardDescriptor.Iterator.name()
        //wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle(NbBundle.getMessage(LabelReportWizardIterator.class, "PrintLabels"));
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            Integer[] selectedFields = lrwi.getLabelReportWizardPanel1().getComponent().getSelectedFields();
            if (lrwi.getLabelReportWizardPanel1().getComponent().smallReportSelected()) {
                printLabelReport(records, ProcessLabelReport.SMALL_REPORT, selectedFields);
            } else {
                printLabelReport(records, ProcessLabelReport.BIG_REPORT, selectedFields);
            }

        }
    }
    private int index;
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;

    public LabelReportWizardIterator(List<String> fields) {
        this.labelReportWizardPanel1.getComponent().assignFields(fields);
    }

    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {
        if (panels == null) {
            panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
            panels.add(labelReportWizardPanel1);
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
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
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

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    public LabelReportWizardPanel1 getLabelReportWizardPanel1() {
        return labelReportWizardPanel1;
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed

    private static void printLabelReport(List<List<Object>> records, String reportType, Integer[] selectedFields) {
        List<ReportBean> beanList = new ArrayList<ReportBean>();
        for(Object row : records ) {
            List<Object> cols = (List<Object>)row;
            ReportBean rb = new ReportBean();
            if (selectedFields[ReportBean.BARCODE] != 0  && cols.get(selectedFields[ReportBean.BARCODE]-1) != null ) {
                rb.setLotid(cols.get(selectedFields[ReportBean.BARCODE]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_1] != 0 && cols.get(selectedFields[ReportBean.LABEL_1]-1) != null) {
                rb.setLabel1(cols.get(selectedFields[ReportBean.LABEL_1]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_2] != 0 && cols.get(selectedFields[ReportBean.LABEL_2]-1) != null) {
                rb.setLabel2(cols.get(selectedFields[ReportBean.LABEL_2]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_3] != 0 && cols.get(selectedFields[ReportBean.LABEL_3]-1) != null) {
                rb.setLabel3(cols.get(selectedFields[ReportBean.LABEL_3]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_4] != 0 && cols.get(selectedFields[ReportBean.LABEL_4]-1) != null) {
                rb.setLabel4(cols.get(selectedFields[ReportBean.LABEL_4]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_5] != 0 && cols.get(selectedFields[ReportBean.LABEL_5]-1) != null) {
                rb.setLabel5(cols.get(selectedFields[ReportBean.LABEL_5]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_6] != 0 && cols.get(selectedFields[ReportBean.LABEL_6]-1) != null) {
                rb.setLabel6(cols.get(selectedFields[ReportBean.LABEL_6]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_7] != 0 && cols.get(selectedFields[ReportBean.LABEL_7]-1) != null) {
                rb.setLabel7(cols.get(selectedFields[ReportBean.LABEL_7]-1).toString());
            }
            if (selectedFields[ReportBean.LABEL_8] != 0 && cols.get(selectedFields[ReportBean.LABEL_8]-1) != null) {
                rb.setLabel8(cols.get(selectedFields[ReportBean.LABEL_8]-1).toString());
            }
            
            beanList.add(rb);
        }

        ProcessLabelReport.printBarCodeReport(beanList, reportType);
    }
    
}
