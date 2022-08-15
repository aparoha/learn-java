package datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
        1. No need of visited array
        2. Properties
            How many parents will be there for each node in tree?
                Each node has one parent except root node
                n - 1 edges
                We can use this property to perform DFS instead of visited array
 */
public class DepthFirstTraversal {

    public static void dfs(Map<Integer, List<Integer>> graph, int current, int parent) {
        System.out.println(current);
        for (int child : graph.get(current)) {
            if (child != parent)
                dfs(graph, child, current);
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 2},
                {1, 3},
                {1, 10},
                {2, 9},
                {2, 4},
                {2, 5},
                {3, 7},
                {3, 8},
                {5, 6}
        };
        int vertices = 11;
        Map<Integer, List<Integer>> graph = createTree(edges, vertices);
        dfs(graph, 1, -1);
    }

    private static Map<Integer, List<Integer>> createTree(int[][] edges, int vertexCount) {
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
