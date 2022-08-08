package datastructures.graphs.dag;

import java.util.*;

public class ShortestPathDAG {

    static class ShortestPath {
        private int[] distance;
        private int[] parent;

        public ShortestPath(int[] distance, int[] parent) {
            this.distance = distance;
            this.parent = parent;
        }

        public int[] getDistance() {
            return distance;
        }

        public int[] getParent() {
            return parent;
        }

    }

    static class Pair {
        private int v;
        private int weight;

        public Pair(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int getV() {
            return v;
        }
        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "v=" + v +
                    ", weight=" + weight +
                    '}';
        }
    }

    /*
This runs in linear time (with the possible exception of finding the ordering), and works even when the graph has negative length edges.
You can even use it to find longest paths: just negate the lengths of all the edges.
The only catch is that it only works when we can find a topological ordering.
 */
    public static ShortestPath dagShortestPath(Map<Integer, List<Pair>> graph, int vertexCount, int sourceVertex) {
        Stack<Integer> topoResult = new Stack<>();
        int[] distance = new int[vertexCount];
        int[] parent = new int[vertexCount];
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < vertexCount; i++) {
            if (!visited.contains(i)) {
                getTopologicalOrder(i, visited, graph, topoResult);
            }
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceVertex] = 0;
        Arrays.fill(parent, -1);
        while (!topoResult.isEmpty()) {
            Integer node = topoResult.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (Pair it : graph.get(node)) {
                    int d = distance[node] + it.getWeight();
                    if (distance[it.getV()] > d) {
                        distance[it.getV()] = d;
                        parent[it.getV()] = node;
                    }
                }
            }
        }
        return new ShortestPath(distance, parent);
    }

    private static void getTopologicalOrder(int current, Set<Integer> visited, Map<Integer, List<Pair>> graph, Stack<Integer> topoOrder) {
        visited.add(current);
        for(Pair neighbor : graph.get(current)) {
            if (!visited.contains(neighbor.getV())) {
                getTopologicalOrder(neighbor.getV(), visited, graph, topoOrder);
            }
        }
        // visit using stack at last
        topoOrder.push(current);
    }

    private static void printSolution(int startVertex, int[] distances, int[] parents)
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                String distance = distances[vertexIndex] == Integer.MAX_VALUE ? "INF" : String.valueOf(distances[vertexIndex]);
                System.out.print(distance  + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Function to print shortest path from source to currentVertex using parents array
    private static void printPath(int currentVertex, int[] parents)
    {
        // Base case : Source node has been processed
        if (currentVertex == -1)
            return;
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    private static Map<Integer, List<Pair>> createWeightedGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for(int i = 0; i < vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }
        return graph;
    }

    public static void main(String[] args) {
        int[][] directedWeightedGraph = new int[][] {
                {0, 1, 5},
                {0, 2, 3},
                {1, 3, 6},
                {1, 2, 2},
                {2, 4, 4},
                {2, 5, 2},
                {2, 3, 7},
                {3, 4, -1},
                {4, 5, -2}
        };
        int vCount = 7;
        int sourceVertex = 1;

        // Create adjacency list
        Map<Integer, List<Pair>> weightedDAG = createWeightedGraph(directedWeightedGraph, vCount);
        System.out.println(weightedDAG);

        // Find the shortest path
        ShortestPath shortestPath = dagShortestPath(weightedDAG, vCount, sourceVertex);

        //Print it
        int[] distances = shortestPath.getDistance();
        int[] parent = shortestPath.getParent();
        System.out.println(Arrays.toString(distances));
        System.out.println(Arrays.toString(parent));
        printSolution(sourceVertex, distances, parent);
    }
}
