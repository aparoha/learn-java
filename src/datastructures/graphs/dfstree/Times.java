package datastructures.graphs.dfstree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

The idea is to run Depth–first search (DFS). Before exploring any adjacent nodes of any vertex in DFS, note the vertex’s arrival time.
After exploring all adjacent nodes of the vertex, note its departure time. After the DFS call is over (i.e., all the graph vertices are
discovered), print the vertices’ arrival and departure time.
The time complexity of DFS traversal is O(V + E), where V and E are the total number of vertices and edges in the graph, respectively.
Please note that O(E) may vary between O(1) and O(V^2), depending on how dense the graph is.

    Applications of finding Arrival and Departure Time:

    Topological sorting in a DAG(Directed Acyclic Graph).
    Finding 2/3–(edge or vertex)–connected components.
    Finding bridges in graphs.
    Finding biconnectivity in graphs.
    Detecting cycle in directed graphs.
    Tarjan’s algorithm to find strongly connected components, and many more…
 */
public class Times {

    static class Edge
    {
        int source, dest;
        public Edge(int source, int dest)
        {
            this.source = source;
            this.dest = dest;
        }
    }

    static class Graph
    {
        List<List<Integer>> adjList;
        Graph(List<Edge> edges, int n)
        {
            adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (Edge edge: edges) {
                adjList.get(edge.source).add(edge.dest);
            }
        }
    }

    // Function to perform DFS traversal on the graph on a graph
    public static int DFS(Graph graph, int v, boolean[] visited,
                          int[] arrival, int[] departure, int time)
    {
        // set the arrival time of vertex `v`
        arrival[v] = ++time;

        // mark vertex as visited
        visited[v] = true;

        for (int i: graph.adjList.get(v))
        {
            if (!visited[i]) {
                time = DFS(graph, i, visited, arrival, departure, time);
            } else {
                //back edge
            }
        }

        // set departure time of vertex `v`
        departure[v] = ++time;

        return time;
    }

    public static void main(String[] args)
    {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 2), new Edge(2, 3), new Edge(2, 4),
                new Edge(3, 1), new Edge(3, 5), new Edge(4, 5), new Edge(6, 7)
        );

        // total number of nodes in the graph (labelled from 0 to 7)
        int n = 8;
        Graph graph = new Graph(edges, n);

        // array to store the arrival time of vertex
        int[] arrival = new int[n];

        // array to store the departure time of vertex
        int[] departure = new int[n];

        // mark all the vertices as not visited
        boolean[] visited = new boolean[n];
        int time = -1;

        // Perform DFS traversal from all undiscovered nodes to
        // cover all unconnected components of a graph
        for (int i = 0; i < n; i++)
        {
            if (!visited[i]) {
                time = DFS(graph, i, visited, arrival, departure, time);
            }
        }

        // print arrival and departure time of each vertex in DFS
        for (int i = 0; i < n; i++)
        {
            System.out.println("Vertex " + i + " (" + arrival[i] + ", " +
                    departure[i] + ")");
        }
    }
}
