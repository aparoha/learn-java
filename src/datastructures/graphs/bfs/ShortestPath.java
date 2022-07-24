package datastructures.graphs.bfs;

import java.util.*;

/*
       1. BFS is the algorithm to use if we want to find the shortest path in an undirected, unweighted graph

 */
public class ShortestPath {

    private static List<Integer> computePath(Map<Integer, List<Integer>> graph, int vertexCount) {
        List<Integer> bfs = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int vertex = 0; vertex < vertexCount; vertex++) {
            if (!visited.contains(vertex)) {
                bfsInternal(graph, bfs, visited, vertex);
            }
        }
        return bfs;
    }

    private static void bfsInternal(Map<Integer, List<Integer>> graph, List<Integer> bfs, Set<Integer> visited, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        visited.add(vertex);
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            bfs.add(current);
            for(Integer neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
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
