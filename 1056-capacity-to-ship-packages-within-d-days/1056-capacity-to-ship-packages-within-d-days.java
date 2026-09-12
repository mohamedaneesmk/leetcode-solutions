class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = Integer.MIN_VALUE;
        int sum = 0;

        for (int weight : weights) {
            maxWeight = Math.max(weight, maxWeight);
            sum += weight;
        }

        int low = maxWeight, high = sum;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int daysRequired = findDaysRequired(weights, mid);
            if (daysRequired <= days) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static int findDaysRequired(int[] weights, int capacity) {
        int days = 1, load = 0;
        for (int weight : weights) {
            if (weight + load > capacity) {
                days = days + 1;
                load = weight;
            } else {
                load += weight;
            }
        }

        return days;
    }
}