class Solution {
    private List<String> res = new ArrayList<>();
    private String s;
    private int n;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.n = s.length();

        // Step 1: compute the minimum number of '(' and ')' to remove
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) left--;
                else right++;
            }
        }

        // Step 2: DFS with pruning
        dfs(0, left, right, 0, new StringBuilder());
        return res;
    }

    private void dfs(int i, int leftRem, int rightRem, int balance, StringBuilder sb) {
        if (i == n) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char ch = s.charAt(i);

        // Letters are always kept
        if (ch != '(' && ch != ')') {
            sb.append(ch);
            dfs(i + 1, leftRem, rightRem, balance, sb);
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        // Group a run of identical parentheses and choose how many to remove.
        // Choosing a count (not which ones) generates each distinct string once.
        int j = i;
        while (j < n && s.charAt(j) == ch) j++;
        int run = j - i;

        int startLen = sb.length();
        for (int removed = 0; removed <= run; removed++) {
            int nl = leftRem, nr = rightRem;
            if (ch == '(') nl -= removed;
            else nr -= removed;
            if (nl < 0 || nr < 0) break;        // removal budget exceeded

            int keep = run - removed;
            int nb = (ch == '(') ? balance + keep : balance - keep;
            if (nb < 0) continue;               // more ')' than '(' so far

            for (int k = 0; k < keep; k++) sb.append(ch);
            dfs(j, nl, nr, nb, sb);
            sb.setLength(startLen);             // backtrack
        }
    }
}