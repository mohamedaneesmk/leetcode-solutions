class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] temp = new int[n1 + n2];
        int x = 0;

        int i = 0;
        int j = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                temp[x++] = nums1[i];
                i++;
            } else {
                temp[x++] = nums2[j];
                j++;
            }
        }

        while (i < n1) {
            temp[x++] = nums1[i];
            i++;
        }

        while (j < n2) {
            temp[x++] = nums2[j];
            j++;
        }

        int mid = temp.length / 2;

        if (temp.length % 2 == 0) {
            return (temp[mid - 1] + temp[mid]) / 2.0;
        } else {
            return temp[mid];
        }
    }
}