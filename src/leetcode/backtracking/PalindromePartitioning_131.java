package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning
    of s. A palindrome string is a string that reads the same backward as forward.

    Example 1:

    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]
    Example 2:

    Input: s = "a"
    Output: [["a"]]


    Constraints:

    1 <= s.length <= 16
    s contains only lowercase English letters.

    Each branch of tree is a solution
    [a,a,b], [aa, b]
    https://medium.com/javarevisited/how-to-return-all-the-palindrome-partitioning-of-a-string-leetcode-131-8b0995599855

                                                aab
                    partition at index 0                    partition at index 1       partition at index 2
                             a                                  aa                       aab
partition at index 1         partition at index 2           partition at index 2
          a                             ab                       b
partition at index 2
          b
 */
public class PalindromePartitioning_131 {

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> currentPartition = new ArrayList<>();
        partition(s, 0, currentPartition, result);
        return result;
    }

    private static void partition(String s, int index, List<String> currPartition, List<List<String>> result) {
        // base case to check if the index i reched to the end of string s, if it does then we can simply copy the result and return the call
        if (index >= s.length()) {
            result.add(new ArrayList<>(currPartition));
            return;
        }
        // lets iterate over string s from j=initial index i upto end of string s
        for (int j = index; j < s.length(); j++) {
            String target = s.substring(index, j + 1);
            if (isPalindrome(target)) { // check if target substr is palindrome
                // if it is then add to current partition
                currPartition.add(target);
                // next recursively check other substrings by increasing initials . for example "a" is palindrome, then next target would be next "a" and then next "b".
                partition(s, j + 1, currPartition, result);
                /** since we already checked for current substring , then lets remove it from our currentPartition and check for another combination
                 like initially we check for combination "a", next we will check for combination "aa" and then finally for "aab" **/
                currPartition.remove(currPartition.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}
