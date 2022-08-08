package companies.grab;

public class LongestPalindromicSubstring {

    // Brute force
    public String longestPalindrome(String s) {
        if (s == null || s == "" || s.length() == 1)
            return s;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    int len = j - i + 1;
                    if (len > result.length()) {
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s, int low, int high){
        while(low <= high){
            if(s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    /*
        Palindromes mirror around its center

        A B A C C A A C C D B B

        2 cases

        1. even characters -
            a b b a
              ^ ^
            if characters are same
            a b b a
            ^     ^
            if characters are same, return it as left and right pointers went out of boundaries
            a b b a
          ^         ^
        2. odd characters -

            a   b   a
                ^
                ^
            if characters are same
                a   b   a
                ^
                        ^
            if characters are same, return it as left and right pointers went out of boundaries
                a   b   a
              ^
                           ^

     */

    public String longestPalindrome2(String s) {
            if (s == null || s.length() < 1) return "";
            String maxPalindrome = "";
            for (int i = 0; i < s.length(); i++) {
                String oddLength = expandAroundCenter(s, i, i);
                String evenLength = expandAroundCenter(s, i, i + 1);
                String current = oddLength.length() > evenLength.length() ? oddLength : evenLength;
                maxPalindrome = current.length() > maxPalindrome.length() ? current : maxPalindrome;
            }
            return maxPalindrome;
        }

        private String expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return s.substring(left + 1, right);
        }

//    class Index {
//        int left;
//        int right;
//        Index(int left, int right) {
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    public String longestPalindrome3(String s) {
//        int len = s.length();
//        if(len == 0) return s;
//        int maxLen = Integer.MIN_VALUE;
//        int start = -1, end = -1;
//        for(int i = 0; i < len; i++) {
//            Index index1 = expandAroundCenter3(s, i, i);
//            Index index2 = expandAroundCenter3(s, i, i+1);
//            int len1 = index1.right - index1.left + 1;
//            int len2 = index2.right - index2.left + 1;
//            int l = Math.max(len1, len2);
//            if(l > maxLen) {
//                maxLen = l;
//                start = (len1 > len2) ? index1.left : index2.left;
//                end = (len1 > len2) ? index1.right : index2.right;
//            }
//        }
//        return s.substring(start, end+1);
//    }
//
//    Index expandAroundCenter3(String s, int left, int right) {
//        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//            left--;
//            right++;
//        }
//        return new Index(left+1, right-1);
//    }
}
