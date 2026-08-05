class Solution {
    public void setZeroes(int[][] matrix) {
        
        boolean firstRowZero = false;
        boolean firstColZero = false;

        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        for (int col = 0; col < colSize; col++) {
            if (matrix[0][col] == 0) {
                firstRowZero = true;
                break;
            }
        } 

        for (int row = 0; row < rowSize; row++) {
            if (matrix[row][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int row = 1; row < rowSize; row++) {
            for (int col = 1; col < colSize; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = 1; row < rowSize; row++) {
            for (int col = 1; col < colSize; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (firstRowZero) {
            for (int col = 0; col < colSize; col++) {
                matrix[0][col] = 0;
            }
        }

        if (firstColZero) {
            for (int row = 0; row < rowSize; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}