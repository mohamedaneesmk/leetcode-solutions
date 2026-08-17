class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfPermutations(int n, int[][] requirements) {

        // req[i] = required inversion count for prefix [0..i]
        int[] req = new int[n];
        Arrays.fill(req, -1);

        for (int[] r : requirements) {
            req[r[0]] = r[1];
        }

        // Maximum required inversion count is 400
        int MAX = 400;

        long[] dp = new long[MAX + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {

            long[] prefix = new long[MAX + 1];

            // Prefix sums of dp
            prefix[0] = dp[0];

            for (int j = 1; j <= MAX; j++) {
                prefix[j] = (prefix[j - 1] + dp[j]) % MOD;
            }

            long[] newDp = new long[MAX + 1];

            /*
             * Adding element i can create k inversions,
             * where 0 <= k <= i.
             *
             * newDp[j] =
             * dp[j] + dp[j-1] + ... + dp[j-i]
             */
            for (int j = 0; j <= MAX; j++) {

                int left = j - i - 1;

                newDp[j] = prefix[j];

                if (left >= 0) {
                    newDp[j] = (newDp[j] - prefix[left] + MOD) % MOD;
                }
            }

            // Apply requirement for prefix [0..i]
            if (req[i] != -1) {

                int required = req[i];

                if (required > MAX) {
                    return 0;
                }

                for (int j = 0; j <= MAX; j++) {
                    if (j != required) {
                        newDp[j] = 0;
                    }
                }
            }

            dp = newDp;
        }

        // If there is no requirement at n-1, sum all possibilities.
        // Otherwise only the required state is non-zero.
        long answer = 0;

        for (long value : dp) {
            answer = (answer + value) % MOD;
        }

        return (int) answer;
    }
}