/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.settings.folder.panels;

import ibfb.settings.core.FieldbookSettings;

import java.io.File;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.commongui.OSUtils;

public final class TemplatesandListsFoldersPanel extends javax.swing.JPanel {

    private final TemplatesandListsFoldersOptionsPanelController controller;

    TemplatesandListsFoldersPanel(TemplatesandListsFoldersOptionsPanelController controller) {
        this.controller = controller;
        initComponents();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTemplatesFolder = new javax.swing.JPanel();
        lblImgTemplates = new javax.swing.JLabel();
        btnSearchTempExpert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPathTempExpert = new javax.swing.JTextArea();
        btnUseDefault = new javax.swing.JButton();
        pnlFolderGermplasm = new javax.swing.JPanel();
        lblImgGermplasm = new javax.swing.JLabel();
        btnSearchGSMExpert = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPathGSMExpert = new javax.swing.JTextArea();
        btnUseDefaultGersmplasmFolder = new javax.swing.JButton();
        pnlSelectionFolder = new javax.swing.JPanel();
        lblImgSelection = new javax.swing.JLabel();
        btnSearchSelections = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPathSelections = new javax.swing.JTextArea();
        btnUseDefaultSelections = new javax.swing.JButton();
        pnlCrossesFolder = new javax.swing.JPanel();
        lbllImgCross = new javax.swing.JLabel();
        btnSearchCrosses = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPathCrosses = new javax.swing.JTextArea();
        btnUseDefaultCrosses = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblRIcon = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtRPath = new javax.swing.JTextArea();
        btnRBrowse = new javax.swing.JButton();
        btnRDefault = new javax.swing.JButton();

        pnlTemplatesFolder.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlTemplatesFolder.border.title"))); // NOI18N
        pnlTemplatesFolder.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlTemplatesFolder.toolTipText")); // NOI18N
        pnlTemplatesFolder.setMaximumSize(null);
        pnlTemplatesFolder.setPreferredSize(new java.awt.Dimension(550, 175));

        lblImgTemplates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/templates.png"))); // NOI18N
        lblImgTemplates.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.lblImgTemplates.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchTempExpert, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchTempExpert.text")); // NOI18N
        btnSearchTempExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTempExpertActionPerformed(evt);
            }
        });

        txtPathTempExpert.setColumns(20);
        txtPathTempExpert.setEditable(false);
        txtPathTempExpert.setLineWrap(true);
        txtPathTempExpert.setRows(5);
        txtPathTempExpert.setMaximumSize(new java.awt.Dimension(240, 80));
        txtPathTempExpert.setMinimumSize(new java.awt.Dimension(240, 80));
        txtPathTempExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathTempExpertMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtPathTempExpert);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefault, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefault.text")); // NOI18N
        btnUseDefault.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefault.toolTipText")); // NOI18N
        btnUseDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTemplatesFolderLayout = new javax.swing.GroupLayout(pnlTemplatesFolder);
        pnlTemplatesFolder.setLayout(pnlTemplatesFolderLayout);
        pnlTemplatesFolderLayout.setHorizontalGroup(
            pnlTemplatesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTemplatesFolderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgTemplates)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTemplatesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchTempExpert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefault, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTemplatesFolderLayout.setVerticalGroup(
            pnlTemplatesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImgTemplates)
            .addGroup(pnlTemplatesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(pnlTemplatesFolderLayout.createSequentialGroup()
                    .addComponent(btnSearchTempExpert)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefault))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlFolderGermplasm.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlFolderGermplasm.border.title"))); // NOI18N
        pnlFolderGermplasm.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlFolderGermplasm.toolTipText")); // NOI18N

        lblImgGermplasm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/germplasm.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchGSMExpert, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchGSMExpert.text")); // NOI18N
        btnSearchGSMExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGSMExpertActionPerformed(evt);
            }
        });

        txtPathGSMExpert.setColumns(20);
        txtPathGSMExpert.setEditable(false);
        txtPathGSMExpert.setLineWrap(true);
        txtPathGSMExpert.setRows(5);
        txtPathGSMExpert.setMaximumSize(null);
        txtPathGSMExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathGSMExpertMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtPathGSMExpert);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefaultGersmplasmFolder, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultGersmplasmFolder.text")); // NOI18N
        btnUseDefaultGersmplasmFolder.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultGersmplasmFolder.toolTipText")); // NOI18N
        btnUseDefaultGersmplasmFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultGersmplasmFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFolderGermplasmLayout = new javax.swing.GroupLayout(pnlFolderGermplasm);
        pnlFolderGermplasm.setLayout(pnlFolderGermplasmLayout);
        pnlFolderGermplasmLayout.setHorizontalGroup(
            pnlFolderGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFolderGermplasmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgGermplasm)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFolderGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchGSMExpert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefaultGersmplasmFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFolderGermplasmLayout.setVerticalGroup(
            pnlFolderGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFolderGermplasmLayout.createSequentialGroup()
                .addComponent(btnSearchGSMExpert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUseDefaultGersmplasmFolder))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(lblImgGermplasm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlSelectionFolder.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlSelectionFolder.border.title"))); // NOI18N
        pnlSelectionFolder.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlSelectionFolder.toolTipText")); // NOI18N

        lblImgSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/selection.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchSelections, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchSelections.text")); // NOI18N
        btnSearchSelections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSelectionsActionPerformed(evt);
            }
        });

        txtPathSelections.setColumns(20);
        txtPathSelections.setEditable(false);
        txtPathSelections.setLineWrap(true);
        txtPathSelections.setRows(5);
        txtPathSelections.setMaximumSize(null);
        txtPathSelections.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathSelectionsMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(txtPathSelections);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefaultSelections, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultSelections.text")); // NOI18N
        btnUseDefaultSelections.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultSelections.toolTipText")); // NOI18N
        btnUseDefaultSelections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultSelectionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectionFolderLayout = new javax.swing.GroupLayout(pnlSelectionFolder);
        pnlSelectionFolder.setLayout(pnlSelectionFolderLayout);
        pnlSelectionFolderLayout.setHorizontalGroup(
            pnlSelectionFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectionFolderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlSelectionFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchSelections, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(btnUseDefaultSelections, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlSelectionFolderLayout.setVerticalGroup(
            pnlSelectionFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectionFolderLayout.createSequentialGroup()
                .addComponent(btnSearchSelections)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUseDefaultSelections))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblImgSelection, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pnlCrossesFolder.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlCrossesFolder.border.title"))); // NOI18N
        pnlCrossesFolder.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.pnlCrossesFolder.toolTipText")); // NOI18N

        lbllImgCross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/cross.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchCrosses, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchCrosses.text")); // NOI18N
        btnSearchCrosses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCrossesActionPerformed(evt);
            }
        });

        txtPathCrosses.setColumns(20);
        txtPathCrosses.setEditable(false);
        txtPathCrosses.setLineWrap(true);
        txtPathCrosses.setRows(5);
        txtPathCrosses.setMaximumSize(null);
        txtPathCrosses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathCrossesMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(txtPathCrosses);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefaultCrosses, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultCrosses.text")); // NOI18N
        btnUseDefaultCrosses.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultCrosses.toolTipText")); // NOI18N
        btnUseDefaultCrosses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultCrossesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCrossesFolderLayout = new javax.swing.GroupLayout(pnlCrossesFolder);
        pnlCrossesFolder.setLayout(pnlCrossesFolderLayout);
        pnlCrossesFolderLayout.setHorizontalGroup(
            pnlCrossesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrossesFolderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbllImgCross, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCrossesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchCrosses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefaultCrosses))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlCrossesFolderLayout.setVerticalGroup(
            pnlCrossesFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCrossesFolderLayout.createSequentialGroup()
                .addComponent(btnSearchCrosses)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUseDefaultCrosses))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(lbllImgCross, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jPanel1.border.title"))); // NOI18N

        lblRIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/R32.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblRIcon, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.lblRIcon.text")); // NOI18N
        lblRIcon.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.lblRIcon.toolTipText")); // NOI18N

        txtRPath.setColumns(20);
        txtRPath.setRows(5);
        jScrollPane5.setViewportView(txtRPath);

        org.openide.awt.Mnemonics.setLocalizedText(btnRBrowse, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnRBrowse.text")); // NOI18N
        btnRBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRBrowseActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnRDefault, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnRDefault.text")); // NOI18N
        btnRDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRIcon)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRDefault, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRIcon)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(btnRBrowse)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRDefault))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlCrossesFolder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFolderGermplasm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTemplatesFolder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(pnlSelectionFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTemplatesFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFolderGermplasm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCrossesFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSelectionFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchTempExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTempExpertActionPerformed
        selectFolderForTemplates();
}//GEN-LAST:event_btnSearchTempExpertActionPerformed

    private void txtPathTempExpertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathTempExpertMousePressed
        selectFolderForTemplates();

}//GEN-LAST:event_txtPathTempExpertMousePressed

    private void btnUseDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultActionPerformed
        txtPathTempExpert.setText(OSUtils.getTemplatesPath());
}//GEN-LAST:event_btnUseDefaultActionPerformed

    private void btnSearchGSMExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGSMExpertActionPerformed
        selectFolderForGermplasmLists();
    }//GEN-LAST:event_btnSearchGSMExpertActionPerformed

    private void txtPathGSMExpertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathGSMExpertMousePressed
        selectFolderForGermplasmLists();
}//GEN-LAST:event_txtPathGSMExpertMousePressed

    private void btnUseDefaultGersmplasmFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultGersmplasmFolderActionPerformed
        this.txtPathGSMExpert.setText(OSUtils.getGermplasmListsPath());
    }//GEN-LAST:event_btnUseDefaultGersmplasmFolderActionPerformed

    private void btnSearchCrossesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCrossesActionPerformed
       selectFolderForCrosses();
    }//GEN-LAST:event_btnSearchCrossesActionPerformed

    private void txtPathCrossesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathCrossesMousePressed
        selectFolderForCrosses();
    }//GEN-LAST:event_txtPathCrossesMousePressed

    private void btnUseDefaultCrossesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultCrossesActionPerformed
        this.txtPathCrosses.setText(OSUtils.getCrossesFilesPath());
    }//GEN-LAST:event_btnUseDefaultCrossesActionPerformed

    private void btnSearchSelectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSelectionsActionPerformed
        selectFolderForSelections();
    }//GEN-LAST:event_btnSearchSelectionsActionPerformed

    private void txtPathSelectionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathSelectionsMousePressed
        selectFolderForSelections();
    }//GEN-LAST:event_txtPathSelectionsMousePressed

    private void btnUseDefaultSelectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultSelectionsActionPerformed
        this.txtPathSelections.setText(OSUtils.getSelectionsFilesPath());
    }//GEN-LAST:event_btnUseDefaultSelectionsActionPerformed

    private void btnRBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRBrowseActionPerformed
       selectFolderForR();
    }//GEN-LAST:event_btnRBrowseActionPerformed

    private void btnRDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRDefaultActionPerformed
       txtRPath.setText(OSUtils.getRPATH());
    }//GEN-LAST:event_btnRDefaultActionPerformed

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(TemplatesandListsFoldersPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(TemplatesandListsFoldersPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        txtPathGSMExpert.setText(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER, ""));
        txtPathTempExpert.setText(FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER, ""));
        txtPathCrosses.setText(FieldbookSettings.getCrossesDefaultFolder());
        txtPathSelections.setText(FieldbookSettings.getSelectionsDefaultFolder());
        txtRPath.setText(FieldbookSettings.getSetting(FieldbookSettings.R_HOME_FOLDER));
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(TemplatesandListsFoldersPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(TemplatesandListsFoldersPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        FieldbookSettings.setSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER, txtPathTempExpert.getText());
        FieldbookSettings.setSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER, txtPathGSMExpert.getText());
        FieldbookSettings.setSetting(FieldbookSettings.CROSSES_DEFAULT_FOLDER, txtPathCrosses.getText());
        FieldbookSettings.setSetting(FieldbookSettings.SELECTION_DEFAULT_FOLDER, txtPathSelections.getText());
        FieldbookSettings.setSetting(FieldbookSettings.R_HOME_FOLDER,txtRPath.getText());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    private void selectFolderForTemplates() {
        txtPathTempExpert.setText(FileUtils.getSelectedFolder(txtPathTempExpert.getText()));
    }

    private void selectFolderForGermplasmLists() {
        txtPathGSMExpert.setText(FileUtils.getSelectedFolder(txtPathGSMExpert.getText()));
    }
    
    private void selectFolderForCrosses() {
        txtPathCrosses.setText(FileUtils.getSelectedFolder(txtPathCrosses.getText()));
    }
    
    private void selectFolderForSelections() {
        txtPathSelections.setText(FileUtils.getSelectedFolder(txtPathSelections.getText()));
    }
    
    private void selectFolderForR() {
        txtRPath.setText(FileUtils.getSelectedFolder(txtRPath.getText()));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRBrowse;
    private javax.swing.JButton btnRDefault;
    private javax.swing.JButton btnSearchCrosses;
    private javax.swing.JButton btnSearchGSMExpert;
    private javax.swing.JButton btnSearchSelections;
    private javax.swing.JButton btnSearchTempExpert;
    private javax.swing.JButton btnUseDefault;
    private javax.swing.JButton btnUseDefaultCrosses;
    private javax.swing.JButton btnUseDefaultGersmplasmFolder;
    private javax.swing.JButton btnUseDefaultSelections;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblImgGermplasm;
    private javax.swing.JLabel lblImgSelection;
    private javax.swing.JLabel lblImgTemplates;
    private javax.swing.JLabel lblRIcon;
    private javax.swing.JLabel lbllImgCross;
    private javax.swing.JPanel pnlCrossesFolder;
    private javax.swing.JPanel pnlFolderGermplasm;
    private javax.swing.JPanel pnlSelectionFolder;
    private javax.swing.JPanel pnlTemplatesFolder;
    private javax.swing.JTextArea txtPathCrosses;
    private javax.swing.JTextArea txtPathGSMExpert;
    private javax.swing.JTextArea txtPathSelections;
    private javax.swing.JTextArea txtPathTempExpert;
    private javax.swing.JTextArea txtRPath;
    // End of variables declaration//GEN-END:variables
}
