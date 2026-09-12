class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        // Not enough flowers even in total — impossible
        if ((long) m * k > n)
            return -1;

        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            lo = Math.min(lo, day);
            hi = Math.max(hi, day);
        }

        // Binary search for the smallest day that works
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                hi = mid; // mid works, try earlier
            } else {
                lo = mid + 1; // mid doesn't work, go later
            }
        }

        return lo;
    }

    // Can we make at least m bouquets if we wait until 'day'?
    private boolean canMake(int[] bloomDay, int day, int m, int k) {
        int bouquets = 0;
        int flowers = 0;

        for (int b : bloomDay) {
            if (b <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0; // start a fresh streak for the next bouquet
                }
            } else {
                flowers = 0; // streak broken, this flower hasn't bloomed
            }
        }

        return bouquets >= m;
    }
}