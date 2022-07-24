//package datastructures.graphs;
//
//import datastructures.graphs.representation.Graph;
//
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Set;
//
///*
//        1. Always assume - a graph could have multiple disconnected components
//        2. Maintain a visited array / set to mark visited vertices
//        3. Pattern
//            Iterate over vertices
//            If vertex is not visited then perform traversal - DFS / BFS
//            Keep in mind - vertex counting starts at 0 or 1
//
//            for(int i = 1; i <= 10; i++) {
//                if(!visited[i])
//                    DFS/BFS
//            }
//
// */
//public class _01_ConnectedComponents {
//
//    public static void main(String[] args) {
//        Graph<Integer> graph = new Graph<>(false);
//        graph.addEdge(1, 2);
//        graph.addEdge(2, 3);
//        graph.addEdge(2, 7);
//        graph.addEdge(3, 5);
//        graph.addEdge(7, 5);
//        graph.addEdge(4, 6);
//        System.out.println(connectedComponents(graph));
//    }
//
//    private static int connectedComponents(Graph<Integer> graph) {
//        Set<Integer> visited = new HashSet<>();
//        int count = 0;
//        for(Integer vertex : graph.getVertices()) {
//            if(!visited.contains(vertex)) {
//                bfs(graph, vertex, visited);
//                count++;
//            }
//        }
//        return count;
//    }
//
//    private static void bfs(Graph<Integer> graph, Integer sourceVertex, Set<Integer> visited) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(sourceVertex);
//        visited.add(sourceVertex);
//        while(!queue.isEmpty()) {
//            Integer node = queue.poll();
//            for(Graph<Integer>.Edge<Integer> edge : graph.getEdges(node)) {
//                if(!visited.contains(edge.getDestination())) {
//                    visited.add(edge.getDestination());
//                    queue.add(edge.getDestination());
//                }
//            }
//        }
//    }
//
//}
