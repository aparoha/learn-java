package datastructures.graphs;

import java.util.*;

/*
        1. DFS based algorithm invented by Tarjan back in 1976
        2. Topological ordering of a graph G(V,E) directed graph is a linear ordering of its vertices such that for every directed (u,v)
           edge u comes before v in the ordering
        3. Any DAG has at least one topological order
        4. We can use topological ordering if only the G(V,E) graph does not have any cycles
        5. It has linear running time complexity - O(V + E)
        6. It is crucial in project management and in finding Hamiltonian paths and cycles
        7. Hamiltonian path in a G(V, E) visits every vertex exactly once.
        8. If a hamiltonian path exists then the topological order is unique
        9. If a topological order does not form a hamiltonian path it means the DAG has more valid topological orderings.
        10. It is a NP-complete problem to find the hamiltonian path but we can decide whether such path exists in O(V+E) running time with
            topological ordering
        11. We can find the shortest path in a DAG with topological ordering in O(V+E) linear running time. It only works when there a valid
            topological order exists.

            DAG shortest path

            do the topological ordering on the G(V,E) graph

            for each u vertex in the topological order
                for each edge (u,v) with weight w
                    // Relaxation- check whether a shorter path exists to vertex v including vertex u
                    if distance[u] + w < distance[v]
                        distance[v] = distance[u] + w


 */
public class _03_TopologicalOrdering {

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
                dfsShortestPath(i, visited, graph, topoResult);
            }
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceVertex] = 0;
        Arrays.fill(parent, -1);
        while (!topoResult.isEmpty()) {
            Integer node = topoResult.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (Pair it : graph.get(node)) {
                    if (distance[it.getV()] > distance[node] + it.getWeight()) {
                        distance[it.getV()] = distance[node] + it.getWeight();
                        parent[it.getV()] = node;
                    }
                }
            }
        }
        return new ShortestPath(distance, parent);
    }

    private static void dfsShortestPath(int current, Set<Integer> visited, Map<Integer, List<Pair>> graph, Stack<Integer> topoOrder) {
        visited.add(current);
        for(Pair neighbor : graph.get(current)) {
            if (!visited.contains(neighbor.getV())) {
                dfsShortestPath(neighbor.getV(), visited, graph, topoOrder);
            }
        }
        // visit using stack at last
        topoOrder.push(current);
    }

    public static Stack<Integer> topoOrder(Map<Integer, List<Integer>> graph, int vertexCount) {
        Stack<Integer> topoResult = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < vertexCount; i++) {
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

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {2, 3},
                {3, 1},
                {4, 1},
                {4, 0},
                {5, 0},
                {5, 2}
        };
        int vertexCount = 6;
        Map<Integer, List<Integer>> graph = createDirectedGraph(edges, vertexCount);
        System.out.println(graph);
        //{0=[], 1=[], 2=[3], 3=[1], 4=[1, 0], 5=[0, 2]}
        //[5, 4, 2, 3, 1, 0]
        Stack<Integer> topoOrder = topoOrder(graph, vertexCount);
        while (!topoOrder.isEmpty())
            System.out.println(topoOrder.pop());

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
        int vCount = 6;
        int sourceVertex = 1;
        Map<Integer, List<Pair>> weightedDAG = createWeightedGraph(directedWeightedGraph, vertexCount);
        //{ 0=[Pair{v=1, weight=5}, Pair{v=2, weight=3}],
        //  1=[Pair{v=3, weight=6}, Pair{v=2, weight=2}],
        //  2=[Pair{v=4, weight=4}, Pair{v=5, weight=2}, Pair{v=3, weight=7}],
        //  3=[Pair{v=4, weight=-1}],
        //  4=[Pair{v=5, weight=-2}],
        //  5=[]}
        //[0, 5, 3, 10, 7, 5]
        //[-1, 0, 0, 2, 2, 2]
        System.out.println(weightedDAG);
        ShortestPath shortestPath = dagShortestPath(weightedDAG, vCount, sourceVertex);
        int[] distances = shortestPath.getDistance();
        int[] parent = shortestPath.getParent();
        System.out.println(Arrays.toString(distances));
        System.out.println(Arrays.toString(parent));
        printSolution(sourceVertex, distances, parent);
    }

    private static Map<Integer, List<Integer>> createDirectedGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
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
}
