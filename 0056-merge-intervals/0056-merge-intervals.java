class Solution {

    public static int[][] merge(int[][] intervals) {

        // 1. Sort by starting time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> ans = new ArrayList<>();

        // 2. Process each interval
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            // No overlap
            if (ans.isEmpty() || start > ans.get(ans.size() - 1)[1]) {
                ans.add(new int[]{start, end});
            }

            // Overlap
            else {
                ans.get(ans.size() - 1)[1] =
                        Math.max(ans.get(ans.size() - 1)[1], end);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

}