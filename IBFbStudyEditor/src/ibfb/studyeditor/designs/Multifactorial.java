package ibfb.studyeditor.designs;

import ibfb.domain.core.Factor;
import java.util.List;

/**
 *
 * @author tmsg
 */
public class Multifactorial {

    private static final int LEVEL_1 = 0;
    private static final int LEVEL_2 = 1;
    private static final int LEVEL_3 = 2;
    private static final int LEVEL_4 = 3;
    /**
     * Matrix with all combinations
     */
    private Object[][] matrix = new Object[0][0];
    /**
     * Matrix for levels
     */
    private Object[][] levels = new Object[0][0];
    /**
     * Total rows to generate
     */
    private int totalRows;
    /**
     * Total number of columns
     */
    private int totalColumns;
    /**
     * Bidimensional array with all treatments
     */
    private Object[][] treatments;
    private int currentRow;

    public Multifactorial(Object[][] treatments) {
        this.treatments = treatments;
        calculateTotalRowsAndColumns();
    }

    /**
     * Returns all combinations
     *
     * @return
     */
    public Object[][] getCombinations() {
        if (treatments.length > 0) {
            int index = 0;
            Object[] simpleArray = treatments[index];
            for (int i = 0; i < simpleArray.length; i++) {
                switch (treatments.length) {
                    case 1:
                        fillMatrix(i, simpleArray[i]);
                        break;
                    case 2:
                        fillMatrix(i, simpleArray[i], treatments[index + 1]);
                        break;
                    case 3:
                        fillMatrix(i, simpleArray[i], treatments[index + 1], treatments[index + 2]);
                        break;
                    case 4:
                        fillMatrix(i, simpleArray[i], treatments[index + 1], treatments[index + 2], treatments[index + 3]);
                }

            }
        }
        return matrix;
    }

    public Object[][] getLevels() {
        return levels;
    }

    /**
     * Calculates total of rows for all combinations
     */
    private void calculateTotalRowsAndColumns() {
        if (treatments.length > 0) {
            totalRows = treatments[0].length;
            totalColumns = treatments.length;
            // first calculate total of rows
            for (int index = 1; index < treatments.length; index++) {
                totalRows = totalRows * treatments[index].length;
            }

            // then define the matrix
            matrix = new Object[totalRows][totalColumns];
            levels = new Object[totalRows][totalColumns];
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    private void fillMatrix(Integer level, Object value) {
        matrix[currentRow][LEVEL_1] = value;
        levels[currentRow][LEVEL_1] = level + 1;
        currentRow++;
    }

    private void fillMatrix(Integer level, Object value, Object[] array) {
        for (int i = 0; i < array.length; i++) {
            matrix[currentRow][LEVEL_1] = value;
            matrix[currentRow][LEVEL_2] = array[i];

            levels[currentRow][LEVEL_1] = level + 1;
            levels[currentRow][LEVEL_2] = i + 1;
            currentRow++;
        }
    }

    private void fillMatrix(Integer level, Object value, Object[] array, Object[] arrayB) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                matrix[currentRow][LEVEL_1] = value;
                matrix[currentRow][LEVEL_2] = array[i];
                matrix[currentRow][LEVEL_3] = arrayB[j];

                levels[currentRow][LEVEL_1] = level + 1;
                levels[currentRow][LEVEL_2] = i + 1;
                levels[currentRow][LEVEL_3] = j + 1;
                currentRow++;
            }
        }
    }

    private void fillMatrix(Integer level, Object value, Object[] array, Object[] arrayB, Object[] arrayC) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                for (int k = 0; k < arrayC.length; k++) {
                    matrix[currentRow][LEVEL_1] = value;
                    matrix[currentRow][LEVEL_2] = array[i];
                    matrix[currentRow][LEVEL_3] = arrayB[j];
                    matrix[currentRow][LEVEL_4] = arrayC[k];

                    levels[currentRow][LEVEL_1] = level + 1;
                    levels[currentRow][LEVEL_2] = i + 1;
                    levels[currentRow][LEVEL_3] = j + 1;
                    levels[currentRow][LEVEL_4] = k + 1;
                    currentRow++;

                }
            }
        }
    }

    public static int getTotalRows(List<Factor> otherFactors) {
        int totalRows = 0;
        Integer[] treatments = new Integer[0];
        if (otherFactors != null && !otherFactors.isEmpty()) {
            treatments = new Integer[otherFactors.size()];
            for (int index = 0; index < otherFactors.size(); index++) {
                Factor factor = otherFactors.get(index);
                Integer totalLevels = Integer.parseInt(factor.getValue().toString());
                treatments[index] = totalLevels;
            }
            totalRows = treatments[0];
            // first calculate total of rows
            for (int index = 1; index < treatments.length; index++) {
                totalRows = totalRows * treatments[index];
            }
        }
        return totalRows;
    }
}
