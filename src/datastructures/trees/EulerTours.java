package datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    An Euler path that goes through every edge of a graph exactly once. An Euler path starts and ends at different vertices
    An Euler circuit is an Euler path that begins and ends at the same vertex
    Euler tour of tree - flattening a tree. Euler tour tree (ETT) is a method for representing a rooted undirected tree as a number sequence

    3 types of euler tours
    1. Store the node every time you visit it
            For every edge, we store 2 nodes
            SC = 2 * (n - 1) = O(n)
            TC - O(n)

                 1
                / \
               2   5
              / \
             3   4

            1 2 3 2 4 2 1 5 1

    2. Store every node twice
        - when you visit it first time
        - when its whole subtree is explored
        TC, SC - O(n)

                 1
                / \
               2   5
              / \
             3   4

            1 2 3 3 4 4 2 5 5 1

    3. Using Edges

             1
            / \
           2   5
          / \
         3   4

        [1-2] [2-3] [3-2] [2-4] [4-2] [2-1] [1-5] [5-1]
 */
public class EulerTours {

    public static void dfs(Map<Integer, List<Integer>> graph, int current, int parent) {
        System.out.println(current);
        for (int child : graph.get(current)) {
            if (child != parent)
                dfs(graph, child, current);
        }
        System.out.println(current);
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {5, 10},
                {5, 6},
                {5, 7},
                {3, 8},
                {8, 9},
                {8, 11}
        };
        int vertices = 12;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertices);
        dfs(graph, 1, -1);
    }

    private static Map<Integer, List<Integer>> createGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
