
package ibfb.studyeditor.importwizard;

import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class importingVisualPanel1 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(importingVisualPanel1.class);

    /** Creates new form importingVisualPanel1 */
    public importingVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("importingVisualPanel1.title");
    }

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        lblImgAndroid = new javax.swing.JLabel();
        lblImgExcel = new javax.swing.JLabel();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jRadioButtonDataKapture = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        buttonGroup1.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToFieldlogItemStateChanged(evt);
            }
        });
        jRadioButtonToFieldlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToFieldlogActionPerformed(evt);
            }
        });

        lblImgAndroid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgAndroid, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.lblImgAndroid.text")); // NOI18N
        lblImgAndroid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgAndroidMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgAndroidMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgAndroidMouseEntered(evt);
            }
        });

        lblImgExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgExcel, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.lblImgExcel.text")); // NOI18N
        lblImgExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseEntered(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToExcelActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonDataKapture);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDataKapture, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonDataKapture.text")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/csvexcel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jLabel2.text")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonToExcel)
                    .addComponent(jRadioButtonDataKapture))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImgExcel)
                    .addComponent(lblImgAndroid)
                    .addComponent(jLabel2))
                .addContainerGap(338, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButtonToFieldlog)
                    .addComponent(lblImgAndroid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblImgExcel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jRadioButtonToExcel)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButtonDataKapture))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImgAndroidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgAndroidMouseClicked

        this.jRadioButtonToFieldlog.setSelected(true);     }//GEN-LAST:event_lblImgAndroidMouseClicked

    private void lblImgAndroidMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgAndroidMouseExited

        this.lblImgAndroid.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png")));     }//GEN-LAST:event_lblImgAndroidMouseExited

    private void lblImgAndroidMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgAndroidMouseEntered

        this.lblImgAndroid.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog2.png")));     }//GEN-LAST:event_lblImgAndroidMouseEntered

    private void lblImgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseClicked

        this.jRadioButtonToExcel.setSelected(true);     }//GEN-LAST:event_lblImgExcelMouseClicked

    private void lblImgExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseExited

        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png")));     }//GEN-LAST:event_lblImgExcelMouseExited

    private void lblImgExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseEntered

        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig2.png")));     }//GEN-LAST:event_lblImgExcelMouseEntered

    private void jRadioButtonToFieldlogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToFieldlogItemStateChanged

    private void jRadioButtonToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToExcelActionPerformed

    private void jRadioButtonToFieldlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToFieldlogActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JRadioButton jRadioButtonDataKapture;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    private javax.swing.JLabel lblImgAndroid;
    private javax.swing.JLabel lblImgExcel;
    // End of variables declaration//GEN-END:variables
}
