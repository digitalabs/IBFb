package ibfb.studyeditor.importwizard;

import ibfb.studyexplorer.filters.CSVFiltro;
import ibfb.studyexplorer.filters.ExcelFiltro;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;

public final class ImportWizardVisualPanel1 extends JPanel {

    private JFileChooser selectorArchivo = new JFileChooser();

    public ImportWizardVisualPanel1() {
        initComponents();
        customDialog();

    }

    @Override
    public String getName() {
        return "Import data from file";
    }

    public JFileChooser getjFileChooser() {
        return selectorArchivo;
    }
    


    public JFileChooser getSelectorArchivo() {
        return selectorArchivo;
    }

  

    public JRadioButton getjRadioButtonToExcel() {
        return jRadioButtonToExcel;
    }

    public JRadioButton getjRadioButtonToFieldlog() {
        return jRadioButtonToFieldlog;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        jRadioButtonDataKapture = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jLabel1.text")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jLabel3.text")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToExcelItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToFieldlogItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDataKapture, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jRadioButtonDataKapture.text")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/csvexcel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jLabel2.text")); // NOI18N
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
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonDataKapture)
                    .addComponent(jRadioButtonToExcel)
                    .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jRadioButtonToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDataKapture)
                    .addComponent(jLabel2))
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        this.jRadioButtonToFieldlog.setSelected(true);     }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered

        this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog2.png")));     }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited

        this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png")));     }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        this.jRadioButtonToExcel.setSelected(true);     }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered

        this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig2.png")));     }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited

        this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png")));     }//GEN-LAST:event_jLabel3MouseExited

    private void jRadioButtonToFieldlogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogItemStateChanged
        if (jRadioButtonToFieldlog.isSelected()) {
            FileFilter[] filtros = new FileFilter[10];
            filtros = selectorArchivo.getChoosableFileFilters();
            for (int i = 0; i < filtros.length; i++) {
                FileFilter filtro = filtros[i];
                selectorArchivo.removeChoosableFileFilter(filtro);
            }
            this.selectorArchivo.addChoosableFileFilter(new CSVFiltro());
        }
    }//GEN-LAST:event_jRadioButtonToFieldlogItemStateChanged

    private void jRadioButtonToExcelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelItemStateChanged
        if (jRadioButtonToExcel.isSelected()) {
            FileFilter[] filtros = new FileFilter[10];
            filtros = selectorArchivo.getChoosableFileFilters();
            for (int i = 0; i < filtros.length; i++) {
                FileFilter filtro = filtros[i];
                selectorArchivo.removeChoosableFileFilter(filtro);
            }
            this.selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        }
    }//GEN-LAST:event_jRadioButtonToExcelItemStateChanged

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButtonDataKapture;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    // End of variables declaration//GEN-END:variables

    private void customDialog() {
        this.selectorArchivo.addChoosableFileFilter(new CSVFiltro());
    }
}
