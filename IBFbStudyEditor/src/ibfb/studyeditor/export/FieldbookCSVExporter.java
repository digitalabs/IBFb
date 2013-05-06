
package ibfb.studyeditor.export;

import com.csvreader.CsvWriter;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.studyeditor.roweditors.CSVFileManager;
import ibfb.studyeditor.roweditors.CSVOziel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JTable;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class FieldbookCSVExporter {

    private static ResourceBundle bundle = NbBundle.getBundle(FieldbookCSVExporter.class);

    public static void exportToFieldlog(JTable jTableObservations, String trialFile, CSVFileManager csv, int triallOption,
            int trialStart, int trialEnd, int trialSelected) {
        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();

        ObservationsTableModel modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());

        if (triallOption == 0) {
            modeloFilter = modeloOriginal;
        }


        if (triallOption == 1) {

            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }

            }
        }


        if (triallOption == 2) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                if (trialNum >= trialStart && trialNum <= trialEnd) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }
            }
        }

        Export_to_fieldlog(new File(trialFile), modeloFilter, csv);


        String fileSaved = bundle.getString("StudyEditorExport.dataSavedTo");
        fileSaved = fileSaved + " " + trialFile + bundle.getString("StudyEditorExport.dataSavedToEnd");

        if (DialogUtil.displayConfirmation(fileSaved, bundle.getString("StudyEditorExport.fileExported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
            try {

                if (Desktop.isDesktopSupported() == true) {
                    Desktop desktop = Desktop.getDesktop();
                    //String folderLocation = OSUtils.extractFilePath(trialFile + ".csv");
                    //File theFile = new File(folderLocation);
                    File theFile = new File(trialFile + ".csv");
                    desktop.open(theFile);
                }
            } catch (Exception e) {
                System.out.println("Error opening file");
            }
        }
    }

    public static void Export_to_fieldlog(File file, ObservationsTableModel modeloFiltro, CSVFileManager csv) {
        String outputFile = file.toString() + ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, false), ',');
            csvOutput.write("Trial");
            csvOutput.write("Rep");
            csvOutput.write("Block");
            csvOutput.write("Plot");
            csvOutput.write("Entry");
            csvOutput.write("StockID");
            csvOutput.write("Name");
            csvOutput.write("BreedersPedigree1");
            csv.writeColums(csvOutput, 6);
            csvOutput.write("Origin");
            csv.writeColums(csvOutput, 8);
            csvOutput.write("GID");
            csv.writeColums(csvOutput, 2);
            csv.writeTraitsFromObservations(csvOutput, modeloFiltro);
            csvOutput.endRecord();
            csv.writeRows(csvOutput, 23);
            csv.writeDATA(csvOutput, modeloFiltro);
            csvOutput.close();
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR CVS fieldlog");
        }
    }
    
  
    /*
     * Exporta el trial al formato de Data Kapture
     * @author Raul Hernandezs Toledo
     * @since 1 01/03/2013
     */    
    public static void exportToDataKapture(JTable jTableObservations, String trialFile, CSVFileManager csv, int triallOption,
            int trialStart, int trialEnd, int trialSelected){
        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();

        ObservationsTableModel modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());

        if (triallOption == 0) {
            modeloFilter = modeloOriginal;
        }


        if (triallOption == 1) {

            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }

            }
        }

        if (triallOption == 2) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                if (trialNum >= trialStart && trialNum <= trialEnd) {
                    List<Object> row = modeloOriginal.getRow(i);
                    System.out.println(row.get(i).toString());
                    modeloFilter.addRow(row);
                }
            }
        }

        exportToDataKaptureLayout(new File(trialFile), modeloFilter, csv);

        String fileSaved = bundle.getString("StudyEditorExport.dataSavedTo");
        fileSaved = fileSaved + " " + trialFile + ".csv " +bundle.getString("StudyEditorExport.dataSavedToEnd");        
        
        if (DialogUtil.displayConfirmation(fileSaved, bundle.getString("StudyEditorExport.fileExported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
            try {

                if (Desktop.isDesktopSupported() == true) {
                    Desktop desktop = Desktop.getDesktop();
                    
                    File theFile = new File(trialFile + ".csv");
                    desktop.open(theFile);

                }
            } catch (Exception e) {
                System.out.println("Error opening file");
            }
        }
    }  
    
    /*
     * Exporta el trial al formato de Data Kapture
     * @author Raul Hernandezs Toledo
     * @since 1 01/03/2013
     */    
    public static void exportToDataKaptureLayout(File file, ObservationsTableModel modeloFiltro,CSVFileManager csv){
        String outputFile = file.toString() + ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, false), ',');
            csvOutput.write("Site");
            csvOutput.write("Type");
            csvOutput.write("Year");
            csvOutput.write("TrialNumber");
            csvOutput.write("Row");
            csvOutput.write("Column");
            csvOutput.write("PlotBarCode");
            csvOutput.write("GID");
            csvOutput.write("Genotype");
            csvOutput.write("Pedigree");
            csvOutput.write("Rep");
            csv.writeTraitsFromObservationsDK(csvOutput, modeloFiltro);
            csvOutput.endRecord();
            csv.writeDataDataKapture(csvOutput, modeloFiltro);
            csvOutput.close();
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR CVS fieldlog");
        }        
    }    
    
    /**
     * Export the trial to CSV
     * @author Raul Hernandez Toledo
     * @since 1.0
    */    
    public static void exportToDataKaptureTraits(JTable jTableObservations, String trialFile, CSVFileManager csv, int triallOption,
            int trialStart, int trialEnd, int trialSelected){
        
        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();
        ObservationsTableModel modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());

        if (triallOption == 0) 
            modeloFilter = modeloOriginal;
        
        if (triallOption == 1) {

            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }
            }
        }

        if (triallOption == 2) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                if (trialNum >= trialStart && trialNum <= trialEnd) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }
            }
        }

        exportToDataKaptureTraitLayout(new File(trialFile), modeloFilter, csv);

        String fileSaved = bundle.getString("StudyEditorExport.traitSavedTo");
        fileSaved = fileSaved + " " + trialFile + "Traits.cvs " + bundle.getString("StudyEditorExport.dataSavedToEnd");

        if (DialogUtil.displayConfirmation(fileSaved, bundle.getString("StudyEditorExport.fileExported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
            try {

                if (Desktop.isDesktopSupported() == true) {
                    Desktop desktop = Desktop.getDesktop();
                    File theFile = new File(trialFile + "Traits"+ ".csv");
                    desktop.open(theFile);
                }
            } catch (Exception e) {
                System.out.println("Error opening file");
            }
        }        
    }
    
    /*
     * Exporta los Traits del Trial al formato de Data Kapture
     * @author Raul Hernandezs Toledo
     * @since 1 01/03/2013
     */    
    public static void exportToDataKaptureTraitLayout(File file, ObservationsTableModel modeloFiltro,CSVFileManager csv){
        String outputFile = file.toString() + "Traits" + ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, false), ',');
            csvOutput.write("TraitName");
            csvOutput.write("TraitValRule");
            csvOutput.write("DataType");
            csvOutput.write("AutoProgressFieldLength");
            csvOutput.write("IsDaysTrait");
            csvOutput.write("DateStamp");
            csvOutput.write("TraitUnits");
            csvOutput.write("Connection");
            csvOutput.endRecord();
            csv.writeTraitsDataKapture(csvOutput, modeloFiltro);
            csvOutput.close();
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR CVS fieldlog");
        }        
    }        
    
}
