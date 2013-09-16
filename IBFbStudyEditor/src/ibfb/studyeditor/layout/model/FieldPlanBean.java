package ibfb.studyeditor.layout.model;

/**
 *
 * @author tmsg
 */
public class FieldPlanBean {
    public static final String BLOCK_REP_COLUMN = "COLUMN";
    public static final String BLOCK_REP_ROW = "ROW";
    
    public static final String PLOT_ORDER_COLUMN = "COLUMN";
    public static final String PLOT_ORDER_ROW = "ROW";
    public static final String PLOT_ORDER_SERPENTINE = "SERPENTINE";
    
    private Integer trial;
    private Integer rowsPlotNumber;
    private Integer columnsPlotNumber;
    private String blockRepsMode;
    private String plotOrder;

    public FieldPlanBean() {
        rowsPlotNumber = 1;
        columnsPlotNumber = 1;
        blockRepsMode = BLOCK_REP_COLUMN;
        plotOrder = PLOT_ORDER_COLUMN;
    }

    public FieldPlanBean(Integer trial, Integer rowsPlotNumber, Integer columnsPlotNumber, String blockRepsMode, String plotOrder) {
        this.trial = trial;
        this.rowsPlotNumber = rowsPlotNumber;
        this.columnsPlotNumber = columnsPlotNumber;
        this.blockRepsMode = blockRepsMode;
        this.plotOrder = plotOrder;
    }

    public String getBlockRepsMode() {
        return blockRepsMode;
    }

    public void setBlockRepsMode(String blockRepsMode) {
        this.blockRepsMode = blockRepsMode;
    }

    public Integer getColumnsPlotNumber() {
        return columnsPlotNumber;
    }

    public void setColumnsPlotNumber(Integer columnsPlotNumber) {
        this.columnsPlotNumber = columnsPlotNumber;
    }

    public String getPlotOrder() {
        return plotOrder;
    }

    public void setPlotOrder(String plotOrder) {
        this.plotOrder = plotOrder;
    }

    public Integer getRowsPlotNumber() {
        return rowsPlotNumber;
    }

    public void setRowsPlotNumber(Integer rowsPlotNumber) {
        this.rowsPlotNumber = rowsPlotNumber;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }
    
    
}
