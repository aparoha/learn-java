package datastructures.graphs;

import java.util.*;

public class _04_CycleDetectionUndirectedGraph {

    /*
            Detect cycle in undirected graph using BFS
            ------------------------------------------
            When we do a Breadth–first search (BFS) from any vertex v in an undirected graph, we may encounter a cross-edge that points to a previously discovered vertex that is neither
            an ancestor nor a descendant of the current vertex. Each “cross edge” defines a cycle in an undirected graph. If the cross edge is x —> y, then since y is already discovered,
            we have a path from v to y (or from y to v since the graph is undirected), where v is the starting vertex of BFS. So, we can say that we have a path v ~~ x ~ y ~~ v that forms
            a cycle. (Here, ~~ represents one more edge in the path, and ~ represents a direct edge).
            
     */
    private static boolean detectCycleBfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        Set<Integer> visited = new HashSet<>();
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (!visited.contains(vertex)) {
                if (isCycleBfs(graph, visited, vertex))
                    return true;
            }
        }
        return false;
    }

    private static boolean isCycleBfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int vertex) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(vertex, -1));
        visited.add(vertex);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for(Integer neighbor : graph.get(current.value)) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, current.value));
                    visited.add(neighbor);
                }
                // neighbor is already discovered and neighbor is not a parent
                else if (neighbor != current.parent) {
                    // A cross edge found, its a cycle
                    return true;
                }
            }
        }
        return false;
    }

    /*
            Detect cycle in undirected graph using DFS
            ------------------------------------------
            When we do a Depth–first search (DFS) from any vertex v in an undirected graph, we may encounter a back-edge that points to one of the ancestors of the current vertex v in the
            DFS tree. Each “back edge” defines a cycle in an undirected graph. If the back edge is x —> y, then since y is the ancestor of node x, we have a path from y to x. So, we can
            say that we have a path y ~~ x ~ y that forms a cycle. (Here, ~~ represents one more edge in the path, and ~ represents a direct edge).
     */
    private static boolean detectCycleDfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        Set<Integer> visited = new HashSet<>();
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            if (!visited.contains(vertex)) {
                if (isCycleDfs(graph, visited, vertex, -1))
                    return true;
            }
        }
        return false;
    }

    private static boolean isCycleDfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int vertex, int parent) {
        visited.add(vertex);
        for(Integer neighbor : graph.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (isCycleDfs(graph, visited, neighbor, vertex))
                    return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
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

    static class Node {
        private int value;
        private int parent;
        public Node(int value, int parent){
            this.value = value;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4},
                {1, 5},
                {4, 8},
                {4, 9},
                {3, 6},
                {3, 7},
                {6, 10},
                {6, 11},
                {5, 9}
        };

//        int[][] edges = new int[][]{
//                {1, 2},
//                {2, 4},
//                {3, 5},
//                {5, 6},
//                {5, 10},
//                {6, 7},
//                {7, 8},
//                {8, 11},
//                {10, 9},
//                {9, 8},
//        };
        
        int vertexCount = 12;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertexCount);
        boolean isCycle = detectCycleDfs(graph, vertexCount);
        System.out.println(isCycle);
    }
}
