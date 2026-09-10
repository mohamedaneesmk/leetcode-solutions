class Solution {
    public int mySqrt(int n) {
        if (n == 0)
            return 0;

        int low = 1, high = n;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long square = (long) mid * mid;

            if (square <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}