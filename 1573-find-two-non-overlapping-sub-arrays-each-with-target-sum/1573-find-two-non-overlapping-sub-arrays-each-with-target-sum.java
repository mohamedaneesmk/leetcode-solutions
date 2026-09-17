class Solution {

    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        // best[i] = shortest target-sum subarray
        // completely inside arr[0...i]
        int[] best = new int[n];

        // Initialize with a large value
        for (int i = 0; i < n; i++) {
            best[i] = Integer.MAX_VALUE;
        }

        int left = 0;
        int currentSum = 0;

        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            currentSum += arr[right];

            // Shrink window if sum becomes greater than target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if (currentSum == target) {

                int currentLength = right - left + 1;

                // There is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(
                            answer,
                            currentLength + best[left - 1]);
                }

                // Store the shortest subarray found so far
                if (right == 0) {
                    best[right] = currentLength;
                } else {
                    best[right] = Math.min(
                            best[right - 1],
                            currentLength);
                }

            } else {

                // No subarray ending at right,
                // so carry forward the previous best
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}