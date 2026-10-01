class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Find the row with the max value in column mid
            int maxRow = 0;
            for (int i = 1; i < m; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }

            int left = mid - 1 >= 0 ? mat[maxRow][mid - 1] : -1;
            int right = mid + 1 < n ? mat[maxRow][mid + 1] : -1;
            int cur = mat[maxRow][mid];

            if (cur > left && cur > right) {
                return new int[] { maxRow, mid }; // peak found
            } else if (left > cur) {
                high = mid - 1; // a peak exists on the left
            } else {
                low = mid + 1; // a peak exists on the right
            }
        }

        return new int[] { -1, -1 }; // never reached for valid input
    }
}