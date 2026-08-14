class Solution {
    public int[][] merge(int[][] nums) {
        int n = nums.length;

        Arrays.sort(nums, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int start = nums[i][0];
            int end = nums[i][1];

            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1)[1]) {
                continue;
            }

            for (int j = i + 1; j < n; j++) {

                if (nums[j][0] <= end) {
                    end = Math.max(end, nums[j][1]);
                } else {
                    break;
                }
            }

            ans.add(new int[] { start, end });
        }

        return ans.toArray(new int[ans.size()][]);
    }
}