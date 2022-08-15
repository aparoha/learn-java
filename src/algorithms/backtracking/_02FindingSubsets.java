package algorithms.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
    Find all subsets of given string
    abc

    ""
    a
    b
    c
    ab
    ac
    bc
    abc
 */
public class _02FindingSubsets {

    /*
    The idea is to consider two cases for every character.
    (i) Consider current character as part of current subset
    (ii) Do not consider current character as part of the current subset.
     */
    public static List<String> findSubsets(String input) {
        List<String> result = new ArrayList<>();
        findSubsetsInternal(input, result, "",0);
        return result;
    }

    private static void findSubsetsInternal(String input, List<String> result, String current, int index) {
        if (index == input.length()) {
            result.add(current);
            return;
        }
        findSubsetsInternal(input, result, current + input.charAt(index), index + 1);
        findSubsetsInternal(input, result, current, index + 1);
    }

    /*
     The idea is to fix a prefix, generate all subsets beginning with the current prefix. After all subsets with a prefix are generated,
     replace the last character with one of the remaining characters.
     */
    public static List<String> powerSet(String input) {
        List<String> result = new ArrayList<>();
        powerSetInternal(input, 0, new StringBuilder(), result);
        return result;
    }

    public static void powerSetInternal(String str, int index, StringBuilder current, List<String> result) {
        int n = str.length();
        // base case
        if (index > n)
            return;
        result.add(current.toString());
        // Try appending remaining characters to current subset
        for (int i = index; i < n; i++)
        {
            current.append(str.charAt(i));
            powerSetInternal(str, i + 1, current, result);
            // Once all subsets beginning with initial "curr" are printed, remove last character to consider a different prefix of subsets.
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void findPowerSet(int[] S, Deque<Integer> set, int n) {
        // if we have considered all elements
        if (n == 0) {
            System.out.println(set);
            return;
        }

        // consider the n'th element
        set.addLast(S[n - 1]);
        findPowerSet(S, set, n - 1);

        set.removeLast();                    // backtrack

        // or don't consider the n'th element
        findPowerSet(S, set, n - 1);
    }

    public static void main(String[] args) {
        //[abc, ab, ac, a, bc, b, c, ]
        System.out.println(findSubsets("abc"));
        System.out.println(powerSet("abc"));

//        int[] S = { 1, 2, 3 };
//
//        Deque<Integer> set = new ArrayDeque<>();
//        findPowerSet(S, set, S.length);
    }
}
