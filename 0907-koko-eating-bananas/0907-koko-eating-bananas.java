class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        long low = 1, high = -1;
        for (int pile : piles)
            high = Math.max(pile, high);

        long ans = -1;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long requiredTime = canFinish(piles, mid);

            if (requiredTime <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) ans;
    }

    public static long canFinish(int[] piles, long hourly) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + hourly - 1) / hourly;
        }

        return totalHours;
    }
}