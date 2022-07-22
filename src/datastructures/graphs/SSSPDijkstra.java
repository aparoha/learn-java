package datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
        1. It can not handle negative weights
        2. Greedy algorithm - find global optimum with local optimum. On every iteration we want to find the minimum distance to the
           next vertex possible
        3. It is asymptotically the fastest known single source shortest path algorithm for arbitrary directed graph with unbounded
           non-negative weights
        4. O(VlogV + E)

 */
public class SSSPDijkstra {

    static class Edge {
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

    static class Vertex implements Comparable<Vertex> {
        private String name;
        private boolean visited;
        private List<Edge> adjacencyList;
        // distance from source vertex
        private double distance;
        private Vertex parent;

        @Override
        public String toString() {
            return name + "-" + distance;
        }

        public Vertex(String name) {
            this.name = name;
            this.adjacencyList = new ArrayList<>();
            this.distance = Double.MAX_VALUE;
        }

        public void addNeighbor(Edge edge) {
            this.adjacencyList.add(edge);
        }

        public String getName() {
            return name;
        }

        public boolean isVisited() {
            return visited;
        }

        public List<Edge> getAdjacencyList() {
            return adjacencyList;
        }

        public double getDistance() {
            return distance;
        }

        public Vertex getParent() {
            return parent;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        @Override
        public int compareTo(Vertex otherVertex) {
            // v1 < v2 if v1.distance < v2.distance
            // v2 < v1 if v2.distance < v1.distance
            return Double.compare(this.distance, otherVertex.getDistance());
        }
    }

    public static void computePath(Vertex source) {
        source.setDistance(0);
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            Vertex actualVertex = queue.poll();
            for (Edge edge : actualVertex.getAdjacencyList()) {
                Vertex v = edge.getTargetVertex();
                double d = actualVertex.getDistance() + edge.getWeight();
                if (d < v.getDistance()) {
                    v.setDistance(d);
                    v.setParent(actualVertex);
                    queue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex targetVertex) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getParent()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Vertex v0 = new Vertex("A");
        Vertex v1 = new Vertex("B");
        Vertex v2 = new Vertex("C");
        Vertex v3 = new Vertex("D");
        Vertex v4 = new Vertex("E");
        Vertex v5 = new Vertex("F");
        Vertex v6 = new Vertex("G");
        Vertex v7 = new Vertex("H");

        v0.addNeighbor(new Edge(5, v0, v1));
        v0.addNeighbor(new Edge(9, v0, v4));
        v0.addNeighbor(new Edge(8, v0, v7));

        v1.addNeighbor(new Edge(12, v1, v2));
        v1.addNeighbor(new Edge(15, v1, v3));
        v1.addNeighbor(new Edge(4, v1, v7));

        v2.addNeighbor(new Edge(3, v2, v3));
        v2.addNeighbor(new Edge(11, v2, v6));

        v3.addNeighbor(new Edge(9, v3, v6));

        v4.addNeighbor(new Edge(4, v4, v5));
        v4.addNeighbor(new Edge(20, v4, v6));
        v4.addNeighbor(new Edge(5, v4, v7));

        v5.addNeighbor(new Edge(1, v5, v2));
        v5.addNeighbor(new Edge(13, v5, v7));

        v7.addNeighbor(new Edge(7, v7, v2));
        v7.addNeighbor(new Edge(6, v7, v5));

        computePath(v0);
        System.out.println(getShortestPathTo(v6));
        System.out.println(getShortestPathTo(v3));
    }
}
