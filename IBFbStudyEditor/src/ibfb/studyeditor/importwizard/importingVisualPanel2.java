package ibfb.studyeditor.importwizard;

import ibfb.studyexplorer.filters.CSVFiltro;
import ibfb.studyexplorer.filters.ExcelFiltro;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public final class importingVisualPanel2 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(importingVisualPanel2.class);

    private JFileChooser selectorArchivo = new JFileChooser();
    private File nombreArchivo;
    private int opcionFiltro = 0; //0=xls, 1=csv
    static WizardDescriptor wizardDescriptor;

    public importingVisualPanel2() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("importingVisualPanel2.title");
    }

    public File getArchivo() {
        return nombreArchivo;
    }

    public static void setWizardDescriptor(WizardDescriptor wd) {
        wizardDescriptor = wd;
    }

    public void setOpcionFiltro(int opcionFiltro) {
        this.opcionFiltro = opcionFiltro;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonSearch = new javax.swing.JButton();
        lblSelectSourcefile = new javax.swing.JLabel();
        lblImgFolders = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.jButtonSearch.text")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblSelectSourcefile, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.lblSelectSourcefile.text")); // NOI18N

        lblImgFolders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/file.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgFolders, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.lblImgFolders.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSelectSourcefile, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImgFolders)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectSourcefile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblImgFolders, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonSearch))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed

        openFile();     }//GEN-LAST:event_jTextArea1MousePressed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        openFile();     }//GEN-LAST:event_jButtonSearchActionPerformed

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

        switch (opcionFiltro) {
            case 0:
                selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
                break;
            case 1:
                selectorArchivo.addChoosableFileFilter(new CSVFiltro());
                break;
            case 2:
                selectorArchivo.addChoosableFileFilter(new CSVFiltro());
                break;
        }

        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        switch (opcionFiltro) {
            case 0:
                nombreArchivo = selectorArchivo.getSelectedFile();
                this.jTextArea1.setText(nombreArchivo + ".xls");
                break;
            case 1:
                nombreArchivo = selectorArchivo.getSelectedFile();
                this.jTextArea1.setText(nombreArchivo + ".csv");
                break;
            case 2:
                nombreArchivo = selectorArchivo.getSelectedFile();
                this.jTextArea1.setText(nombreArchivo + ".csv");
                break;
        }

        wizardDescriptor.doFinishClick();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblImgFolders;
    private javax.swing.JLabel lblSelectSourcefile;
    // End of variables declaration//GEN-END:variables
}
