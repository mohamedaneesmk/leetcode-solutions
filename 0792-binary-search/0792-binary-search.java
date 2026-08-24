class Solution {
    public int search(int[] nums, int target) {
        int result = findIndex(nums, 0, nums.length - 1, target);
        return result;
    }

    private static int findIndex(int[] nums, int low, int high, int target) {
        if (low > high) return -1;

        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return findIndex(nums, mid + 1, high, target);
        } else {
            return findIndex(nums, low, mid - 1, target);
        }
    }
}