import java.util.*;

class Solution {
    private int digitSum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] order = new Integer[n];
        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
            ds[i] = digitSum(nums[i]);
        }

        Arrays.sort(order, (a, b) -> {
            if (ds[a] != ds[b]) return Integer.compare(ds[a], ds[b]);
            return Integer.compare(nums[a], nums[b]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || order[i] == i) continue;
            int j = i;
            int length = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = order[j];
                length++;
            }
            swaps += length - 1;
        }
        return swaps;
    }
}