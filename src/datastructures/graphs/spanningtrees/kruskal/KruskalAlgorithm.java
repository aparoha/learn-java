package datastructures.graphs.spanningtrees.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    1. It uses Disjoint Set data structure
    2. It is a data structure to keep track of set of elements partitioned into a number of disjoint subsets
    3. With the help of disjoint set, we can identify if 2 vertices belong to same set or not in O(1) time
    4. We cna find cycles in a graph with this data structure using O(logN) time
    5. Disjoint sets operations - we can represent it as a tree where the root is called representative of set (4 is representative)
                                4
                            10      14
                                128     55
        a. Making sets - set the parent to node itself
            function makeSet(x)
                x.parent = x
        b. Find - recursive
            function find(x)
                if x.parent ==x
                    return x
                else
                    return find(x.parent)
        c. Union - merging 2 disjoint sets by connecting them according to the representatives
            Problem - Tree could be imbalanced
                function union(x, y)
                    x_root = find(x)
                    y_root = find(y)
                    x_root.parent = y_root.parent

        How to improve Union operation? - we can combine both approaches
        1. Union by rank
            Always attach smaller tree to the root of the larger one
        2. Path compression
            We may flatten the structure of the tree. We set every visited node to be connected to the root directly and achieve O(1) time

       Rank parameter

       1. We can represent the sets with the help of tree structures
       2. Every node in the tree can have a rank parameter - height
       3. The rank of the set = rank of representative (root of tree)
       4. Attach the tree with smaller rank to the tree with higher rank

       Path compression

              function find(x)
                if x.parent !=x
                    x.parent = find(x.parent)
                return x.parent


       Spanning tree

       1. A spanning tree of an undirected graph G(V,E) is a subgraph that includes all vertices of G(V,E)
       2. A graph may have several spanning trees
       3. A minimum spanning tree is a spanning tree with total weight <= weight of every other spanning tree

       Kruskal Algorithm O(ElogE), if edges are already sorted so it could be quasi linear

       1. Sort the edges according to their weights - O(ElogE), we can also use heap
       2. Use Disjoint Set to boost running time
       3. Start adding edges to MST and make sure there wont be any cycles in MST O(logN) or O(1) - with path compression
 */
public class KruskalAlgorithm {

    public List<Edge> run(List<Vertex> vertexList, List<Edge> edgeList) {
        DisjointSet disjointSet = new DisjointSet(vertexList);
        List<Edge> mst = new ArrayList<>();
        // sort all edges by weight
        Collections.sort(edgeList);
        for (Edge edge : edgeList) {
            Vertex u = edge.getStartVertex();
            Vertex v = edge.getTargetVertex();
            if (disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
                mst.add(edge);
            }
            disjointSet.union(u.getNode(), v.getNode());
        }
        return mst;
    }
}
