package datastructures.graphs.dag;

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
public class TopologicalOrderingDfs {

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
}
