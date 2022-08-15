package datastructures.trees;

import java.sql.Array;
import java.util.*;

/*
Subtree Problem
You are given a tree with n nodes rooted at 1  where each node is numbered 1 to n.
The tree is given as an array of edges where edges[i] = [ui, vi] is a bidirectional edge between node ui and node vi , and also you are given
an array of queries where query[i] = ui , you have to count the number of nodes present in the subtree of  node ui for each query.
Return an array containing the answer of each query respectively.

Constraints:

2≤ n ≤10^5

1<= edges.length <= n-1

1≤ queries.length ≤10^5

1<= ui, vi <=n



Example:

Input

n = 5
edges = [
    [1, 2],
    [1, 3],
    [3, 4],
    [3, 5]
]

queries = [ 1, 2, 3, 4, 5]
Output

[5, 1, 3, 1, 1]


Expected Time Complexity: O(n + queries.length)
 */
public class SubtreeProblem {

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 2},
                {1, 3},
                {3, 4},
                {3, 5}
        };
        int vertices = 5;
        Map<Integer, List<Integer>> graph = createTree(edges, vertices);
        int[] queries = new int[] {1, 2, 3, 4, 5};
        int[] queryResults = new int[vertices + 1];
        dfs(graph, 1, -1, queryResults);
        System.out.println(Arrays.toString(queryResults));
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int current, int parent, int[] queryResults) {
        queryResults[current] = 1;
        for (int child : graph.get(current)) {
            if (child != parent) {
                dfs(graph, child, current, queryResults);
                queryResults[current] += queryResults[child];
            }
        }
    }

    private static Map<Integer, List<Integer>> createTree(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i <= vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
