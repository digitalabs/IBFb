/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.scales;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.OntologyTool;
import org.cimmyt.cril.ibwb.commongui.TableBindingUtil;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ibfb.traits.scales//ScalesBrowser//EN",
autostore = false)
@TopComponent.Description(preferredID = "ScalesBrowserTopComponent",
iconBase = "ibfb/traits/scales/scales.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.traits.scales.ScalesBrowserTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ScalesBrowserAction",
preferredID = "ScalesBrowserTopComponent")
public final class ScalesBrowserTopComponent extends TopComponent {

    private ResourceBundle bundle = NbBundle.getBundle(ScalesBrowserTopComponent.class);
    
    private List<Scales> scales;

    public ScalesBrowserTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ScalesBrowserTopComponent.class, "CTL_ScalesBrowserTopComponent"));
        setToolTipText(NbBundle.getMessage(ScalesBrowserTopComponent.class, "HINT_ScalesBrowserTopComponent"));
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        updateScalesTable();
        tblScales.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editRecord();
                }
            }
            
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenuItem();
        mnuDelete = new javax.swing.JMenuItem();
        mnuOpenOntology = new javax.swing.JMenuItem();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblRecordsFound = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblScales = new javax.swing.JTable();
        tblBarMenu = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();

        mnuNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/new.png"))); // NOI18N
        mnuNew.setLabel(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.mnuNew.label")); // NOI18N
        mnuNew.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/scales/new.png"))); // NOI18N
        mnuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewActionPerformed(evt);
            }
        });
        popMenu.add(mnuNew);

        mnuEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuEdit, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.mnuEdit.text")); // NOI18N
        mnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEditActionPerformed(evt);
            }
        });
        popMenu.add(mnuEdit);

        mnuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuDelete, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.mnuDelete.text")); // NOI18N
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });
        popMenu.add(mnuDelete);

        mnuOpenOntology.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/browse16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuOpenOntology, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.mnuOpenOntology.text")); // NOI18N
        mnuOpenOntology.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenOntologyActionPerformed(evt);
            }
        });
        popMenu.add(mnuOpenOntology);

        org.openide.awt.Mnemonics.setLocalizedText(lblSearch, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.lblSearch.text")); // NOI18N

        txtSearch.setText(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.txtSearch.text")); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblRecordsFound, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.lblRecordsFound.text")); // NOI18N

        tblScales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblScales.setComponentPopupMenu(popMenu);
        jScrollPane1.setViewportView(tblScales);

        tblBarMenu.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnNew, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnNew.text")); // NOI18N
        btnNew.setToolTipText(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnNew.toolTipText")); // NOI18N
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnNew);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnEdit, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnEdit.text")); // NOI18N
        btnEdit.setToolTipText(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnEdit.toolTipText")); // NOI18N
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnDelete, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnDelete.text")); // NOI18N
        btnDelete.setToolTipText(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnDelete.toolTipText")); // NOI18N
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnDelete);

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/core/images/browse16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnBrowse, org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnBrowse.text")); // NOI18N
        btnBrowse.setToolTipText(org.openide.util.NbBundle.getMessage(ScalesBrowserTopComponent.class, "ScalesBrowserTopComponent.btnBrowse.toolTipText")); // NOI18N
        btnBrowse.setFocusable(false);
        btnBrowse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBrowse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnBrowse);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRecordsFound)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)))))
                    .addComponent(tblBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tblBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRecordsFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        updateScalesTable();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        createNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editRecord();
    }//GEN-LAST:event_btnEditActionPerformed

    private void mnuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewActionPerformed
        createNew();
    }//GEN-LAST:event_mnuNewActionPerformed

    private void mnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEditActionPerformed
        editRecord();
    }//GEN-LAST:event_mnuEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_mnuDeleteActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        openOntology();
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void mnuOpenOntologyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenOntologyActionPerformed
        openOntology();
    }//GEN-LAST:event_mnuOpenOntologyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRecordsFound;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JMenuItem mnuEdit;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JMenuItem mnuOpenOntology;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JToolBar tblBarMenu;
    private javax.swing.JTable tblScales;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public void updateScalesTable() {

        Scales filter = new Scales(true);
        //filter.setTrname(txtSearchTrait.getText());
        filter.setGlobalsearch(txtSearch.getText());

        //int total = AppServicesProxy.getDefault().appServices().getTotalTrait(filter);

        scales = AppServicesProxy.getDefault().appServices().getListScalesByScaleNew(filter, 0, 0, false);
        lblRecordsFound.setText(scales.size() + " Record(s) found ");
        
        String scaleId = bundle.getString("ScalesBrowserTopComponent.scaleId");
        String scaleName = bundle.getString("ScalesBrowserTopComponent.scaleName");
        String ontology = bundle.getString("ScalesBrowserTopComponent.ontology");
        
        String headers = scaleId + "," + scaleName + "," + ontology; 
        
        TableBindingUtil.createColumnsFromDB(Scales.class, scales, tblScales, "scaleid,scname,ontology", headers);
        
    }

    private void createNew() {
        Scales scale = new Scales();
        ScalesEditorTopComponent scalesEditorTopComponent = new ScalesEditorTopComponent(scale);
        scalesEditorTopComponent.open();
        scalesEditorTopComponent.requestActive();
    }

    private void editRecord() {
        Integer row = tblScales.getSelectedRow();
        if (row != -1) {
            Scales scale = scales.get(row);
            ScalesEditorTopComponent scalesEditorTopComponent = ScalesEditorTopComponent.getScalesEditorTopComponent(scale);
            if (scalesEditorTopComponent == null) {
                scalesEditorTopComponent = new ScalesEditorTopComponent(scale);
            }
            scalesEditorTopComponent.open();
            scalesEditorTopComponent.requestActive();
        }
    }

    private void deleteRecord() {
        Integer row = tblScales.getSelectedRow();
        if (row != -1) {
        }
    }

    private void openOntology() {
        Integer row = tblScales.getSelectedRow();
        if (row != -1) {
            Scales scale = scales.get(row);
            OntologyTool.openOntology(scale.getOntology());
        }
    }
}
