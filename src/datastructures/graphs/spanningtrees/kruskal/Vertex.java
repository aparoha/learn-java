package datastructures.graphs.spanningtrees.kruskal;

public class Vertex {

    public Vertex(String name) {
        this.name = name;
    }

    private String name;
    // Node in the disjoint set
    private Node node;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return name;
    }
}
