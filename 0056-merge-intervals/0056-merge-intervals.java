class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = result.get(result.size() - 1);

            if (current[0] <= last[1]) {
                // Overlap -> merge by extending the end
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap -> add as new interval
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}