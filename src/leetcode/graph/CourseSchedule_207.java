package leetcode.graph;

import java.util.*;

/*

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.

 */
public class CourseSchedule_207 {

    /*
        Example
        numCourse = 5
        prerequisites = [[0,1],[0,2],[1,3],[1,4],[3,4]]
        prereqMap
        0   -> 1, 2
        1   -> 3, 4
        2   -> []
        3   -> [4]
        4   -> []
        Complexity => O(n + p) => numCourse + prerequisites
        1. Create adjacency list of prerequisites, empty list where no prerequisite
        2. Also maintain a set data structure - visited. It will contain courses we visiting along DFS. It will help us to detect loop
        2. Run depth first search from every single course 0 to numCourse - 1
        3. Start DFS from 0 (add in visited), it has 2 neighbors [1, 2],
            go to 1 (add in visited), it has 2 neighbors [3, 4],
            go to 3 (add in visited), it has 1 neighbor [4],
            go to 4 (add in visited) it has no neighbor i.e. course 4 can be completed
        4. Going back in DFS, Because 4 can be completed and 3 only has 4 as dependency so 3 can also be completed and so on
            - (Update adjacency list with empty list entry while going back for course which can be completed)

     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Map each course to prerequisites list
        Map<Integer, List<Integer>> prereqMap = new HashMap<>();
        for(int i = 0; i < numCourses; i++)
            prereqMap.put(i, new ArrayList<>());
        for(int[] pre : prerequisites)
            prereqMap.get(pre[0]).add(pre[1]);
        // Store all course along the current DFS path
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < numCourses; i++) {
            if(!backtrack(i, visited, prereqMap))
                return false;
        }
        return true;
    }

    private boolean backtrack(int currentCourse, Set<Integer> visited, Map<Integer, List<Integer>> prereqMap) {
        // If course is visited already then its a cycle
        if(visited.contains(currentCourse))
            return false;
        // If prerequisites list is empty then course can be completed
        if(prereqMap.get(currentCourse).equals(Collections.emptyList()))
            return true;
        // Add course in visited and run DFS to its prerequisites
        visited.add(currentCourse);
        for(Integer prereqCourse : prereqMap.get(currentCourse)) {
            if(!backtrack(prereqCourse, visited, prereqMap))
                return false;
        }
        visited.remove(currentCourse);
        prereqMap.put(currentCourse, Collections.emptyList());
        return true;
    }
}
