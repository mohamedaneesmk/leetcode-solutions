class Solution {

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by right endpoint
        java.util.Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[3], b[3]);
        });

        // Find previous non-overlapping interval
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(arr, i);
        }

        /*
         * dp[i][k]:
         * Best answer using first i intervals
         * and choosing at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[0][k] = new State(0, new int[0]);
        }

        for (int i = 1; i <= n; i++) {

            for (int k = 0; k <= 4; k++) {

                // Option 1: Skip current interval
                State skip = dp[i - 1][k];

                State best = skip;

                // Option 2: Take current interval
                if (k > 0) {

                    int p = prev[i - 1];

                    State previous = dp[p + 1][k - 1];

                    int[] takeIndices =
                            new int[previous.indices.length + 1];

                    System.arraycopy(
                            previous.indices,
                            0,
                            takeIndices,
                            0,
                            previous.indices.length
                    );

                    takeIndices[takeIndices.length - 1] =
                            arr[i - 1][3];

                    // We need indices in sorted order for
                    // lexicographical comparison.
                    java.util.Arrays.sort(takeIndices);

                    State take = new State(
                            previous.score + arr[i - 1][2],
                            takeIndices
                    );

                    if (isBetter(take, best)) {
                        best = take;
                    }
                }

                dp[i][k] = best;
            }
        }

        return dp[n][4].indices;
    }

    private int findPrevious(int[][] arr, int i) {

        int low = 0;
        int high = i - 1;

        int answer = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            /*
             * Intervals sharing a boundary overlap.
             *
             * Therefore:
             * previous.right < current.left
             */
            if (arr[mid][1] < arr[i][0]) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    private boolean isBetter(State a, State b) {

        // Higher score wins
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices win
        return lexicographicallySmaller(a.indices, b.indices);
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return a.length < b.length;
    }
}