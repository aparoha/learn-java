package datastructures.graphs.spanningtrees.prims;

import java.util.*;

/*
        1. Use priority queue to get minimum edge weight in every iteration
        2. Vertex based approach while Kruskal is edge based approach
        3. O(ElogE) additional O(E) memory
 */
public class LazyPrimsJarnikAlgorithm {
    // most of the operations have O(1) running time
    private Set<Vertex> unvisited;
    private List<Edge> mst;
    private PriorityQueue<Edge> heap;
    private double fullCost;

    public LazyPrimsJarnikAlgorithm(List<Vertex> vertexList) {
        this.unvisited = new HashSet<>(vertexList);
        this.mst = new ArrayList<>();
        this.heap = new PriorityQueue<>();
    }

    public void run(Vertex vertex) {

        // remove the start vertex (source)
        unvisited.remove(vertex);

        // the algorithm terminates when we visit all the vertices
        while(!unvisited.isEmpty()) {
            // insert all the edges into the heap except for the
            // edges leading to already visited vertices
            for(Edge edge : vertex.getAdjacencyList()) {
                if(unvisited.contains(edge.getTargetVertex())) {
                    heap.add(edge);
                }
            }

            // get the minimum edge from heap in O(logE)
            Edge minEdge = heap.remove();

            // get another edge if the edge is leading to
            // an already visited vertex
            if(!unvisited.contains(minEdge.getTargetVertex()))
                continue;

            mst.add(minEdge);
            fullCost += minEdge.getWeight();

            // in the next iteration we consider the next vertex
            vertex = minEdge.getTargetVertex();
            unvisited.remove(vertex);
        }
    }

    public void printSolution() {
        System.out.println("Cost of MST: " + this.fullCost);

        for(Edge edge : this.mst)
            System.out.println(edge.getStartVertex() + "-" + edge.getTargetVertex());
    }
}
