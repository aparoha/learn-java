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
                set.remove(tmp);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("abbbacb"));
        System.out.println(maxUniqueSplit("abcd"));

//        int[] arr = new int[] {1, 2, 3, 4};
//        int N = arr.length;
//        // visited array to check if value is seen already
//        boolean[] visited = new boolean[N + 1];
//        int minimumSwaps = 0;
//        Arrays.fill(visited,false);
//
//
//        for (int i = 0; i < N; i++) {
//
//            // If the arr[i] is seen first time
//            if (visited[arr[i]] == false) {
//                visited[arr[i]] = true;
//
//                // stores the number of swaps required to
//                // find the correct position of current
//                // element's partner
//                int count = 0;
//
//                for (int j = i + 1; j < N; j++) {
//
//                    // Increment count only if the current
//                    // element has not been visited yet (if is
//                    // visited, means it has already been placed
//                    // at its correct position)
////                    if (visited[arr[j]] == false)
////                        count++;
//
//                        // If current element's partner is found
//                    if (arr[i] == arr[j])
//                        count++;
//                }
//                minimumSwaps = Math.max(minimumSwaps, count);
//            }
//        }
//        System.out.println(minimumSwaps);
    }
}
