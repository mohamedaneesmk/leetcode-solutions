class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sm, sq;
    int[] lz;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sm = new long[4 * n];
        sq = new long[4 * n];
        lz = new int[4 * n];

        int[] last = new int[100_001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int p = last[nums[j]];
            update(1, 0, n - 1, p + 1, j);
            ans = (ans + sq[1]) % MOD;
            last[nums[j]] = j;
        }
        return (int) ans;
    }

    private void apply(int node, int len, int d) {
        sq[node] = (sq[node] + 2L * d * sm[node] + (long) d * d % MOD * len) % MOD;
        sm[node] = (sm[node] + (long) d * len) % MOD;
        lz[node] += d;
    }

    private void push(int node, int nl, int nr) {
        if (lz[node] != 0) {
            int mid = (nl + nr) / 2;
            apply(2 * node, mid - nl + 1, lz[node]);
            apply(2 * node + 1, nr - mid, lz[node]);
            lz[node] = 0;
        }
    }

    private void update(int node, int nl, int nr, int l, int r) {
        if (r < nl || nr < l) return;
        if (l <= nl && nr <= r) {
            apply(node, nr - nl + 1, 1);
            return;
        }
        push(node, nl, nr);
        int mid = (nl + nr) / 2;
        update(2 * node, nl, mid, l, r);
        update(2 * node + 1, mid + 1, nr, l, r);
        sm[node] = (sm[2 * node] + sm[2 * node + 1]) % MOD;
        sq[node] = (sq[2 * node] + sq[2 * node + 1]) % MOD;
    }
}