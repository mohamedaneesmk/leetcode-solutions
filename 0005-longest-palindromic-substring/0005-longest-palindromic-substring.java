class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int maxLen = 1, start = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);     // Odd Length
            int len2 = expand(s, i, i + 1); // Even Length
            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, maxLen + start);
    }

    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}