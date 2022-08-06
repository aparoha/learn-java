package datastructures.graphs.spanningtrees.kruskal;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    public DisjointSet(List<Vertex> vertexList) {
        makeSets(vertexList);
    }

    public Node find(Node node) {
        Node actual = node;
        // find the root node
        while (actual.getParent() != null)
            actual = actual.getParent();
        // path compression
        Node root = actual;
        actual = node;

        while (actual != root) {
            Node temp = actual.getParent();
            actual.setParent(root);
            actual = temp;
        }
        return root;
    }

    public void union(Node node1, Node node2) {
        Node root1 = find(node1);
        Node root2 = find(node2);
        if (root1 == root2)
            return;
        // attach smaller tree to the larger tree
        if (root1.getHeight() < root2.getHeight())
            root1.setParent(root2);
        else if (root2.getHeight() < root1.getHeight())
            root2.setParent(root1);
        else {
            root2.setParent(root1);
            root1.setHeight(root1.getHeight() + 1);
        }
    }

    private void makeSets(List<Vertex> vertexList) {
        for (Vertex vertex : vertexList)
            makeSet(vertex);
    }

    private void makeSet(Vertex vertex) {
        Node node = new Node(0, null);
        vertex.setNode(node);
    }
}
