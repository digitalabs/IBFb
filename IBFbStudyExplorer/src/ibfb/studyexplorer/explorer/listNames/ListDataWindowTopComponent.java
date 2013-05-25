package ibfb.studyexplorer.explorer.listNames;

import ibfb.domain.core.Factor;
import ibfb.inventory.core.InventoryFactors;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.TableBindingUtil;
import org.cimmyt.cril.ibwb.commongui.filters.ExcelFiltro;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import ibfb.inventory.export.InventoryExcelExporter;
import ibfb.inventory.models.GermplasmEntriesTableModel;
import ibfb.inventory.update.UpdateInventoryWizardIterator;
import ibfb.nursery.inventory.InventoryTopComponent;
import java.text.MessageFormat;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

@ConvertAsProperties(dtd = "-//ibfb.studyexplorer.explorer.listNames//ListDataWindow//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListDataWindowTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.studyexplorer.explorer.listNames.ListDataWindowTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListDataWindowAction",
preferredID = "ListDataWindowTopComponent")
public final class ListDataWindowTopComponent extends TopComponent {

    private ResourceBundle bundle = NbBundle.getBundle(ListDataWindowTopComponent.class);
    private Listnms parentListname;
    private List<Listdata> listdataEntries;
    private boolean forWheat = false;
    private JFileChooser fileChooser = new JFileChooser();

    public ListDataWindowTopComponent(Listnms listnms) {
        this.parentListname = listnms;
        initComponents();
        String theName = NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent");
        setName(theName + "(" + listnms.getListname() + ")");
        setToolTipText(NbBundle.getMessage(ListDataWindowTopComponent.class, "HINT_ListDataWindowTopComponent"));
        this.parentListname = listnms;
        updateListData();
    }

    public ListDataWindowTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(ListDataWindowTopComponent.class, "HINT_ListDataWindowTopComponent"));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        popMnu = new javax.swing.JPopupMenu();
        pMnuDelete = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        txtListName = new javax.swing.JTextField();
        txtSearchText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListData = new javax.swing.JTable();
        lblEntriesFound = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        lblListId = new javax.swing.JLabel();
        txtListId = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnExporToInventory = new javax.swing.JButton();
        btnImportToInventory = new javax.swing.JButton();

        pMnuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(pMnuDelete, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.pMnuDelete.text")); // NOI18N
        pMnuDelete.setToolTipText(org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.pMnuDelete.toolTipText")); // NOI18N
        pMnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pMnuDeleteActionPerformed(evt);
            }
        });
        popMnu.add(pMnuDelete);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel1.text")); // NOI18N

        txtListName.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listname}"), txtListName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtSearchText.setText(org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.txtSearchText.text")); // NOI18N
        txtSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTextKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel2.text")); // NOI18N

        tblListData.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblListData.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListData.setComponentPopupMenu(popMnu);
        tblListData.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblListData);

        org.openide.awt.Mnemonics.setLocalizedText(lblEntriesFound, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.lblEntriesFound.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel3.text")); // NOI18N

        txtDescription.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listdesc}"), txtDescription, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        org.openide.awt.Mnemonics.setLocalizedText(lblListId, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.lblListId.text")); // NOI18N

        txtListId.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listid}"), txtListId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.openide.awt.Mnemonics.setLocalizedText(btnExporToInventory, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.btnExporToInventory.text")); // NOI18N
        btnExporToInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExporToInventoryActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnImportToInventory, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.btnImportToInventory.text")); // NOI18N
        btnImportToInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportToInventoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImportToInventory)
                    .addComponent(btnExporToInventory))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExporToInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImportToInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(lblListId))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescription)
                            .addComponent(txtListName)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblEntriesFound))
                                    .addComponent(txtListId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 135, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblListId)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtListId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEntriesFound, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    public boolean isForWheat() {
        return forWheat;
    }

    public void setIsForWheat(boolean forWheat) {
        this.forWheat = forWheat;
    }

    private void txtSearchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTextKeyReleased
        updateListData();
}//GEN-LAST:event_txtSearchTextKeyReleased

    private void pMnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pMnuDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_pMnuDeleteActionPerformed

    private void btnExporToInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExporToInventoryActionPerformed
        exportToInventory();
    }//GEN-LAST:event_btnExporToInventoryActionPerformed

    private void btnImportToInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportToInventoryActionPerformed
        importToInventory();
    }//GEN-LAST:event_btnImportToInventoryActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExporToInventory;
    private javax.swing.JButton btnImportToInventory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEntriesFound;
    private javax.swing.JLabel lblListId;
    private javax.swing.JMenuItem pMnuDelete;
    private javax.swing.JPopupMenu popMnu;
    private javax.swing.JTable tblListData;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtListId;
    private javax.swing.JTextField txtListName;
    private javax.swing.JTextField txtSearchText;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
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

    public Listnms getParentListname() {
        return parentListname;
    }

    public void setParentListname(Listnms parentListname) {
        this.parentListname = parentListname;
    }

    private void updateListData() {

        Listdata filter = new Listdata();
        filter.setEntrycd(txtSearchText.getText());
        filter.setListdataPK(new ListdataPK(parentListname.getListid(), 0));
        filter.setGlobalsearch(txtSearchText.getText());
        //int total = AppServicesProxy.getDefault().appServices().getTotalListdata(filter);

        listdataEntries = AppServicesProxy.getDefault().appServices().getListListdata(filter, 0, 0, false);

        StringBuilder header = new StringBuilder(bundle.getString("ListDataWindowTopComponent.headerGid"));
        header.append(",");
        header.append(bundle.getString("ListDataWindowTopComponent.headerDesignation"));
        header.append(",");
        header.append(bundle.getString("ListDataWindowTopComponent.headerEntryCD"));
        header.append(",");
        header.append(bundle.getString("ListDataWindowTopComponent.headerSource"));
        header.append(",");
        header.append(bundle.getString("ListDataWindowTopComponent.headerCross"));
        header.append(",");
        header.append(bundle.getString("ListDataWindowTopComponent.headerEntryId"));



        lblEntriesFound.setText(listdataEntries.size() + " " + bundle.getString("ListDataWindowTopComponent.entriesFound"));
        TableBindingUtil.createColumnsFromDB(Listdata.class, listdataEntries, tblListData, "gid,desig,entrycd,source,grpname,entryid", header.toString());

        if (isForWheat()) {
            loadData();
            System.out.println("ok es trigo");
        }
    }

    public void loadData() {

        TableColumn columna = new TableColumn();
        columna.setHeaderValue("UNO");
        tblListData.addColumn(columna);

    }

    /**
     * Return current instance of TraitEditorTopComponent using current Traits
     * object
     *
     * @param scales Scale object to check
     * @return TraitEditorTopComponent instance if found or null if not found
     */
    public static ListDataWindowTopComponent getListDataWindowTopComponent(Listnms listnms) {
        ListDataWindowTopComponent listDataWindowTopComponent = null;
        Set<TopComponent> comps = TopComponent.getRegistry().getOpened();
        for (TopComponent tc : comps) {
            if (tc instanceof ListDataWindowTopComponent) {
                ListDataWindowTopComponent setc = (ListDataWindowTopComponent) tc;
                StringBuilder name = new StringBuilder();

                name.append(NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent"));
                name.append("(").append(listnms.getListname()).append(")");

                if (setc.getName().equals(name.toString())) {
                    listDataWindowTopComponent = setc;
                    break;
                }
            }
        }
        return listDataWindowTopComponent;
    }

    private void deleteRecord() {
        String confirmation = bundle.getString("ListDataWindowTopComponent.deleteConfirmation");
        String title = bundle.getString("ListDataWindowTopComponent.deleteTitle");
        if (DialogUtil.displayConfirmation(confirmation, title, NotifyDescriptor.OK_CANCEL_OPTION)) {

            int[] selectedRows = tblListData.getSelectedRows();

            for (int index = 0; index < selectedRows.length; index++) {
                AppServicesProxy.getDefault().appServices().deleteListData(listdataEntries.get(selectedRows[index]));
            }
            updateListData();
        }
    }

    private void exportToInventory() {

        FileFilter[] filtros = new FileFilter[10];
        filtros = fileChooser.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            fileChooser.removeChoosableFileFilter(filtro);
        }


        File archivoNulo = new File("");
        fileChooser.setSelectedFile(archivoNulo);


        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new ExcelFiltro());
        int resultado = fileChooser.showSaveDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        exportToExcel(fileChooser.getSelectedFile());



    }

    private void exportToExcel(File file) {
        File archivo = new File(file.getAbsolutePath());

        List<Factor> inventoryFactors = InventoryFactors.getGermplasmAndInventoryFactors();

        List<List<Object>> germplasmListData = new ArrayList<List<Object>>();

        GermplasmEntriesTableModel modeloFilter = new GermplasmEntriesTableModel(inventoryFactors, germplasmListData);

        JTable inventoryTable = new JTable();
        inventoryTable.setModel(modeloFilter);


//        List<InventoryData> inventoryListData = new ArrayList<InventoryData>();
//        for (Listdata listdata : this.listdataEntries) {
//            InventoryData id = new InventoryData();
//            id.setDesig(listdata.getDesig());
//            id.setEntry(listdata.getEntryid());
//            id.setGid(listdata.getGid());
//            id.setCross(listdata.getGrpname());
//            inventoryListData.add(id);
//        }

        List<InventoryData> inventoryListData = AppServicesProxy.getDefault().appServices().getInventoryDataFromList(parentListname.getListid());
        
        InventoryExcelExporter inventoryExcelExporter = new InventoryExcelExporter(inventoryTable, archivo.getAbsolutePath(), inventoryListData, this.parentListname);
        try {
            //exporter = new ExcelTableExporter(tables, archivo, nombreTabs);
            inventoryExcelExporter.exportToExcel();
            //if (exporter.export()) {
            DialogUtil.display(NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.inventoryExported"));
            //}


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL EXPORTAR TABLA");
        }



    }

    private void importToInventory() {
//        WizardDescriptor wiz = new WizardDescriptor(new UpdateInventoryWizardIterator());
//        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
//        // {1} will be replaced by WizardDescriptor.Iterator.name()
//        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
//        wiz.setTitle(NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.UpdateSeedInventory"));
//        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
//            
//        }

        List<List<Object>> germplasmData = new ArrayList<List<Object>>();
        for (Listdata listdata: listdataEntries) {
            List<Object> row =  new ArrayList<Object>();
            row.add(listdata.getGid());
            row.add(listdata.getDesig());
            row.add(listdata.getEntrycd());
            row.add(listdata.getSource());
            row.add(listdata.getGrpname());
            row.add(listdata.getEntryid());
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            
            germplasmData.add(row);
        }

        InventoryTopComponent inventario = new InventoryTopComponent();

        inventario.assignGermplasmEntries(InventoryFactors.getGermplasmFactors(), germplasmData);
        inventario.setListName(parentListname.getListname());
        inventario.setDescription(parentListname.getListdesc());
        inventario.setListToSave(parentListname);

        if (!inventario.validateSeedStockTrait()) {
            return;
        }
        inventario.open();
        inventario.setName(this.getName() + " INVENTORY");
        inventario.requestActive();
    }
}
