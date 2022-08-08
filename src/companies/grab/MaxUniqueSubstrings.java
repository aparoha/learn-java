package companies.grab;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaxUniqueSubstrings {

    public static int maxUniqueSplit(String S)
    {
        return maxUnique(S, new HashSet<>());
    }

    public static int maxUnique(String S, Set<String> set)
    {
        // Stores maximum count of unique substring by splitting the string into substrings
        int max = 0;
        for (int i = 1; i <= S.length(); i++) {
            // Stores prefix substring
            String tmp = S.substring(0, i);
            // Check if the current substring already exists
            if (!set.contains(tmp)) {
                set.add(tmp);
                // Recursively call for remaining characters of string
                max = Math.max(max, maxUnique(S.substring(i), set) + 1);
                // Remove from the set
                System.out.println(tmp);
                set.remove(tmp);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("abbbacb"));
        //System.out.println(maxUniqueSplit("abcd"));
    }
}
