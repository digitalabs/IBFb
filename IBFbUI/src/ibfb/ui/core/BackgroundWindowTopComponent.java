package ibfb.ui.core;

import ibfb.ui.actions.ShowTutorialAction;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.commongui.AppConstants;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.actions.SystemAction;

@ConvertAsProperties(dtd = "-//ibfb.ui.core//BackgroundWindow//EN",
autostore = false)
@TopComponent.Description(preferredID = "BackgroundWindowTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "ibfb.ui.core.BackgroundWindowTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_BackgroundWindowAction",
preferredID = "BackgroundWindowTopComponent")
public final class BackgroundWindowTopComponent extends TopComponent {
    
    private static final String FIELDBOOK_TUTORIAL_FILE_NAME = "Tutorial_for_using_the_Integrated_Breeding_Fieldbook";    
    private static final String BREEDING_TUTORIAL_FILE_NAME = "Tutorial_for_using_the_Integrated_BreedingManager";    
    private static final String PDF_EXT = ".pdf";
    private ResourceBundle bundle = NbBundle.getBundle(BackgroundWindowTopComponent.class);
    
    public BackgroundWindowTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(BackgroundWindowTopComponent.class, "CTL_BackgroundWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(BackgroundWindowTopComponent.class, "HINT_BackgroundWindowTopComponent"));
        jLabel1.setText(bundle.getString("BackgroundWindowTopComponent.desc"));
        loadImage();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnBreedingTutorial = new javax.swing.JButton();
        btnFieldbookTutorial = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton3.text")); // NOI18N

        setLayout(new java.awt.BorderLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/splash.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jLabel6.text")); // NOI18N

        btnBreedingTutorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/tutorialbreeding.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnBreedingTutorial, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.btnBreedingTutorial.text")); // NOI18N
        btnBreedingTutorial.setToolTipText(org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.btnBreedingTutorial.toolTipText")); // NOI18N
        btnBreedingTutorial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBreedingTutorial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBreedingTutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBreedingTutorialActionPerformed(evt);
            }
        });

        btnFieldbookTutorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/tutorialfieldbook.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnFieldbookTutorial, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.btnFieldbookTutorial.text")); // NOI18N
        btnFieldbookTutorial.setToolTipText(org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.btnFieldbookTutorial.toolTipText")); // NOI18N
        btnFieldbookTutorial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFieldbookTutorial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFieldbookTutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldbookTutorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBreedingTutorial)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnFieldbookTutorial, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBreedingTutorial)
                .addGap(66, 66, 66))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnFieldbookTutorial)
                    .addContainerGap(170, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBreedingTutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBreedingTutorialActionPerformed
        openBreedingTutorial();
    }//GEN-LAST:event_btnBreedingTutorialActionPerformed
    
    private void btnFieldbookTutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldbookTutorialActionPerformed
        openFieldbookTutorial();
    }//GEN-LAST:event_btnFieldbookTutorialActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBreedingTutorial;
    private javax.swing.JButton btnFieldbookTutorial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }
    
    @Override
    public void componentClosed() {
    }
    
    void writeProperties(java.util.Properties p) {
        
        p.setProperty("version", "1.0");
        
    }
    
    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
    
    private void openFieldbookTutorial() {
        openTutorial(FIELDBOOK_TUTORIAL_FILE_NAME);
    }
    
    private void openBreedingTutorial() {
        openTutorial(BREEDING_TUTORIAL_FILE_NAME);
    }
    
    private void openTutorial(String fileName) {
        
        String language = System.getProperty("user.language");
        //String pdfFileName = OSUtils.getDocumentsPath() + File.separator + TUTORIAL_FILE_NAME + PDF_EXT;
        String pdfFileName = OSUtils.getDocumentsPath() + File.separator + fileName + PDF_EXT;
        if (language.equals("es")) {
            pdfFileName = OSUtils.getDocumentsPath() + File.separator + fileName + "_es" + PDF_EXT;
        }
        try {
            
            File pdfFile = new File(pdfFileName);
            if (pdfFile.exists()) {
                
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }
                
            } else {
                System.out.println("File is not exists!");
            }
            
            System.out.println("Done");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadImage() {
        if (AppConstants.bothApps()) {
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/splash.png"))); // NOI18N
        } else if (AppConstants.onlyBreeding()) {
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/splashBreedingManager.png"))); // NOI18N
        } else if (AppConstants.onlyFieldbook()) {
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/splashFieldbook.png"))); // NOI18N
        }
        
        btnBreedingTutorial.setVisible(AppConstants.bothApps() || AppConstants.onlyBreeding());
        btnFieldbookTutorial.setVisible(AppConstants.bothApps() || AppConstants.onlyFieldbook());
        
        
    }
}
