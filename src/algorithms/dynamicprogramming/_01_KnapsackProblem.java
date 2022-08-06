package algorithms.dynamicprogramming;

/*
    Recursion -> Memoization -> Tabulation

    1. 0-1 Knapsack
        subset sum
        equal sum partition
        count of subset sum
        minimum subset sum difference
        target sum
        no of subset of given difference
    2. Other knapsacks - Unbounded Knapsack, Fractional Knapsack
    3. Fibonacci
    4. Longest common subsequence
    5. Longest Increasing subsequence
    6. Kadane's algorithm
    7. Matrix chain multiplication
    8. DP on trees
    9. DP on grids
 */

import java.util.HashMap;
import java.util.Map;

/*
 In the 0–1 Knapsack problem, we are given a set of items, each with a weight and a value, and we need to determine the number of each
 item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as
 possible.

Please note that the items are indivisible; we can either take an item or not (0-1 property). For example,

Input:

value = [ 20, 5, 10, 40, 15, 25 ]
weight = [ 1, 2, 3, 8, 7, 4 ]
int W = 10

Output: Knapsack value is 60

value = 20 + 40 = 60
weight = 1 + 8 = 9 < W

 */
public class _01_KnapsackProblem {

    /*
    The idea is to use recursion to solve this problem. For each item, there are two possibilities:
    1. If items' weight > bag's weight, exclude it
    2. If item's weight <= bag's weight then we have 2 choices
        Include or exclude
     */
    public static int recursive(int[] items, int[] weights, int totalWeight) {
        int size = items.length;
        return recursiveInternal(items, weights, size, totalWeight);
    }

    private static int recursiveInternal(int[] items, int[] weights, int size, int w) {
        //base case
        //If there is empty items, weights or total weight is 0
        if (size == 0 || w == 0)
            return 0;
        if (weights[size - 1] <= w) {
            int include = items[size - 1] + recursiveInternal(items, weights, size - 1, w - weights[size - 1]);
            int exclude = recursiveInternal(items, weights, size - 1, w);
            return Math.max(include, exclude);
        } else //exclude
            return recursiveInternal(items, weights, size - 1, w);
    }

    public static int memoize(int[] items, int[] weights, int totalWeight) {
        int size = items.length;
        //Create lookup for variables which are changing with each recursive call
        Map<String, Integer> dp = new HashMap<>();
        return memoizeInternal(items, weights, size, totalWeight, dp);
    }

    private static int memoizeInternal(int[] items, int[] weights, int size, int w, Map<String, Integer> dp) {
        if (size == 0 || w == 0)
            return 0;
        String key = size + "|" + w;
        if (dp.containsKey(key))
            return dp.get(key);
        if (weights[size - 1] <= w) {
            int include = items[size - 1] + recursiveInternal(items, weights, size - 1, w - weights[size - 1]);
            int exclude = recursiveInternal(items, weights, size - 1, w);
            dp.put(key, Math.max(include, exclude));
        } else {
            //exclude
            dp.put(key, recursiveInternal(items, weights, size - 1, w));
        }
        return dp.get(key);
    }

    public static void main(String[] args) {
        // input: a set of items, each with a weight and a value
        int[] v = { 20, 5, 10, 40, 15, 25 };
        int[] w = { 1, 2, 3, 8, 7, 4 };

        // knapsack capacity
        int W = 10;
        System.out.println(recursive(v, w, W));
        System.out.println(memoize(v, w, W));
    }

}
