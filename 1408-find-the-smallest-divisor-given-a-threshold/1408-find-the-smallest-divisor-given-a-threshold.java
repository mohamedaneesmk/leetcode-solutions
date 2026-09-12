class Solution {

    public static int smallestDivisor(int[] nums, int threshold) {

        int low = 1;
        int high = 0;

        // Find maximum value
        for (int num : nums) {
            high = Math.max(high, num);
        }

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (calculateSum(nums, mid) <= threshold) {

                // mid is a valid answer
                ans = mid;

                // Try to find a smaller divisor
                high = mid - 1;

            } else {

                // mid is too small
                low = mid + 1;
            }
        }

        return ans;
    }

    private static int calculateSum(int[] nums, int divisor) {

        int totalSum = 0;

        for (int num : nums) {
            totalSum += (num + divisor - 1) / divisor;
        }

        return totalSum;
    }
}