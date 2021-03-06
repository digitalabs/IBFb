package ibfb.studyeditor.util;

import ibfb.studyexplorerutil.core.StudyExplorerListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author TMSANCHEZ
 */
public class RefreshBrowserHelper {

    public static void refreshStudyBrowser() {
        // first look for StudyExplorerTopComponent
        TopComponent topComponent = WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");

        // if found, then cast it as StudyExplorerListener, then refresh it
        if (topComponent != null) {
            StudyExplorerListener studyExplorerListener = (StudyExplorerListener) topComponent;
            studyExplorerListener.refreshStudyBrowser();
        }

    }
}
