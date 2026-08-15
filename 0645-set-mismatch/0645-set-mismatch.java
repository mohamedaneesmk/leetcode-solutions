class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];
        int dup = -1, missing = -1;
        
        for (int x : nums) {
            count[x]++;
            if (count[x] == 2) {
                dup = x;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                missing = i;
                break;
            }
        }
        
        return new int[]{dup, missing};
    }
}