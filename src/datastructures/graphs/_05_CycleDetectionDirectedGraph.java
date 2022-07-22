package datastructures.graphs;

import java.util.*;

/*
        Using DFS
        ---------

        1. Algorithm we used to detect cycle in undirected graph wont work here
        2. Steps
            a. Create 2 sets / arrays - visited, dfsVisited
            b. Iterate over vertices
            c. If vertex is not visited, call isCycle recursively
            d. Inside isCycle
                d.1 Mark vertex as visited and dfsVisited
                d.2. Iterate over neighbors of current passed vertex
                d.3 If neighbor node is not visited, call isCycle recursively with neighbor as current vertex
                d.4 When path from vertex is already explored (coming back from dfs) remove that vertex from dfsVisited


 */
public class _05_CycleDetectionDirectedGraph {

    private static boolean detectCycleBfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> dfsVisited = new HashSet<>();
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (!visited.contains(vertex)) {
                if (isCycleDfs(graph, vertex, visited, dfsVisited))
                    return true;
            }
        }
        return false;
    }

    private static boolean detectCycleDfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> dfsVisited = new HashSet<>();
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (!visited.contains(vertex)) {
                if (isCycleDfs(graph, vertex, visited, dfsVisited))
                    return true;
            }
        }
        return false;
    }

    private static boolean isCycleDfs(Map<Integer, List<Integer>> graph, int vertex, Set<Integer> visited, Set<Integer> dfsVisited) {
        visited.add(vertex);
        dfsVisited.add(vertex);
        for(Integer neighbor : graph.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (isCycleDfs(graph, neighbor, visited, dfsVisited))
                    return true;
            } else if (dfsVisited.contains(neighbor)) {
                return true;
            }
        }
        dfsVisited.remove(vertex);
        return false;
    }

    public static void main(String[] args) {
        int[][] cyclicEdges = new int[][]{
                {0, 1},
                {0, 2},
                {1, 2},
                {2, 0},
                {2, 3},
                {3, 3}
        };
//        int[][] acyclicEdges = new int[][]{
//                {0, 1},
//                {0, 2},
//                {1, 2}
//        };
        int vertexCount = 4;
        Map<Integer, List<Integer>> graph = createDirectedGraph(cyclicEdges, vertexCount);
        System.out.println(detectCycleDfs(graph, vertexCount));
    }

    private static Map<Integer, List<Integer>> createDirectedGraph(int[][] edges, int vertexCount) {
        // 1 based index
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }
}
