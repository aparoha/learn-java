package datastructures.graphs.stronglyconnectedcomponents;

import java.util.*;

/*
        1. Finish time - time when we leave the node in DFS
        2. Fo any two nodes u and v, if there is an edge from u->v, finish time of u will be higher than finish time of v
        3. Strongly connected applies only to directed graphs. A directed graph is strongly connected if there is a directed path from
           any vertex to every other vertex
        4. All the nodes in a cycle part of SCC
        5. Condensed component graph - It is a DAG in which every node is SCC and edges are between nodes if there is an edge from
           one of the nodes of these SCC in original graph.
            Source - Node with no incoming edges
            Sink - Node with no outgoing edges
        6. A condensed component graph has at least one source node and at least one sink node
        7. Algorithm
            Perform a topological sort of graph G(V, E)
            Reverse the original graph
            Perform DFS on reversed graph



 */
public class KosarajuAlgorithm {

    public static Stack<Integer> topoOrder(Map<Integer, List<Integer>> graph, int vertexCount) {
        Stack<Integer> topoResult = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 1; i <= vertexCount; i++) {
            if (!visited.contains(i)) {
                dfs(i, visited, graph, topoResult);
            }
        }
        return topoResult;
    }

    private static void dfs(int current, Set<Integer> visited, Map<Integer, List<Integer>> graph, Stack<Integer> topoOrder) {
        visited.add(current);
        for(Integer neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, graph, topoOrder);
            }
        }
        // visit using stack at last
        topoOrder.push(current);
    }

    private static void dfs2(int current, Set<Integer> visited, Map<Integer, List<Integer>> graph, int component, int[] color) {
        visited.add(current);
        color[current] = component;
        for(Integer neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                dfs2(neighbor, visited, graph, component, color);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {1, 2},
                {2, 3},
                {3, 1},
                {3, 4}
        };
//        int[][] edges = new int[][]{
//                {1, 2},
//                {2, 3},
//                {3, 1},
//                {3, 4},
//                {4, 3}
//        };
        int vertexCount = 4;
        Map<Integer, List<Integer>> graph = createDirectedGraph(edges, vertexCount);
        Stack<Integer> topoOrder = topoOrder(graph, vertexCount);
        Map<Integer, List<Integer>> reverseGraph = createDirectedGraph(edges, vertexCount);
        Set<Integer> visited = new HashSet<>();
        int component = 1;
        int[] color = new int[vertexCount + 1];
        for (int node : topoOrder) {
            if (!visited.contains(node))
                dfs2(node, visited, reverseGraph, component++, color);
        }
        for (int i = 1; i <= vertexCount; i++)
            System.out.println(i + "->" + color[i]);
        System.out.println("Total strongly connected components " + (component - 1));
    }

    private static Map<Integer, List<Integer>> createDirectedGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i <= vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }

    private static Map<Integer, List<Integer>> createReverseDirectedGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i <= vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}

