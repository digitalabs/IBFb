package ibfb.lists.report;

import ibfb.lists.report.viewer.ReportViewerTopComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;

/**
 *
 * @author tmsg
 */
public class ProcessLabelReport {

    public static final String BIG_REPORT = "BIG_REPORT";
    public static final String SMALL_REPORT = "SMALL_REPORT";
    private static final String FOLDER = "modules/reports/";
    private static final String SMALL_FILE = FOLDER + "IBFbLabelReport.jasper";
    private static final String BIG_FILE = FOLDER + "IBFbLabelReportBig.jasper";
    private static final String MODULE_NAME = "ibfb.inventory.core";

    public static void printBarCodeReport(final List<ReportBean> records, final String reportType) {
        final ProgressHandle handle = ProgressHandleFactory.createHandle("Generating report... ");
        handle.start();

        (new SwingWorker<String, Object>() {

            InputStream streamReport = null;
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(records);
            InstalledFileLocator ifl = InstalledFileLocator.getDefault();
            JasperPrint jasperPrint = null;

            @Override
            protected String doInBackground() throws Exception {
                File reportFile = ifl.locate(SMALL_FILE, MODULE_NAME, false);
                
                if (reportType.equals(BIG_REPORT)) {
                    reportFile = ifl.locate(BIG_FILE, MODULE_NAME, false);
                }
                
                streamReport = new FileInputStream(reportFile);
                JasperReport report = (JasperReport) JRLoader.loadObject(streamReport);
                report.setProperty("net.sf.jasperreports.components.barcode4j.image.producer", "image");
                jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), datasource);
                return "";
            }

            @Override
            protected void done() {
                super.done();
                try {
                    //            JRExporter exporter = new JRPdfExporter();
                    //            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    //            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("c:/tmsanchez/temporal/reporte.pdf"));
                    //            exporter.exportReport();
                    String valor = get();
                    ReportViewerTopComponent reportViewerTopComponent = ReportViewerTopComponent.getDefault();
                    reportViewerTopComponent.setJasperPrint(jasperPrint);
                    reportViewerTopComponent.open();
                    reportViewerTopComponent.requestActive();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                } finally {
                    // close the progress handler
                    handle.finish();
                }
            }
        }).execute();

    }
}
