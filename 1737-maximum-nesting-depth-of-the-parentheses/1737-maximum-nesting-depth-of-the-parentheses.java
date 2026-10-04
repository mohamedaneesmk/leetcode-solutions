class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int max = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                depth++;
                max = Math.max(depth, max);
            } else if (ch == ')') {
                depth--;
            }
        }

        return max;
    }
}