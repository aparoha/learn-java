package datastructures.graphs;

import java.util.*;

/*

    Space complexity - O(logN)

 */
public class _02_DepthFirstTraversal {

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
        System.out.println(graph);
        System.out.println(dfsRecursive(graph, vertexCount));
        System.out.println(dfsIterative(graph, vertexCount));
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

    private static List<Integer> dfsRecursive(Map<Integer, List<Integer>> graph, int vertexCount) {
        List<Integer> dfsResult = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < vertexCount; i++) {
            if (!visited.contains(i)) {
                dfsRecursiveHelper(i, visited, graph, dfsResult);
            }
        }
        return dfsResult;
    }

    private static void dfsRecursiveHelper(int current, Set<Integer> visited, Map<Integer, List<Integer>> graph, List<Integer> dfsResult) {
        visited.add(current);
        dfsResult.add(current);
        for(Integer neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursiveHelper(neighbor, visited, graph, dfsResult);
            }
        }
    }

    private static List<Integer> dfsIterative(Map<Integer, List<Integer>> graph, int vertexCount) {
        List<Integer> dfsResult = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < vertexCount; i++) {
            if (!visited.contains(i)) {
                dfsIterativeHelper(i, visited, graph, dfsResult);
            }
        }
        return dfsResult;
    }

    private static void dfsIterativeHelper(int vertex, Set<Integer> visited, Map<Integer, List<Integer>> graph, List<Integer> dfsResult) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        visited.add(vertex);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            dfsResult.add(current);
            for (int neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}
