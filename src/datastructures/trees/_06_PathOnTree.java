package datastructures.trees;

import java.util.*;

/*

Path on Tree

You are given a tree with n nodes where each node has labels in the set {1,2,.......,n} .
The tree is given as an array of edges where edges[i] = [ui, vi, ci] is a bidirectional edge between node ui and node vi of length ci,
and also you are given an array of queries and an integer k where query[i] = [ui, vi] , you have to calculate the length of the shortest
path from node ui to node vi via vertex k for each query.

Return an array containing the answer of each query respectively.

Constraints:

3≤ n ≤10^5
2<= edges.length <= n-1
1≤ queries.length ≤10^5
1<= k <= n
1<= c <= 10^4
1<= ui, vi <= n

ui != vi  , ui != k and vi != k

Example:

Input

n = 5 , k = 1
edges = [
    [1, 2, 1],
    [1, 3, 1],
    [2, 4, 1],
    [3, 5, 1]
]

queries = [
    [2, 4],
    [2, 3],
    [4, 5]
]
Output

[3, 2, 4]
Explanation

The shortest paths for the three queries are as follows:

Query 1: Vertex 2 → Vertex 1 → Vertex 2 → Vertex 4 : Length 1+1+1=3.
Query 2: Vertex 2 → Vertex 1 → Vertex 3 : Length 1+1=2.
Query 3: Vertex 4 → Vertex 2 → Vertex 1 → Vertex 3 → Vertex 5 : Length 1+1+1+1=4

Expected Time Complexity: O( n + queries.length)
 */
public class _06_PathOnTree {

    /*
        1. Perform dfs starting k
        2. Update distance array - representing distance of each node from k
        3. Answer queries

     */
    public static List<Integer> getPathsOnTree(Map<Integer, List<Integer[]>> tree, int vertices, int k, int[][] queries) {
        int[] distance = new int[vertices + 1]; // 1 based indexing of vertices
        dfs(tree, distance, k,-1, 0);
        List<Integer> paths = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            // distance of [i][0] from k + distance of [i][1] from k
            int value = distance[queries[i][0]] + distance[queries[i][1]];
            paths.add(value);
        }
        return paths;
    }

    private static void dfs(Map<Integer, List<Integer[]>> tree, int[] distance, int current, int parent, int weight) {
        distance[current] = weight;
        for (Integer[] nodeEdgeWeightTuple : tree.get(current)) {
            int neighbor = nodeEdgeWeightTuple[0];
            int neighborEdgeWeight = nodeEdgeWeightTuple[1];
            if (neighbor != parent)
                dfs(tree, distance, neighbor, current, weight + neighborEdgeWeight);
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 2, 1},
                {1, 3, 1},
                {2, 4, 1},
                {3, 5, 1}
        };
        int vertices = 5;
        Map<Integer, List<Integer[]>> tree = createTree(edges, vertices);
        int k = 1;
        int[][] queries = new int[][] {
                {2, 4},
                {2, 3},
                {4, 5}
        };
        List<Integer> answers = getPathsOnTree(tree, vertices, k, queries);
        System.out.println(answers);
    }

    private static Map<Integer, List<Integer[]>> createTree(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer[]>> graph = new HashMap<>();
        for(int i = 1; i <= vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Integer[] { edge[1], edge[2] });
            graph.get(edge[1]).add(new Integer[] { edge[0], edge[2] });
        }
        return graph;
    }
}
