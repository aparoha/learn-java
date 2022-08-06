package datastructures.graphs.articulationpoints;

import java.util.*;

/*
    A vertex V in a connected graph is an articulation point iff the deletion of vertex V together with all edges incident to V
    disconnects the graph into two or more non empty components

    A bridge is an edge, removing it disconnects the graph

    discovered time - time at which current node is discovered using DFS

    lowest time - It is the minimum discovered time of a node, to which we can traverse, by using at most one backedge in current subtree

    Any graph n nodes, m edges
    total edge = n - 1
    back edges = m - (n - 1)

    Algorithm - check if edge between current and x is bridge or not?
        Bridge edge condition - if low[x] > discovery[current] i.e. from any subtree of x (including x), I cant reach any node above current i.e. there is
        no back edge from subtree of x to the nodes above current node

        if low[x] == discovery[current] , then its not bridge edge
        if low[x] < discovery[current], then its not bridge edge
        For both conditions, there could be a back edge exists between subtree of x and pointing to current or nodes above current
        --------------------------------------------------------------------------------------------------------------------------
        Articulation point condition - low[x] >= discovery[current]
        Special condition for root node - if no of child of root node > 1 in the DFS tree then root is an articulation point




                                    1
                                    |
                                    |
                                    |
             discovery[current]    current    low[current]
                                    |
                                    |
                                    |
             discovery[x]           x          low[x]



 */
public class ArticulationPoint {

    private static void dfs(Map<Integer, List<Integer>> graph,
                     int currentVertex,
                     int parent,
                     int time,
                     Set<Integer> visited,
                     int[] discover,
                     int[] low, List<int[]> bridges, List<Integer> articulationpoints
                            ) {
        visited.add(currentVertex);
        discover[currentVertex] = low[currentVertex] = time++;
        int child = 0;
        for (int neighbor : graph.get(currentVertex)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, currentVertex, time, visited, discover, low, bridges, articulationpoints);
                child++;
                // Now we know discover and low time of neighbor
                // Set low of parent minimum of its current low value and low of child value
                low[currentVertex] = Math.min(low[currentVertex], low[neighbor]);
                // bridges
                if (low[neighbor] > discover[currentVertex]) {
                    bridges.add(new int[] { currentVertex, neighbor});
                }
                // articulation point
                if (parent != -1 && low[neighbor] >= discover[currentVertex]) {
                    articulationpoints.add(currentVertex);
                }
            } else if (neighbor != parent){
                // back edge
                low[currentVertex] = Math.min(low[currentVertex], discover[neighbor]);
            }
        }

        // Check if root is an articulation point
        if (parent == -1 && child > 1) {
            articulationpoints.add(currentVertex);
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {1, 2},
                {1, 3},
                {2, 3},
                {2, 4},
                {4, 5},
                {4, 6},
                {5, 7},
                {6 , 7}

        };
        int vertexCount = 8;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertexCount);
        int[] discover = new int[vertexCount];
        int[] low = new int[vertexCount];
        Set<Integer> visited = new HashSet<>();
        List<int[]> bridges = new ArrayList<>();
        List<Integer> articulationpoints = new ArrayList<>();
        dfs(graph, 1, -1, 1, visited, discover, low, bridges, articulationpoints);
        for (int[] bridge : bridges)
            System.out.println("Bridge " + bridge[0] + "->" + bridge[1]);
        for (int ap : articulationpoints)
            System.out.println("Articulation point " + ap);
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
