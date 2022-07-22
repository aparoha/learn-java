package datastructures.graphs;

import java.util.*;

/*
    1. A graph that can be colored using 2 colors such that no 2 adjacent nodes have same color
    2. If a graph is bipartite it doesn’t contains any odd length cycles, but, if a graph is non-bipartite it surely contains at least
       one odd length cycle.

    Steps

    1. Create a color array, initialize colors of all nodes with some default value i.e. -1
    2. Create a queue to do BFS
    3. We'll color with 2 colors - 0 and 1
    4. Add start node to queue, color it with 0 in color array
    5. poll node out of queue and color adjacent nodes with opposite color of popped off node

 */
public class _05_BipartiteGraph {

    public static boolean isBipartiteDfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        int[] color = new int[vertexCount];
        Arrays.fill(color, -1);
        for(int vertex = 0; vertex < vertexCount; vertex++) {
            if (color[vertex] == -1 && !dfsCheck(graph, vertex, color))
                return false;
        }
        return true;
    }

    private static boolean dfsCheck(Map<Integer, List<Integer>> graph, int vertex, int[] color) {
        if(color[vertex] == -1)
            color[vertex] = 1;
        for(int neighbor : graph.get(vertex)) {
            if(color[neighbor] == -1){
                color[neighbor] = 1 - color[vertex];
                if(!dfsCheck(graph, neighbor, color)){
                    return false;
                }
            }
            else if(color[neighbor] == color[vertex]){
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartiteBfs(Map<Integer, List<Integer>> graph, int vertexCount) {
        int[] color = new int[vertexCount];
        Arrays.fill(color, -1);
        for(int vertex = 0; vertex < vertexCount; vertex++) {
            if (color[vertex] == -1 && !bfsCheck(graph, vertex, color))
                return false;
        }
        return true;
    }

    private static boolean bfsCheck(Map<Integer, List<Integer>> graph, int vertex, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        color[vertex] = 1;
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (Integer neighbor : graph.get(current)) {
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[current];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[current])
                    return false;
            }
        }
        return true;
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

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {4, 3},
                {4, 5},
                {4, 6},
                {1, 6}
        };
        // vertex 0, 13, and 14 are single nodes
        int vertexCount = 7;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertexCount);
        if (isBipartiteBfs(graph, vertexCount))
            System.out.println("Yes Bipartite");
        else
            System.out.println("Not Bipartite");
    }
}
