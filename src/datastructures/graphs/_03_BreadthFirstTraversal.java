package datastructures.graphs;

import java.util.*;

/*

    Space complexity - O(N)

 */
public class _03_BreadthFirstTraversal {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {1, 2},
                {1, 3},
                {1, 4},
                {2, 5},
                {2, 6},
                {5, 10},
                {5, 10},
                {4, 7},
                {4, 8},
                {7, 11},
                {7, 12}
        };
        // vertex 0, 13, and 14 are single nodes
        int vertexCount = 15;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertexCount);
        List<Integer> bfs = traverse(graph, vertexCount);
        System.out.println(bfs);
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

    private static List<Integer> traverse(Map<Integer, List<Integer>> graph, int vertexCount) {
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
}
