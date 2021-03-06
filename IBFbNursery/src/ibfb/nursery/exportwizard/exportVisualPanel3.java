package ibfb.nursery.exportwizard;

import ibfb.nursery.filters.CSVFiltro;
import ibfb.nursery.filters.ExcelFiltro;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public final class exportVisualPanel3 extends JPanel {

    public String getNombreArchivo() {
        return nombreArchivo;
    }
    private JFileChooser selectorArchivo = new JFileChooser();
    private String nombreArchivo = "";
    private int opcionFiltro = 0; //0=xls, 1=csv
    static WizardDescriptor wizardDescriptor;

    public exportVisualPanel3() {
        initComponents();
        this.jTextArea1.setText("");
    }

    public static void setWizardDescriptor(WizardDescriptor wd) {
        wizardDescriptor = wd;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(exportVisualPanel3.class,"exportVisualPanel3.name");
    }

    public void setOpcionFiltro(int opcionFiltro) {
        this.opcionFiltro = opcionFiltro;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(exportVisualPanel3.class, "exportVisualPanel3.jButtonSearch.text_1")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/file.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(exportVisualPanel3.class, "exportVisualPanel3.jLabel1.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(exportVisualPanel3.class, "exportVisualPanel3.jLabel2.text_1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        openFile();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed
        openFile();
    }//GEN-LAST:event_jTextArea1MousePressed

    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }
        File archivoNulo = new File("");
        selectorArchivo.setSelectedFile(archivoNulo);
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (this.opcionFiltro == 0) {
            selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        }
        if (this.opcionFiltro == 1) {
            selectorArchivo.addChoosableFileFilter(new CSVFiltro());
        }


        int resultado = selectorArchivo.showSaveDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        
        

        if (this.opcionFiltro == 0) {
            nombreArchivo = selectorArchivo.getSelectedFile().toString();
            this.jTextArea1.setText(nombreArchivo + ".xls");
        }


        if (this.opcionFiltro == 1) {
            nombreArchivo = selectorArchivo.getSelectedFile().toString();
            this.jTextArea1.setText(nombreArchivo + ".csv");
        }
        
        File fileName = new File(jTextArea1.getText());
        if (fileName.exists()) {
           if (! DialogUtil.displayConfirmation(NbBundle.getMessage(exportVisualPanel3.class,"exportVisualPanel3.overwrite"), NbBundle.getMessage(exportVisualPanel3.class,"exportVisualPanel3.exists"), NotifyDescriptor.YES_NO_OPTION) ){
             return;  
           }
        }
        

        wizardDescriptor.doFinishClick();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
