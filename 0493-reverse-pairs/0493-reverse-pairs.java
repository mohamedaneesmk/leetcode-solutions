class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        // Count cross reverse pairs (both halves already sorted)
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }

        // Merge step
        int[] merged = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, k = 0;
        while (p1 <= mid && p2 <= right) {
            if (nums[p1] <= nums[p2]) merged[k++] = nums[p1++];
            else merged[k++] = nums[p2++];
        }
        while (p1 <= mid) merged[k++] = nums[p1++];
        while (p2 <= right) merged[k++] = nums[p2++];

        System.arraycopy(merged, 0, nums, left, merged.length);
        return count;
    }
}