class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }

            // Too many ')' even if every '*' acts as '('
            if (high < 0)
                return false;

            // Can't have negative open count; treat that '*' as empty instead
            if (low < 0)
                low = 0;
        }

        return low == 0;
    }
}