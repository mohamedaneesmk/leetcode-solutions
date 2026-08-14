class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0;

        int leftOfNums1 = 0;
        int leftOfNums2 = 0;

        while (leftOfNums1 < m && leftOfNums2 < n) {
            if (nums1[leftOfNums1] <= nums2[leftOfNums2]) {
                result[i++] = nums1[leftOfNums1];
                leftOfNums1++;
            } else {
                result[i++] = nums2[leftOfNums2];
                leftOfNums2++;
            }
        }

        while (leftOfNums1 < m) {
            result[i++] = nums1[leftOfNums1];
            leftOfNums1++;
        }

        while (leftOfNums2 < n) {
            result[i++] = nums2[leftOfNums2];
            leftOfNums2++;
        }

        for (int x = 0; x < result.length; x++) {
            nums1[x] = result[x];
        }
    }
}