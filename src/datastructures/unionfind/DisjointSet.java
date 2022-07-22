package datastructures.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DisjointSet<E> {

    private final Map<E, Node<E>> nodes;
    private int numComponents;

    public DisjointSet() {
        this.nodes = new HashMap<>();
        this.numComponents = 0;
    }

    public boolean makeSet(E data) {
        Optional<Node<E>> node = getNode(data);
        if (node.isPresent())
            return false;
        this.nodes.put(data, new Node<>(data));
        this.numComponents++;
        return true;
    }

    public void union(E elementA, E elementB) {
        Optional<Node<E>> maybeParentA = find(elementA);
        Optional<Node<E>> maybeParentB = find(elementB);
        if (maybeParentA.isPresent() && maybeParentB.isPresent()) {
            Node<E> parentA = maybeParentA.get();
            Node<E> parentB = maybeParentB.get();
            if(parentA.equals(parentB))
                return;
            int currentSize = parentA.getSize() + parentB.getSize();
            if (parentA.getRank() < parentB.getRank()) {
                parentA.setParent(parentB);
                parentB.setSize(currentSize);
            } else {
                if (parentA.getRank() == parentB.getRank())
                    parentA.increaseRank();
                parentB.setParent(parentA);
                parentA.setSize(currentSize);
            }
            numComponents--;
        }
    }

    public Optional<Node<E>> find(E data) {
        Optional<Node<E>> node = getNode(data);
        if(node.isPresent())
            return Optional.ofNullable(find(node.get()));
        return Optional.empty();
    }

    private Node<E> find(Node<E> node) {
        if(node.equals(node.getParent()))
            return node;
        node.setParent(find(node.getParent()));
        return node.getParent();
    }

    public int getNumComponents() {
        return numComponents;
    }

    private Optional<Node<E>> getNode(E data) {
        return Optional.ofNullable(this.nodes.get(data));
    }
}
