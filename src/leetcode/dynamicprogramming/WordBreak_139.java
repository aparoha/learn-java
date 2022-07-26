package leetcode.dynamicprogramming;

import java.util.*;

public class WordBreak_139 {

    // Approach 1 - BFS
    public boolean WordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.poll();
            if(visited.contains(start))
                continue;
            for(int end = start + 1; end <= s.length(); end++){
                String word = s.substring(start, end);
                if(set.contains(word)){
                    if(end == s.length())
                        return true;
                    queue.add(end);
                }
            }
            visited.add(start);
        }
        return false;
    }
}
