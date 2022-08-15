package datastructures.trees;

import java.util.*;

/*
        How to use this logic to print path between 2 nodes x and y
        Make y as root of tree
        perform dfs from y
        print all ancestors from y to x
 */
public class AllAncestors {

    public static void dfs(Map<Integer, List<Integer>> graph, int current, int parent, int[] parents) {
        parents[current] = parent;
        for (int child : graph.get(current)) {
            if (child != parent)
                dfs(graph, child, current, parents);
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
        int vertices = 10;
        Map<Integer, List<Integer>> graph = createTree(edges, vertices);
        int[] parents = new int[vertices + 1];

        //Root is 1
        dfs(graph, 1, -1, parents);

        // Print all parents of all nodes
        for (int i = 1; i <= vertices; i++) {
            System.out.println("Ancestors of " + i);
            printAncestors(parents, i);
        }

        Arrays.fill(parents, 0);

        //Root is 10
        dfs(graph, 10, -1, parents);

        // Print all parents of all nodes
        for (int i = 1; i <= vertices; i++) {
            System.out.println("Ancestors of " + i);
            printAncestors(parents, i);
        }
    }

    private static void printAncestors(int[] parents, int x) {
        while (x != -1) {
            System.out.println(x);
            x = parents[x];
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
