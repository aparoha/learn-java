package datastructures.graphs.dag;

/*

                 What is longest path problem?

         1. It is NP hard problem
         2. If G(V, E) is DAG then we can solve it in linear running time
         3. Scheduling algorithms rely heavily on longest paths. It can solve parallel job scheduling problem
         4. Transform the longest path problem to shortest path problem?
            - Negate the edge weights - multiply them by -1 and run the shortest path algorithm
            - Because edge weight is negative now so we need to use bellman ford algorithm
 */
public class LongestPathDAG {
}
