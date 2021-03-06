/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ScaleDisPanel.java
 *
 * Created on Apr 13, 2012, 4:21:05 PM
 */
package ibfb.traits.traits;

import org.cimmyt.cril.ibwb.domain.TmsScaleDis;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author TMSANCHEZ
 */
public class ScaleDisPanel extends javax.swing.JPanel  implements TmsScaleDefCellValue {

    private TmsScaleDis scaleDis;

    /** Creates new form ScaleDisPanel */
    public ScaleDisPanel(TmsScaleDis scaleDis) {
        this.scaleDis = scaleDis;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        lblStart = new javax.swing.JLabel();
        txtStartValue = new javax.swing.JTextField();
        txtEndValue = new javax.swing.JTextField();
        lblEndValue = new javax.swing.JLabel();
        btnEditValues = new javax.swing.JButton();

        lblStart.setText(org.openide.util.NbBundle.getMessage(ScaleDisPanel.class, "ScaleDisPanel.lblStart.text")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${scaleDis.value}"), txtStartValue, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${scaleDis.valdesc}"), txtEndValue, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblEndValue.setText(org.openide.util.NbBundle.getMessage(ScaleDisPanel.class, "ScaleDisPanel.lblEndValue.text")); // NOI18N

        btnEditValues.setText(org.openide.util.NbBundle.getMessage(ScaleDisPanel.class, "ScaleDisPanel.btnEditValues.text")); // NOI18N
        btnEditValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEndValue)
                    .addComponent(lblStart))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtStartValue, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditValues))
                    .addComponent(txtEndValue, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStart)
                    .addComponent(txtStartValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditValues))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEndValue)
                    .addComponent(txtEndValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditValuesActionPerformed
        editValues();
    }//GEN-LAST:event_btnEditValuesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditValues;
    private javax.swing.JLabel lblEndValue;
    private javax.swing.JLabel lblStart;
    private javax.swing.JTextField txtEndValue;
    private javax.swing.JTextField txtStartValue;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void editValues() {
        ScaleDisValuesPanel scaleDisValuesPanel = new ScaleDisValuesPanel();


        //NotifyDescriptor notifyDescriptor = new NotifyDescriptor(scaleDisValuesPanel, "Enter Values", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, null);
        DialogDescriptor notifyDescriptor = new DialogDescriptor(scaleDisValuesPanel, "Enter Values", true, new String[]{"Ok"}, null, DialogDescriptor.DEFAULT_ALIGN, null, null);

//        final DialogDescriptor dd = new DialogDescriptor(
//                     bookPanel           // component to display
//                   , NbBundle.getMessage("Enter new Book")    // title
//                   , true                // modal, application is blocked
//                   , options             // buttons here, can be Strings, too
//                   , save                  // initial value, default value
//                   , DialogDescriptor.RIGHT_ALIGN  // option buttons right
//                   , HelpCtx.DEFAULT_HELP
//                   , null     // action listener - is set later
//                );
//                
//        // List of options that close the dialog 
//        // null - all options will close the dialog 
//        // empty array - no option will close the dialog
//        // - save and cancel close the dialog for us, 
//        // - saveAsTemplate leaves the form for next working
//
//        dd.setClosingOptions( new JButton[] { save, cancel } );


        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
        }
    }

    public TmsScaleDis getScaleDis() {
        return scaleDis;
    }

    public void setScaleDis(TmsScaleDis scaleDis) {
        this.scaleDis = scaleDis;
    }

    @Override
    public Object getCellValue() {
        return this.scaleDis;
    }
    
    
}
