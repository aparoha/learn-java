package datastructures.graphs.shortestpath;

import java.util.ArrayList;
import java.util.List;

/*
        Single Source Shortest Path - Shortest path from one node to all other nodes
        ---------------------------

        1. It will work with negative edge weights but fails with negative cycles
        2. Follows dynamic programming
        3. It says to relax all edges n -1 times where n is no of vertices
        4. What is relaxation?
                If there is an edge between a pair of vertices (u,v) then check
                    distance[v] = min{distance[v], distance[u] + cost(u, v)}
        5. Steps
            1. Take all edges and total vertex count i.e. n
            2. Mark the distances of all vertices as INF
            3. Relax all edges n - 1 times
        6. Time complexity
            O (E * (V -1)) ~ O(V^2)
        7. Why Dijkstra's fail with negative weight edge? -> Because it is a greedy algorithm
                        3     5      -2     1
                    S ---> A ---> B ---> C ---> D

        1. Slower than Dijkstra but it is more robust
        2. Running time complexity O(V*E)
        3. It does V-1 iterations and then an extra one to detect cycles, if cost decreases in the Vth iteration then there is a negative
           cycle because all the paths are considered in the V-1 iterations

 */

class Vertex {
    private String name;
    // the distance from the source vertex
    private double distance;
    // the previous vertex of the shortest path
    private Vertex parent;
    private List<Edge> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public List<Edge> getAdjacencyList() {
        return adjacencyList;
    }

    public void addNeighbor(Edge edge) {
        this.adjacencyList.add(edge);
    }

    @Override
    public String toString() {
        return name + "-" + distance;
    }
}

class Edge {
    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(double weight, Vertex startVertex, Vertex targetVertex) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }
}

public class _02_SSSPBellmanFord {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public _02_SSSPBellmanFord(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public void run(Vertex source) {
        source.setDistance(0);
        //V-1 iterations
        for (int i = 0; i < vertexList.size() - 1; i++) {
            // Relaxation
            for (Edge edge : edgeList) {
                Vertex u = edge.getStartVertex();
                Vertex v = edge.getTargetVertex();
                double d = u.getDistance() + edge.getWeight();
                if (v.getDistance() > d) {
                    v.setDistance(d);
                    v.setParent(u);
                }
            }
        }

        //If we make an additional relaxation and there is shorter paths then we know there is a negative cycle in the graph
        for (Edge edge : edgeList) {
            if (edge.getStartVertex().getDistance() != Double.MAX_VALUE) {
                if (hasCycle(edge)) {
                    System.out.println("There is a negative cycle");
                    return;
                }
            }
        }
    }

    private boolean hasCycle(Edge edge) {
        return edge.getStartVertex().getDistance() + edge.getWeight()
                < edge.getTargetVertex().getDistance();
    }

}
