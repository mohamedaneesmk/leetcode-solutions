class Solution {
    private int k;
    private int N;
    private int[][] T; // T[node][r] = residue after multiplying starting residue r by the whole block
    private int[][] M; // M[node][r*k+c] = # positions in block whose running product == c, starting from r

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;
        N = 1;
        while (N < n) N *= 2;
        int size = 2 * N;
        T = new int[size][];
        M = new int[size][];

        for (int i = 0; i < N; i++) {
            int v = (i < n) ? nums[i] : 1;
            makeLeaf(N + i, v);
        }
        for (int node = N - 1; node >= 1; node--) {
            mergeNode(node);
        }

        int[] result = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int idx = queries[qi][0];
            int val = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            update(idx, val);
            int[] vec = query(start, n - 1);
            result[qi] = vec[x];
        }
        return result;
    }

    private void makeLeaf(int pos, int v) {
        int vmod = v % k;
        int[] Tl = new int[k];
        int[] Ml = new int[k * k];
        for (int r = 0; r < k; r++) {
            Tl[r] = (r * vmod) % k;
            Ml[r * k + Tl[r]] = 1;
        }
        T[pos] = Tl;
        M[pos] = Ml;
    }

    private void mergeNode(int node) {
        int l = 2 * node, r = 2 * node + 1;
        int[] Tl = T[l], Ml = M[l];
        int[] Tr = T[r], Mr = M[r];
        int[] Tn = new int[k];
        int[] Mn = new int[k * k];
        for (int rr = 0; rr < k; rr++) {
            int mid = Tl[rr];
            Tn[rr] = Tr[mid];
            int baseL = rr * k;
            int baseR = mid * k;
            for (int c = 0; c < k; c++) {
                Mn[baseL + c] = Ml[baseL + c] + Mr[baseR + c];
            }
        }
        T[node] = Tn;
        M[node] = Mn;
    }

    private void update(int idx, int val) {
        int pos = N + idx;
        makeLeaf(pos, val);
        pos /= 2;
        while (pos >= 1) {
            mergeNode(pos);
            pos /= 2;
        }
    }

    private int[] query(int l, int r) {
        int lo = l + N;
        int hi = r + N + 1;
        int[] leftParts = new int[32];
        int[] rightParts = new int[32];
        int lp = 0, rp = 0;
        while (lo < hi) {
            if ((lo & 1) == 1) {
                leftParts[lp++] = lo;
                lo++;
            }
            if ((hi & 1) == 1) {
                hi--;
                rightParts[rp++] = hi;
            }
            lo >>= 1;
            hi >>= 1;
        }

        int curR = 1 % k;
        int[] countVec = new int[k];

        for (int i = 0; i < lp; i++) {
            int node = leftParts[i];
            int[] Ml = M[node];
            int base = curR * k;
            for (int c = 0; c < k; c++) countVec[c] += Ml[base + c];
            curR = T[node][curR];
        }
        for (int i = rp - 1; i >= 0; i--) {
            int node = rightParts[i];
            int[] Ml = M[node];
            int base = curR * k;
            for (int c = 0; c < k; c++) countVec[c] += Ml[base + c];
            curR = T[node][curR];
        }

        return countVec;
    }
}