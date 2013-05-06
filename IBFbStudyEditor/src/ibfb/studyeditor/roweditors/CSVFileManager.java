package ibfb.studyeditor.roweditors;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import ibfb.domain.core.Condition;
import ibfb.domain.core.Study;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsScaleCon;
import org.cimmyt.cril.ibwb.domain.TmsScaleDis;

public class CSVFileManager {

    private JTable jTableObservations;
    private JList lista;
    private DefaultListModel listModel = new DefaultListModel();
    private String stringTraitToEvaluate = "GY";

    public CSVFileManager(JTable jTableObservations, JList list) {
        this.jTableObservations = jTableObservations;
        this.lista = list;
    }

    public void writeColums(CsvWriter csvOutput, int columnas) {
        for (int i = 0; i < columnas; i++) {
            String cad = null;
            try {
                csvOutput.write(cad);
            } catch (IOException ex) {
                System.out.println("ERROR AL GENERAR COLUMNS CSV");
            }

        }
    }

    public void writeRows(CsvWriter csvOutput, int rows) {
        try {
            for (int j = 0; j < rows; j++) {
                writeColums(csvOutput, 129);
                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR ROWS CSV " + ex);
        }
    }

    public void writeTraits(CsvWriter csvOutput) {
        try {
            listModel = (DefaultListModel) lista.getModel();
            int tot = listModel.size();

            for (int i = 0; i < tot; i++) {
                String cadena = listModel.getElementAt(i).toString();
                int espacio = cadena.indexOf("(");
                String valor = cadena.substring(0, espacio - 1).trim();
                csvOutput.write(valor);
            }


            writeColums(csvOutput, 104 - tot);
            csvOutput.write("IBFB");


        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR TRAITS CSV " + ex);
        }
    }

    public void writeTraitsFromObservations(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        try {
            int tot = 0;

            for (Variate variate : tableModel.getVariateList()) {
                csvOutput.write(variate.getVariateName());
                tot++;
            }

            writeColums(csvOutput, 104 - tot);
            csvOutput.write("IBFB");


        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR TRAITS CSV " + ex);
        }
    }

    /**
     * Print variates in excel file to DataKapture
     *
     * @param csvOutput
     * @param tableModel
     */
    public void writeTraitsFromObservationsDK(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        try {
            int tot = 0;

            for (Variate variate : tableModel.getVariateList()) {
                csvOutput.write(variate.getVariateName());
                tot++;
            }

        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR TRAITS CSV " + ex);
        }
    }

    public void writeTraitsR(CsvWriter csvOutput) {
        try {

            listModel = (DefaultListModel) lista.getModel();
            int tot = listModel.size();

            for (int i = 0; i < tot; i++) {
                String cadena = listModel.getElementAt(i).toString();
                int espacio = cadena.indexOf("(");
                String valor = cadena.substring(0, espacio - 1).trim();
                if (!valor.equals(stringTraitToEvaluate)) {
                    csvOutput.write(valor);
                }
            }

        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR TRAITS CSV " + ex);
        }
    }

    public void writeTraitsR(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        try {
            int tot = tableModel.getVariateList().size();

            for (int i = 0; i < tot; i++) {
                String valor = tableModel.getVariateList().get(i).getVariateName();
                if (!valor.equals(stringTraitToEvaluate)) {

                    if (valor.isEmpty()) {
                        valor = ".";
                    }
                    csvOutput.write(valor);
                }
            }

        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR TRAITS CSV " + ex);
        }
    }

    public void writeDATA(CsvWriter csvOutput, DefaultTableModel modeloFiltro) {
        int total = modeloFiltro.getRowCount();
        int tot = listModel.size();
        try {
            for (int i = 0; i < total; i++) {

                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("TRIAL")).toString());
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("REP")).toString());
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("BLOCK")).toString());
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("PLOT")).toString());
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("ENTRY")).toString());
                writeColums(csvOutput, 2);
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("DESIG")).toString());
                writeColums(csvOutput, 15);
                csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn("GID")).toString());
                writeColums(csvOutput, 2);

                for (int j = 0; j < tot; j++) {
                    String cadena = listModel.getElementAt(j).toString();
                    int espacio = cadena.indexOf("(");
                    String valor = cadena.substring(0, espacio - 1).trim();


                    try {
                        csvOutput.write(modeloFiltro.getValueAt(i, modeloFiltro.findColumn(valor)).toString());
                    } catch (NullPointerException ex) {
                        String cad = ".";
                        csvOutput.write(cad);
                    }
                }

                writeColums(csvOutput, 104 - tot);
                csvOutput.write("END");
                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV " + ex);
        }

    }

    /*
     * Exporta un Nursery al formato de Data Kapture @author Raul Hernandezs
     * Toledo @since 1 01/03/2013
     */
    public void writeDataDataKapture(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        int total = tableModel.getRowCount();
        int tot = tableModel.getVariateList().size();
        String strStudyType = "";
        String trialNumber = "";
        String strLocationName = "";
        String strCycle = "";

        /**
         * Type
         */
        Workbook wb = tableModel.getWorkbook();
        Study st = wb.getStudy();
        strStudyType = st.getStudyType();


        /**
         * TrialNumber, Location Name, Cycle
         */
        Iterator l = wb.getStudyConditions().iterator();
        while (l.hasNext()) {
            Condition c = (Condition) l.next();
            if ("TID".equalsIgnoreCase(c.getConditionName())) {
                trialNumber = c.getValue().toString();
            }

            if ("LID".equalsIgnoreCase(c.getConditionName())) {
                strLocationName = c.getValue().toString();
            }

            if ("Cycle".equalsIgnoreCase(c.getConditionName())) {
                strCycle = c.getValue().toString();
            }

        }

        int gidColumn = tableModel.getHeaderIndex(ObservationsTableModel.GID);
        int plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT);
        int pedigreeColumn = tableModel.getHeaderIndex(ObservationsTableModel.CROSSNAME);
        int occColumn = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
        int numVariates = wb.getVariates().size();

        // if plotColumn not found
        if (plotColumn == -1) {
            plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOTNUMBER);
        }

        // if plotColumn not found
        if (plotColumn == -1) {
            plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT_NESTED);
        }

        // search for pedigree using "Designation"
        if (pedigreeColumn == -1) {
            pedigreeColumn = tableModel.getHeaderIndex(ObservationsTableModel.DESIG);
        }

        try {
            for (int i = 0; i < total; i++) {

                /**
                 * Site
                 */
                csvOutput.write(strLocationName);
                /**
                 * Type
                 */
                csvOutput.write(strStudyType);
                /**
                 * Year (cycle)
                 */
                csvOutput.write(strCycle);
                /**
                 * TrialNumber
                 */
                csvOutput.write(trialNumber);

                /*
                 * El row y column es una manera de dividir el campo como un
                 * plano cartesiano. Esa manera solo aplica en experimentos de
                 * otros paises. Aqui no se maneja de la misma forma. Mientras
                 * no haya forma de realizarlo se imprime una constante.
                 */
                csvOutput.write("1");             //row
                csvOutput.write("1");             //column 

                /**
                 * plotBarCode
                 */
                csvOutput.write(tableModel.getValueAt(i, plotColumn).toString());

                /**
                 * GID
                 */
                csvOutput.write(tableModel.getValueAt(i, gidColumn).toString());

                /**
                 * Genotype
                 */
                //es el Nombre, no necesariament se encuentra en las entradas del germoplasma
                csvOutput.write("");

                /**
                 * Pedigree
                 */
                csvOutput.write(tableModel.getValueAt(i, pedigreeColumn).toString());

                /**
                 * Rep
                 */
                //Actualmente se guarda la ocurrencia, pero no necesariamente debe ser asi.
                //se acordo con Celso dejar ahi la occurencia.
                csvOutput.write(tableModel.getValueAt(i, occColumn).toString());

                for (int z = 0; z < numVariates; z++) {
                    csvOutput.write("");
                }

                //csvOutput.write("END");
                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV " + ex);
        }
    }

    /*
     * Exporta los traits de un Trial al formato de Data Kapture @author Raul
     * Hernandezs Toledo @since 1 01/03/2013
     */
    public void writeTraitsDataKapture(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        int total = tableModel.getRowCount();
        int tot = tableModel.getVariateList().size();
        String strTraitName = "";
        String strTrailValRule = "";
        String strDataType = "";
        //String strAutoProgress = "";
        //String strIsDayTrait   = "";
        //String strDateStamp    = "";
        //String strTraitUnits   = "";
        //String strConnection   = "";    

        /**
         * Type
         */
        Workbook wb = tableModel.getWorkbook();

        try {

            Iterator l = wb.getVariates().iterator();

            while (l.hasNext()) {

                Variate o = (Variate) l.next();
                strDataType = o.getDataType();
                strTraitName = o.getVariateName();


                /**
                 * Trait Name
                 */
                csvOutput.write(strTraitName);

                /**
                 * Trait Value Rule
                 */
                org.cimmyt.cril.ibwb.domain.Variate v = AppServicesProxy.getDefault().appServices().getVariate(o.getVariateId().intValue());
                List<Measuredin> listV = AppServicesProxy.getDefault().appServices().getMeasuredInListByTrait(v.getTid());

                /**
                 * Search in all results
                 */
                Iterator i = listV.iterator();
                while (i.hasNext()) {
                    Measuredin obj = (Measuredin) i.next();
                    if (v.getScaleid().equals(obj.getScaleid())) {
                        if (v.getTmethid().equals(obj.getTmethid())) {
                            if (obj.getScales().getSctype().equals(Scales.SCALE_TYPE_CONTINOUS)) {
                                TmsScaleCon tm = (TmsScaleCon) obj.getTmsScaleDef();

                                if (tm.getSlevel() != null) {
                                    strTrailValRule = tm.getSlevel().toString();
                                }

                                if (tm.getElevel() != null) {
                                    strTrailValRule.concat(".." + tm.getElevel().toString());
                                }

                            } else {
                                TmsScaleDis tm = (TmsScaleDis) obj.getTmsScaleDef();
                                strTrailValRule = tm.getValue();
                            }
                        }
                    }
                }
                /*
                 *
                 */

                csvOutput.write(strTrailValRule);
                /**
                 * Data Type
                 */
                csvOutput.write(strDataType);

                /**
                 * Auto Progress Field Length
                 */
                csvOutput.write("1");
                /**
                 * Is Days Trait
                 */
                csvOutput.write("1");
                /**
                 * DateStamp
                 */
                csvOutput.write("1");
                /**
                 * Trait Units
                 */
                csvOutput.write("1");
                /**
                 * Connection
                 */
                csvOutput.write("0");

                //csvOutput.write("END");
                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("Error al generar el archivo csv: " + ex);
        }
    }

    public void writeDATA(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        int total = tableModel.getRowCount();
        int tot = tableModel.getVariateList().size();

        int trialColumn = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
        int repColumn = tableModel.getHeaderIndex(ObservationsTableModel.REPLICATION);
        int blockColumn = tableModel.getHeaderIndex(ObservationsTableModel.BLOCK);
        int plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT);
        int entryColumn = tableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
        int designColumn = tableModel.getHeaderIndex(ObservationsTableModel.DESIG);
        int gidColumn = tableModel.getHeaderIndex(ObservationsTableModel.GID);
        if (plotColumn == -1) {
            plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT_NESTED);
        }
        if (plotColumn == -1) {
            plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOTNUMBER);
        }
        try {
            for (int i = 0; i < total; i++) {

                csvOutput.write(tableModel.getValueAt(i, trialColumn).toString());
                csvOutput.write(tableModel.getValueAt(i, repColumn).toString());
                csvOutput.write(tableModel.getValueAt(i, blockColumn).toString());
                csvOutput.write(tableModel.getValueAt(i, plotColumn).toString());
                csvOutput.write(tableModel.getValueAt(i, entryColumn).toString());
                writeColums(csvOutput, 2);
                csvOutput.write(tableModel.getValueAt(i, designColumn).toString());
                writeColums(csvOutput, 15);
                csvOutput.write(tableModel.getValueAt(i, gidColumn).toString());
                writeColums(csvOutput, 2);



                for (int j = 0; j < tot; j++) {
                    String valor = tableModel.getVariateList().get(j).getVariateName();
                    try {
                        csvOutput.write(tableModel.getValueAt(i, tableModel.findColumn(valor)).toString());
                    } catch (NullPointerException ex) {
                        String cad = null;
                        csvOutput.write(cad);
                    }

                }

                writeColums(csvOutput, 104 - tot);
                csvOutput.write("END");
                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV " + ex);
        }

    }

    public void writeDATAR(CsvWriter csvOutput, DefaultTableModel modeloFilter) {

        int total = modeloFilter.getRowCount();
        int tot = listModel.size();

        try {


            for (int i = 0; i < total; i++) {

                csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn("TRIAL")).toString());
                csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn("REP")).toString());
                csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn("BLOCK")).toString());
                csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn("ENTRY")).toString());
                try {
                    csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn(stringTraitToEvaluate)).toString());
                } catch (NullPointerException ex) {
                    String cad = null;
                    csvOutput.write(cad);
                }


                for (int j = 0; j < tot; j++) {
                    String cadena = listModel.getElementAt(j).toString();
                    int espacio = cadena.indexOf("(");
                    String valor = cadena.substring(0, espacio - 1).trim();

                    if (!valor.equals(stringTraitToEvaluate)) {
                        try {
                            csvOutput.write(modeloFilter.getValueAt(i, modeloFilter.findColumn(valor)).toString());
                        } catch (NullPointerException ex) {
                            String cad = null;
                            csvOutput.write(cad);
                        }
                    }

                }

                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV FOR R" + ex);
        }

    }

    public void writeDATAR_MASTER(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        int total = tableModel.getRowCount();
        int tot = tableModel.getVariateList().size();

        int trialColumn = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
        int repColumn = tableModel.getHeaderIndex(ObservationsTableModel.REPLICATION);
        int blockColumn = tableModel.getHeaderIndex(ObservationsTableModel.BLOCK);
        int plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT);
        int entryColumn = tableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
        int designColumn = tableModel.getHeaderIndex(ObservationsTableModel.DESIG);
        int gidColumn = tableModel.getHeaderIndex(ObservationsTableModel.GID);


        try {


            for (int i = 0; i < total; i++) {

                if (trialColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, trialColumn).toString());
                }
                if (repColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, repColumn).toString());
                }
                if (blockColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, blockColumn).toString());
                }
                if (entryColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, entryColumn).toString());
                }
                try {
                    csvOutput.write(tableModel.getValueAt(i, tableModel.findColumn(stringTraitToEvaluate)).toString());
                } catch (NullPointerException ex) {
                    String cad = ".";

                    csvOutput.write(cad);
                }


                for (int j = 0; j < tot; j++) {
                    String valor = tableModel.getVariateList().get(j).getVariateName();

                    if (!valor.equals(stringTraitToEvaluate)) {
                        try {
                            csvOutput.write(tableModel.getValueAt(i, tableModel.findColumn(valor)).toString());
                        } catch (NullPointerException ex) {
                            String cad = ".";
                            csvOutput.write(cad);
                        }
                    }

                }

                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV FOR R" + ex);
        }

    }

    public void writeDATAR(CsvWriter csvOutput, ObservationsTableModel tableModel) {
        int total = tableModel.getRowCount();
        int tot = tableModel.getVariateList().size();

        int trialColumn = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
        int repColumn = tableModel.getHeaderIndex(ObservationsTableModel.REPLICATION);
        int blockColumn = tableModel.getHeaderIndex(ObservationsTableModel.BLOCK);
        int plotColumn = tableModel.getHeaderIndex(ObservationsTableModel.PLOT);
        int entryColumn = tableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
        int designColumn = tableModel.getHeaderIndex(ObservationsTableModel.DESIG);
        int gidColumn = tableModel.getHeaderIndex(ObservationsTableModel.GID);


        try {


            for (int i = 0; i < total; i++) {

                if (trialColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, trialColumn).toString());
                }
                if (repColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, repColumn).toString());
                }
                if (blockColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, blockColumn).toString());
                }
                if (entryColumn >= 0) {
                    csvOutput.write(tableModel.getValueAt(i, entryColumn).toString());
                }
                try {
                    csvOutput.write(tableModel.getValueAt(i, tableModel.findColumn(stringTraitToEvaluate)).toString());
                } catch (NullPointerException ex) {
                    String cad = ".";

                    csvOutput.write(cad);
                }


                for (int j = 0; j < tot; j++) {
                    String valor = tableModel.getVariateList().get(j).getVariateName();

                    if (!valor.equals(stringTraitToEvaluate)) {
                        try {
                            csvOutput.write(tableModel.getValueAt(i, tableModel.findColumn(valor)).toString());
                        } catch (NullPointerException ex) {
                            String cad = ".";
                            csvOutput.write(cad);
                        }
                    }

                }

                csvOutput.endRecord();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GENERAR DATA CSV FOR R" + ex);
        }

    }

    public void readDATA(File file) {

        ArrayList titulos = new ArrayList();
        DefaultTableModel modelo = (DefaultTableModel) jTableObservations.getModel();
        //ObservationsTableModel modelo =  (ObservationsTableModel)jTableObservations.getModel();
        System.out.println("TENEMOS: " + dameTotalDatos(file));

        try {
            CsvReader csvReader = new CsvReader(file.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            if (headers[headers.length - 1].equals("IBFB")) {
                System.out.println("ES DEL IBFB");
            } else {
                System.out.println("NO ES DEL IBFB");
            }

            for (int i = 26; i < headers.length - 1; i++) {
                String titulo = headers[i];
                if (!titulo.equals("")) {
                    System.out.println(titulo);
                    titulos.add(titulo);
                }
            }

            for (int i = 0; i < 23; i++) {
                csvReader.skipRecord();

            }

            System.out.println("TENEMOS traits: " + titulos.size());


            int myrow = 0;
            while (csvReader.readRecord()) {
                String dataOfTraits = "";

                String trial = csvReader.get("Trial");
                String rep = csvReader.get("Rep");
                String block = csvReader.get("Block");
                String plot = csvReader.get("Plot");
                String entry = csvReader.get("Entry");
                String ped = csvReader.get("BreedersPedigree1");
                String gid = csvReader.get("GID");


                for (int i = 0; i < titulos.size(); i++) {

                    String head = titulos.get(i).toString();

                    int col = buscaCol(head);

                    if (col >= 0) {
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow, col);

                        dataOfTraits = dataOfTraits + " " + data;
                    } else {
                        modelo.addColumn(head);

                        col = buscaCol(head);
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow, col);

                        dataOfTraits = dataOfTraits + " " + data;

                    }


                }

                myrow++;

                System.out.println(trial + " " + rep + " " + block + " " + plot + " " + entry + " " + ped + " " + gid + dataOfTraits);
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }
    }

    public void readDATAnew(File file) {

        ArrayList titulos = new ArrayList();
        ObservationsTableModel modelo = (ObservationsTableModel) jTableObservations.getModel();
        //DefaultTableModel modelo = (DefaultTableModel) jTableObservations.getModel();
        int add = 0;
        String before = "";
        String actual = "";

        try {
            CsvReader csvReader = new CsvReader(file.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            if (headers[headers.length - 1].equals("IBFB")) {
                System.out.println("ES DEL IBFB");
            } else {
                System.out.println("NO ES DEL IBFB");
            }

            for (int i = 26; i < headers.length - 1; i++) {
                String titulo = headers[i];
                if (!titulo.equals("")) {
                    System.out.println(titulo);
                    titulos.add(titulo);
                }
            }

            for (int i = 0; i < 23; i++) {
                csvReader.skipRecord();
            }

            System.out.println("TENEMOS traits: " + titulos.size());

            int myrow = 0;
            while (csvReader.readRecord()) {

                String dataOfTraits = "";
                before = actual;
                String trial = csvReader.get("Trial");
                String rep = csvReader.get("Rep");
                String block = csvReader.get("Block");
                String plot = csvReader.get("Plot");
                String entry = csvReader.get("Entry");
                String ped = csvReader.get("BreedersPedigree1");
                String gid = csvReader.get("GID");

                System.out.println("TRIAL : --->>>" + trial);
                System.out.println("PLOT : --->>>" + plot);

                actual = entry;

                if (before.equals(entry)) {
                    add++;
                } else {
                    add = 0;
                }


                try {
                    myrow = findRow(Integer.parseInt(trial), Integer.parseInt(plot));
                } catch (NumberFormatException ex) {
                    System.out.println("ERROR AL IMPORTAR FIELDLOG FILE");
                    return;
                }

                for (int i = 0; i < titulos.size(); i++) {
                    String head = titulos.get(i).toString();
                    int col = buscaCol(head);
                    if (col >= 0) {
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow + add, col);
                        dataOfTraits = dataOfTraits + " " + data;
                    } else {
                        //modelo.addColumn(head);
                        col = buscaCol(head);
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow + add, col);
                        dataOfTraits = dataOfTraits + " " + data;
                    }
                }

                //  myrow++;
                //System.out.println(trial + " " + rep + " " + block + " " + plot + " " + entry + " " + ped + " " + gid + dataOfTraits);
            }
            csvReader.close();
            modelo.fireTableDataChanged();
        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }
    }

    public void readDATAnewCSV(File file) {

        ArrayList titulos = new ArrayList();
        ObservationsTableModel modelo = (ObservationsTableModel) jTableObservations.getModel();
        //DefaultTableModel modelo = (DefaultTableModel) jTableObservations.getModel();
        int add = 0;
        String before = "";
        String actual = "";

        try {
            CsvReader csvReader = new CsvReader(file.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            for (int i = 26; i < headers.length - 1; i++) {
                String titulo = headers[i];
                if (!titulo.equals("")) {
                    System.out.println(titulo);
                    titulos.add(titulo);
                }
            }

            System.out.println("TENEMOS traits: " + titulos.size());

            int myrow = 0;
            while (csvReader.readRecord()) {

                String dataOfTraits = "";
                before = actual;
                String trial = csvReader.get("Trial");
                String rep = csvReader.get("Rep");
                String block = csvReader.get("Block");
                String plot = csvReader.get("Plot");
                String entry = csvReader.get("Entry");
                String ped = csvReader.get("BreedersPedigree1");
                String gid = csvReader.get("GID");

                System.out.println("TRIAL : --->>>" + trial);
                System.out.println("PLOT : --->>>" + plot);

                actual = entry;

                if (before.equals(entry)) {
                    add++;
                } else {
                    add = 0;
                }


                try {
                    myrow = findRow(Integer.parseInt(trial), Integer.parseInt(plot));
                } catch (NumberFormatException ex) {
                    System.out.println("ERROR AL IMPORTAR FIELDLOG FILE");
                    return;
                }

                for (int i = 0; i < titulos.size(); i++) {
                    String head = titulos.get(i).toString();
                    int col = buscaCol(head);
                    if (col >= 0) {
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow + add, col);
                        dataOfTraits = dataOfTraits + " " + data;
                    } else {
                        //modelo.addColumn(head);
                        col = buscaCol(head);
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow + add, col);
                        dataOfTraits = dataOfTraits + " " + data;
                    }
                }

                //  myrow++;
                //System.out.println(trial + " " + rep + " " + block + " " + plot + " " + entry + " " + ped + " " + gid + dataOfTraits);
            }
            csvReader.close();
            modelo.fireTableDataChanged();
        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }
    }

    public void readDATACapture(File file) {

        ObservationsTableModel modelo = (ObservationsTableModel) jTableObservations.getModel();

        int variateCol = 0;
        HashMap<String, Integer> traitsMap = new HashMap<String, Integer>();
        for (Variate variate : modelo.getVariateList()) {
            variateCol = modelo.getHeaderIndex(Workbook.getStringWithOutBlanks(variate.getProperty()+variate.getScale()));
            traitsMap.put(variate.getVariateName(), variateCol);
        }
        int add = 0;

        try {
            CsvReader csvReader = new CsvReader(file.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            int myrow = 0;
            while (csvReader.readRecord()) {


                for (Variate variate : modelo.getVariateList()) {
                    String head = variate.getVariateName();
                    int col = traitsMap.get(head);
                    if (col >= 0) {
                        String data = csvReader.get(head);
                        modelo.setValueAt(data, myrow + add, col);
                        //dataOfTraits = dataOfTraits + " " + data;
                    }
                }

                myrow++;
                //System.out.println(trial + " " + rep + " " + block + " " + plot + " " + entry + " " + ped + " " + gid + dataOfTraits);
            }
            csvReader.close();
            modelo.fireTableDataChanged();
        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }
    }

    private int findRow(int trial, int plot) {
        int row = 0;

        ObservationsTableModel modelo = (ObservationsTableModel) jTableObservations.getModel();
        int colTrial = modelo.getHeaderIndex(ObservationsTableModel.TRIAL);
        int colplot = modelo.getHeaderIndex(ObservationsTableModel.PLOT);


        for (int i = 0; i < modelo.getRowCount(); i++) {
            int trialFromRow = Integer.parseInt(modelo.getValueAt(i, colTrial).toString());
            int plotFromRow = Integer.parseInt(modelo.getValueAt(i, colplot).toString());
            if (trial == trialFromRow && (plot == plotFromRow)) {
                row = i;
                break;
            }
        }

        return row;
    }

    public int dameTotalDatos(File file) {
        int total = 0;
        try {
            CsvReader csvReader = new CsvReader(file.toString());

            for (int i = 0; i < 24; i++) {
                csvReader.skipRecord();
            }

            while (csvReader.readRecord()) {
                total++;
            }
        } catch (IOException ex) {
            System.out.println("ERROR EN CONTAR REGISTROS CSV READER");
            total = 0;
        }

        return total;
    }

    public int dameTotalColumnas(File file) {
        int total = 0;
        try {
            CsvReader csvReader = new CsvReader(file.toString());

            total = csvReader.getHeaderCount();


        } catch (IOException ex) {
            System.out.println("ERROR EN CONTAR REGISTROS CSV READER");
            total = 0;
        }

        return total;
    }

    public boolean isValid(File file) {
        boolean isvalid = false;
        try {
            CsvReader csvReader = new CsvReader(file.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            if (headers[headers.length - 1].equals("IBFB")) {
                isvalid = true;
            } else {
                isvalid = false;
            }
        } catch (IOException ex) {
        }
        return isvalid;
    }

    private int buscaCol(String head) {
        int col = -1;

        for (int i = 0; i < this.jTableObservations.getColumnCount(); i++) {
            if (head.equals(this.jTableObservations.getColumnName(i))) {
                col = i;
            }
        }
        return col;
    }

    public void DefineTraitToEvaluate(String stringTraitToEval) {
        this.stringTraitToEvaluate = stringTraitToEval;
    }
}
