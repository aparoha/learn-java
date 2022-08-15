package algorithms.dynamicprogramming;

import java.util.HashMap;

/*
No of ways to fill an array with 0 and 1 such that there are no consecutive 1

Lets say, I have empty array of length 3, place 0 at index 0

                                0
                           /        \
                           0        1
                     /          \   \
                     0          1    0

Tree branches when place 0 at index 0
                     0  0   0
                     0  0   1
                     0  1   0

        Place 1 at index 0

                1
              /
            0
          /    \
          0     1

          Tree branches when place 1 at index 0
                     1  0   0
                     1  0   1

                     f(index) -- no of ways filling array if I place 0 or 1 at given index
                     we also need to keep track of previous position to avoid placing consecutive 1's


 */
public class _02_NoOfWays {

    static int count;
    // one - represents whether the number at previous index is 1
    public static int findWaysRecursive(int index, int length, boolean previousOne) {
        count++;
        // Case when I reached leaf node of tree and it was valid arrangement
        if (index == length)
            return 1;
        int ways = 0;
        // Either place 0
        ways += findWaysRecursive(index + 1, length, false);
        // Or place 1
        if (!previousOne)
            ways += findWaysRecursive(index + 1, length, true);
        return ways;
    }

    public static int findWaysMemoization(int index, int length, boolean previousOne) {
        return findWaysMemoizationInternal(index, length, previousOne, new HashMap<>());
    }

    public static int findWaysMemoizationInternal(int index, int length, boolean previousOne, HashMap<String, Integer> cache) {
        count++;
        // Case when I reached leaf node of tree and it was valid arrangement
        if (index == length)
            return 1;
        int ways = 0;
        String key = index + "|" + previousOne;
        if (cache.containsKey(key))
            return cache.get(key);
        // Either place 0
        ways += findWaysMemoizationInternal(index + 1, length, false, cache);
        // Or place 1
        if (!previousOne)
            ways += findWaysMemoizationInternal(index + 1, length, true, cache);
        cache.put(key, ways);
        return cache.get(key);
    }

    public static void main(String[] args) {
        System.out.println(findWaysRecursive(0, 10, false));
        System.out.println(count);
        count = 0;
        System.out.println(findWaysMemoization(0, 10, false));
        System.out.println(count);
    }
}
