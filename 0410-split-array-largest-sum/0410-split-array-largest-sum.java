class Solution {

    public static int findCount(int[] nums, int mid) {
        int studentsCount = 1, currentPages = 0;

        for (int num : nums) {
            if (num + currentPages <= mid) {
                currentPages += num;
            } else {
                studentsCount++;
                currentPages = num;
            }
        }

        return studentsCount;
    }

    public static int findPages(int[] nums, int k) {
        int low = Integer.MIN_VALUE;
        int high = 0;

        for (int num : nums) {
            if (num > low)
                low = Math.max(num, low);
            high += num;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int requiredStudents = findCount(nums, mid);

            if (requiredStudents > k)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return low;
    }

    public int splitArray(int[] nums, int k) {
        return findPages(nums, k);
    }
}