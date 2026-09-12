class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (daysNeeded(weights, mid) <= days) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int daysNeeded(int[] weights, int cap) {
        int days = 1, cur = 0;
        for (int w : weights) {
            if (cur + w > cap) {
                days++;
                cur = 0;
            }
            cur += w;
        }
        return days;
    }
}