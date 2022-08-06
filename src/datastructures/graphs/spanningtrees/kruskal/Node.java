package datastructures.graphs.spanningtrees.kruskal;

public class Node {
    //count of nodes below current node
    private int height;
    private Node parent;

    public Node(int height, Node parentNode) {
        this.height = height;
        this.parent = parentNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
