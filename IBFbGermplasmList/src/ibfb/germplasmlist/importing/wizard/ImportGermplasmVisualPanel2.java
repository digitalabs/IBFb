/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.germplasmlist.importing.wizard;

import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.location.SelectLocationPanel;
import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import ibfb.germplasmlist.models.GermplasmEntriesTableModelChecks;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.domain.*;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

public final class ImportGermplasmVisualPanel2 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(ImportGermplasmVisualPanel2.class);
    private Location selectedLocation;
    private GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();
    private Workbook workbook;
    private List<List<Object>> rowListExcel;
    private String fileName;

    /**
     * Creates new form ImportGermplasmVisualPanel2
     */
    public ImportGermplasmVisualPanel2() {
        initComponents();
        fillComboBoxes();
        initValues();
        radConnectEntries.setVisible(false);
        radOnlyMatch.setVisible(false);        
    }

    @Override
    public String getName() {
        return bundle.getString("ImportGermplasmVisualPanel2.title");
    }

    private void initValues() {
        dtDate.setDate(new java.util.Date());
    }

    private void fillComboBoxes() {
        fillMethodsComboBox();
        fillListNameComboBox();


    }

    private void fillListNameComboBox() {
        List<Udflds> udfldList = AppServicesProxy.getDefault().appServices().getUdfldsList(Udflds.NAMES_TABLE, Udflds.NAMES_FIELD);
        DefaultComboBoxModel dcbmList = (DefaultComboBoxModel) cboNameType.getModel();
        Udflds defaultNameToSelect = null;

        dcbmList.removeAllElements();

        for (Udflds udflds : udfldList) {
            dcbmList.addElement(udflds);
            if (udflds.getFldno().equals(Udflds.NAMES_DEFAULT_NAME)) {
                defaultNameToSelect = udflds;
            }
        }
        // if default name found
        if (defaultNameToSelect != null) {
            dcbmList.setSelectedItem(defaultNameToSelect);
        }
    }

    private void fillMethodsComboBox() {
        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();
        Methods defaultMethodToSelect = null;
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) cboMethods.getModel();

        dcbm.removeAllElements();

        Collections.sort(methodsList);


        for (Methods method : methodsList) {
            dcbm.addElement(method);
            if (method.getMid().equals(Methods.IMPORT_LIST_DEFAULT_METHOD)) {
                defaultMethodToSelect = method;
            }
        }

        // if default method found then select it
        if (defaultMethodToSelect != null) {
            dcbm.setSelectedItem(defaultMethodToSelect);
        }
    }

    public void readGermplasmList() {

        rowListExcel = new ArrayList<List<Object>>();
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        germplasmListReader.setSheetNumberForGermplasm(1);
        germplasmListReader.setEntryFactors(workbook.getEntryFactors());
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidTemplate(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }

        if (validFile) {
            try {
                GermplasmList germplasmList = germplasmListReader.getGermPlasmList(fileName);
                setGermplasmListIntoTable(germplasmList, tblEntries, 1, 0);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        }
        else {
            DialogUtil.displayError(bundle.getString("ImportGermplasmWizard.InvalidTemplate"));
        }
    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int opcion, int form) {


        List<String> columnList = gat.getColumnList(workbook.getFactors());


        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

        rowListExcel = gat.getMappedColumns(columnList, germplasmList);
        validateValuesForSource(rowListExcel);
        GermplasmEntriesTableModelChecks tableModel = null;
        tableModel = new GermplasmEntriesTableModelChecks(workbook.getFactors(), rowListExcel);


        //this.jTextFieldEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
        tabla.setModel(tableModel);
        tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }

    /**
     * Validates if source column is empty if source column is empty then uses
     * either list name from combo box or file name
     *
     * @param jtable
     */
    private void validateValuesForSource(List<List<Object>> dataList) {
        int SOURCE_COLUMN = 3;
        if (dataList.get(0).size() != 4) {
            return;
        }

        // get female list name
        String listName = "";

        listName = FileUtils.extractFileName(fileName);

        int rowNumber = 1;
        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
            List<Object> columnList = dataList.get(rowIndex);
            String sourceText = (String) columnList.get(SOURCE_COLUMN);

            if (sourceText == null) {
                columnList.set(SOURCE_COLUMN, listName + ":" + rowNumber);
            } else if (sourceText.isEmpty()) {
                columnList.set(SOURCE_COLUMN, listName + ":" + rowNumber);
            }
            rowNumber++;
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
    
    public List<Listdata> getListDataEntries() {
        List<Listdata> listDataEntries = new ArrayList<Listdata>();
        GermplasmEntriesTableModelChecks model = (GermplasmEntriesTableModelChecks)tblEntries.getModel();
        
        int gidCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.GID);
        int designationCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.DESIG);
        int entryCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.ENTRY);
        int entryCodeCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.ENTRY_CODE);
        int sourceCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.SOURCE);
        int crossCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.CROSS_NAME);
        if (crossCol == -1) {
            crossCol = model.getHeaderIndex(GermplasmEntriesTableModelChecks.CROSS);
        }        
        int entryNumber =1 ;
        for (int row=0; row < model.getRowCount(); row++ ) {
            ListdataPK lpk = new ListdataPK(0, entryNumber);
            Listdata ld = new Listdata();
            ld.setListdataPK(lpk);
            
            if (gidCol != -1) {
                ld.setGid(Integer.parseInt(model.getValueAt(row, gidCol).toString()));
            }
            if (designationCol != -1) {
                ld.setDesig(model.getValueAt(row, designationCol).toString());
            }
            if (entryCol != -1) {
                ld.setEntryid(Integer.parseInt(model.getValueAt(row, entryCol).toString()));
            }
            if (entryCodeCol == -1) {
                //ld.setEntrycd(model.getValueAt(row, entryCodeCol).toString());
                ld.setEntrycd(Listdata.ENTRY_PREFIX + ConvertUtils.getZeroLeading(entryNumber, 4));
            } else {
                ld.setEntrycd(model.getValueAt(row, entryCodeCol).toString());
            }
            if (sourceCol != -1) {
                ld.setSource(model.getValueAt(row, sourceCol).toString());
            }
            if (crossCol != -1) {
                ld.setGrpname(model.getValueAt(row, crossCol).toString());
            }
            ld.setHarvestDate(ConvertUtils.getDateAsInteger(dtDate.getDate()));
            ld.setLrstatus(Listdata.LRSTATUS_ACTIVE);      //*
            
            listDataEntries.add(ld);
            entryNumber++;
        }
        
        return listDataEntries;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radGrpInsertMethod = new javax.swing.ButtonGroup();
        pnlGermplasmInfo = new javax.swing.JPanel();
        lblBreedingMethod = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        lblNameType = new javax.swing.JLabel();
        dtDate = new com.toedter.calendar.JDateChooser();
        jTextFieldLocation = new javax.swing.JTextField();
        btnFindLocation = new javax.swing.JButton();
        cboMethods = new javax.swing.JComboBox();
        cboNameType = new javax.swing.JComboBox();
        pnlRadios = new javax.swing.JPanel();
        radNoPedigree = new javax.swing.JRadioButton();
        radConnectEntries = new javax.swing.JRadioButton();
        radOnlyMatch = new javax.swing.JRadioButton();
        pnlEntries = new javax.swing.JPanel();
        scrlEntries = new javax.swing.JScrollPane();
        tblEntries = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        pnlGermplasmInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.pnlGermplasmInfo.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblBreedingMethod, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.lblBreedingMethod.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblDate, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.lblDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblLocation, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.lblLocation.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblNameType, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.lblNameType.text")); // NOI18N

        dtDate.setToolTipText(org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.dtDate.toolTipText")); // NOI18N

        jTextFieldLocation.setText(org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.jTextFieldLocation.text")); // NOI18N

        btnFindLocation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/findLocation.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnFindLocation, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.btnFindLocation.text")); // NOI18N
        btnFindLocation.setToolTipText(org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.btnFindLocation.toolTipText")); // NOI18N
        btnFindLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindLocationActionPerformed(evt);
            }
        });

        cboMethods.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboNameType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlGermplasmInfoLayout = new javax.swing.GroupLayout(pnlGermplasmInfo);
        pnlGermplasmInfo.setLayout(pnlGermplasmInfoLayout);
        pnlGermplasmInfoLayout.setHorizontalGroup(
            pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBreedingMethod)
                    .addComponent(lblNameType)
                    .addComponent(lblDate)
                    .addComponent(lblLocation))
                .addGap(18, 18, 18)
                .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                        .addComponent(dtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(284, Short.MAX_VALUE))
                    .addComponent(cboNameType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                        .addComponent(jTextFieldLocation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFindLocation))
                    .addComponent(cboMethods, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlGermplasmInfoLayout.setVerticalGroup(
            pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                        .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBreedingMethod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDate))
                .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(lblLocation))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGermplasmInfoLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFindLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlGermplasmInfoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGermplasmInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameType)))
        );

        add(pnlGermplasmInfo, java.awt.BorderLayout.NORTH);

        pnlRadios.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        radGrpInsertMethod.add(radNoPedigree);
        radNoPedigree.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(radNoPedigree, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.radNoPedigree.text")); // NOI18N
        radNoPedigree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNoPedigreeActionPerformed(evt);
            }
        });

        radGrpInsertMethod.add(radConnectEntries);
        org.openide.awt.Mnemonics.setLocalizedText(radConnectEntries, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.radConnectEntries.text")); // NOI18N
        radConnectEntries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radConnectEntriesActionPerformed(evt);
            }
        });

        radGrpInsertMethod.add(radOnlyMatch);
        org.openide.awt.Mnemonics.setLocalizedText(radOnlyMatch, org.openide.util.NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.radOnlyMatch.text")); // NOI18N

        javax.swing.GroupLayout pnlRadiosLayout = new javax.swing.GroupLayout(pnlRadios);
        pnlRadios.setLayout(pnlRadiosLayout);
        pnlRadiosLayout.setHorizontalGroup(
            pnlRadiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRadiosLayout.createSequentialGroup()
                .addGroup(pnlRadiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radNoPedigree)
                    .addComponent(radConnectEntries)
                    .addComponent(radOnlyMatch))
                .addGap(0, 127, Short.MAX_VALUE))
        );
        pnlRadiosLayout.setVerticalGroup(
            pnlRadiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRadiosLayout.createSequentialGroup()
                .addComponent(radNoPedigree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radConnectEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radOnlyMatch))
        );

        add(pnlRadios, java.awt.BorderLayout.SOUTH);

        pnlEntries.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblEntries.setModel(new javax.swing.table.DefaultTableModel(
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
        scrlEntries.setViewportView(tblEntries);

        javax.swing.GroupLayout pnlEntriesLayout = new javax.swing.GroupLayout(pnlEntries);
        pnlEntries.setLayout(pnlEntriesLayout);
        pnlEntriesLayout.setHorizontalGroup(
            pnlEntriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlEntries, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        pnlEntriesLayout.setVerticalGroup(
            pnlEntriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlEntries, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );

        add(pnlEntries, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindLocationActionPerformed

        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();


        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(selectLocationPanel, NbBundle.getMessage(ImportGermplasmVisualPanel2.class, "ImportGermplasmVisualPanel2.chooseLocation"), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            String value = selectLocationPanel.getLocationName();
            //this.selectedLocation = selectLocationPanel.getSelectedLocation();
            this.jTextFieldLocation.setText(value);
        }
    }//GEN-LAST:event_btnFindLocationActionPerformed

    private void radConnectEntriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radConnectEntriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radConnectEntriesActionPerformed

    private void radNoPedigreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNoPedigreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNoPedigreeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindLocation;
    private javax.swing.JComboBox cboMethods;
    private javax.swing.JComboBox cboNameType;
    private com.toedter.calendar.JDateChooser dtDate;
    private javax.swing.JTextField jTextFieldLocation;
    private javax.swing.JLabel lblBreedingMethod;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblNameType;
    private javax.swing.JPanel pnlEntries;
    private javax.swing.JPanel pnlGermplasmInfo;
    private javax.swing.JPanel pnlRadios;
    private javax.swing.JRadioButton radConnectEntries;
    private javax.swing.ButtonGroup radGrpInsertMethod;
    private javax.swing.JRadioButton radNoPedigree;
    private javax.swing.JRadioButton radOnlyMatch;
    private javax.swing.JScrollPane scrlEntries;
    private javax.swing.JTable tblEntries;
    // End of variables declaration//GEN-END:variables
}

