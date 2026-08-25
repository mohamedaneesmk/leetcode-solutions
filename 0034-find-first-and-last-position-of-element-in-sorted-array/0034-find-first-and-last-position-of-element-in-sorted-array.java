class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int lowerBound = findLowerBound(nums, n, target);
        
        if (lowerBound == n || nums[lowerBound] != target) {
            return new int[]{-1, -1};
        }

        return new int[]{lowerBound, findUpperBound(nums, n, target) - 1};
    }

    public static int findLowerBound(int[] nums, int n, int x) {
        int low = 0, high = nums.length - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int findUpperBound(int[] nums, int n, int x) {
        int low = 0, high = nums.length - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}