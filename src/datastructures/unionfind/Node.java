package datastructures.unionfind;

import java.util.Objects;

public class Node<E> {
    private final E element;
    private Node<E> parent;
    private Integer rank;
    private int size;

    public Node(E element) {
        this.element = element;
        this.parent = this;
        this.rank = 0;
        this.size = 1;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getParent() {
        return parent;
    }

    public int getRank() {
        return rank;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void increaseRank() {
        this.rank++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return getElement().equals(node.getElement())
                && getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElement(), getParent());
    }
}
