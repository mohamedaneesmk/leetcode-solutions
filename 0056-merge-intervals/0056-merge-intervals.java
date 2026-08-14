class Solution {
    public int[][] merge(int[][] intervals) {

        // 1. Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        // 2. Start with the first interval
        int start = intervals[0][0];
        int end = intervals[0][1];

        // 3. Process remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // Overlapping
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }

            // Non-overlapping
            else {
                result.add(new int[]{start, end});

                start = intervals[i][0];
                end = intervals[i][1];
            }
        }

        // 4. Add the last interval
        result.add(new int[]{start, end});

        return result.toArray(new int[result.size()][]);
    }
}