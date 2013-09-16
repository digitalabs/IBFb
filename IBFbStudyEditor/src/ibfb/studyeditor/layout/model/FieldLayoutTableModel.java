package ibfb.studyeditor.layout.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;
import org.openide.util.NbBundle;

/**
 *
 * @author tmsg
 */
public class FieldLayoutTableModel extends AbstractTableModel {

    private static Logger log = Logger.getLogger(FieldLayoutTableModel.class);
    private static final String TRIAL = NbBundle.getMessage(FieldLayoutTableModel.class, "layout.trial");
    private static final String NUM_OF_PLOT_ROWS = NbBundle.getMessage(FieldLayoutTableModel.class, "layout.numofplotrows");
    private static final String NUM_OF_PLOT_COLS = NbBundle.getMessage(FieldLayoutTableModel.class, "layout.numofplotcols");
    private static final String BLOCK_REPS = NbBundle.getMessage(FieldLayoutTableModel.class, "layout.blockreps");
    private static final String PLOT_ORDER = NbBundle.getMessage(FieldLayoutTableModel.class, "layout.plotorder");
    private static final String[] columnNames = {TRIAL, NUM_OF_PLOT_ROWS, NUM_OF_PLOT_COLS, BLOCK_REPS, PLOT_ORDER};
    private List<FieldPlanBean> fieldPlanList;

    public FieldLayoutTableModel(List<FieldPlanBean> fieldPlanList) {
        this.fieldPlanList = fieldPlanList;
    }

    @Override
    public int getRowCount() {
        return fieldPlanList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FieldPlanBean bean = fieldPlanList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return bean.getTrial();
            case 1:
                return bean.getRowsPlotNumber();
            case 2:
                return bean.getColumnsPlotNumber();
            case 3:
                return bean.getBlockRepsMode();
            case 4:
                return bean.getPlotOrder();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FieldPlanBean bean = fieldPlanList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                bean.setTrial(Integer.parseInt(aValue.toString()));
                break;
            case 1:
                bean.setRowsPlotNumber(Integer.parseInt(aValue.toString()));
                break;
            case 2:
                bean.setColumnsPlotNumber(Integer.parseInt(aValue.toString()));
                break;
            case 3:
                bean.setBlockRepsMode(aValue.toString());
                break;
            case 4:
                bean.setPlotOrder(aValue.toString());
                break;
            default:

        }
    }

    public List<FieldPlanBean> getFieldPlanList() {
        return fieldPlanList;
    }

    public void setFieldPlanList(List<FieldPlanBean> fieldPlanList) {
        this.fieldPlanList = fieldPlanList;
    }
}
