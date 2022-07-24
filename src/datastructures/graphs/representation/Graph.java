package datastructures.graphs.representation;

import java.util.*;

//https://github.com/coding-minutes/graph-algorithms-for-competitive-coding/tree/master/Graphs%20Java/src
//https://github.com/ssid8802/Graph-Coding-Minutes
public class Graph<T> {

    private Map<T, List<Edge<T>>> graph;
    private List<T> vertices;
    private boolean isDirected;

    public Graph(boolean isDirected) {
        this.graph = new HashMap<>();
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, 0);
    }

    public void addEdge(T source, T destination, int weight) {
        if(!hasVertex(source))
            addVertex(source);
        if(!hasVertex(destination))
            addVertex(destination);
        internalAddEdge(source, destination, weight);
        if(!this.isDirected) {
            internalAddEdge(destination, source, weight);
        }
    }

    public List<Edge<T>> getEdges(T vertex) {
        if(hasVertex(vertex))
            return graph.get(vertex);
        else
            return Collections.emptyList();
    }

    private void internalAddEdge(T source, T destination, int weight) {
        Edge<T> reverseEdge = new Edge(destination, source, weight);
        graph.get(reverseEdge.getSource()).add(reverseEdge);
    }

    private boolean hasVertex(T vertex) {
        return graph.containsKey(vertex);
    }

    private void addVertex(T vertex) {
        graph.put(vertex, new ArrayList<>());
        this.vertices.add(vertex);
    }

    public void printGraph(){
        for (Map.Entry<T, List<Edge<T>>> kv : graph.entrySet()) {
            List<Edge<T>> vertexEdges = kv.getValue();
            for(Edge<T> edge : vertexEdges) {
                System.out.println(edge.toString());
            }
        }
    }

    public List<T> getVertices() {
        return vertices;
    }

    class Edge<T> {
        private T source;
        private T destination;
        private int weight;

        Edge(T source, T destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public T getSource() {
            return source;
        }

        public T getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((source == null) ? 0 : source.hashCode());
            result = prime * result + ((destination == null) ? 0 : destination.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge other = (Edge) obj;
            if (source == null) {
                if (other.source != null)
                    return false;
            } else if (!source.equals(other.source))
                return false;
            if (destination == null) {
                if (other.destination != null)
                    return false;
            } else if (!destination.equals(other.destination))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Edge [source=" + source
                    + ", destination=" + destination + ", weight=" + weight + "]";
        }
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph(true);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 4);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 5, 6);
        graph.printGraph();
    }
}
