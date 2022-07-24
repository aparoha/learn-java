package datastructures.graphs.shortestpath;

/*
        Single Source Shortest Path - Shortest path from one node to all other nodes
        ---------------------------

        1. It will work with negative edge weights but fails with negative cycles
        2. Follows dynamic programming
        3. It says to relax all edges n -1 times where n is no of vertices
        4. What is relaxation?
                If there is an edge between a pair of vertices (u,v) then check
                    distance[v] = min{distance[v], distance[u] + cost(u, v)}
        5. Steps
            1. Take all edges and total vertex count i.e. n
            2. Mark the distances of all vertices as INF
            3. Relax all edges n - 1 times
        6. Time complexity
            O (E * (V -1)) ~ O(V^2)
        7. Why Dijkstra's fail with negative weight edge? -> Because it is a greedy algorithm
                        3     5      -2     1
                    S ---> A ---> B ---> C ---> D



 */
public class SSSPBellmanFord {
}
