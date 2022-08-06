package datastructures.graphs.spanningtrees.kruskal;

// We need to sort Edges by weight so implement Comparable
public class Edge implements Comparable<Edge> {

    public Edge(Vertex startVertex, Vertex targetVertex, double weight) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;

    public double getWeight() {
        return weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    @Override
    public String toString() {
        return startVertex + "" + targetVertex;
    }

    @Override
    public int compareTo(Edge otheEdge) {
        return Double.compare(this.weight, otheEdge.getWeight());
    }
}
