class Solution {

    // Returns how many subarrays are needed
    // if the maximum allowed subarray sum is maxSum.
    public static int countSubarrays(int[] nums, int maxSum) {

        int subarrayCount = 1;
        int currentSum = 0;

        for (int num : nums) {

            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                subarrayCount++;
                currentSum = num;
            }
        }

        return subarrayCount;
    }

    public static int findMinimumLargestSum(int[] nums, int k) {

        // Minimum possible answer = largest element.
        int low = Integer.MIN_VALUE;

        // Maximum possible answer = sum of all elements.
        int high = 0;

        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        // Binary search on the answer.
        while (low <= high) {

            int mid = low + (high - low) / 2;

            int requiredSubarrays = countSubarrays(nums, mid);

            if (requiredSubarrays > k) {
                // We need too many subarrays.
                // Increase the allowed maximum sum.
                low = mid + 1;
            } else {
                // We can split into k or fewer subarrays.
                // Try to find a smaller maximum sum.
                high = mid - 1;
            }
        }

        return low;
    }

    public int splitArray(int[] nums, int k) {
        return findMinimumLargestSum(nums, k);
    }
}