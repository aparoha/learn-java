package datastructures.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    An Euler path that goes through every edge of a graph exactly once. An Euler path starts and ends at different vertices
    An Euler circuit is an Euler path that begins and ends at the same vertex
    Euler tour of tree - flattening a tree. Euler tour tree (ETT) is a method for representing a rooted undirected tree as a number sequence

    3 types of euler tours
    1. Store the node every time you visit it
            For every edge, we store 2 nodes
            SC = 2 * (n - 1) = O(n)
            TC - O(n)

                 1
                / \
               2   5
              / \
             3   4

            1 2 3 2 4 2 1 5 1

    2. Store every node twice
        - when you visit it first time
        - when its whole subtree is explored
        TC, SC - O(n)

                 1
                / \
               2   5
              / \
             3   4

            1 2 3 3 4 4 2 5 5 1

    3. Using Edges

             1
            / \
           2   5
          / \
         3   4

        [1-2] [2-3] [3-2] [2-4] [4-2] [2-1] [1-5] [5-1]


        Concepts of time - All nodes lying in the range of tIn[u] - tOut[u] are the nodes which are in subtree of u
        tIn[5]  = 5
        tOut[5] = 12
        nodes in range 5 - 12 => 5, 10, 6, 7 (subtree of 5)

        leaf noes e.g. 10 -> there is no new node between tIn[10] and tOut[10]

        Whether u is ancestor of v? of whether v lies in subtree of u?
        tIn[u] < tIn[v] && tOut[u] > tOut[v]

                tIn[u]------------------------------tOut[u]
                        tIn[v]-----------tOut[v]

               1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22
euler 1        1   2   4   4   5   10  10  6   6   7   7   5   2   3   8   9   9   11  11  8   3   1
euler 2        1   2   4   2   5   10  5   6   5   7   5   2   1   3   8   9   8   11  8   3   1
euler 3


Query 1 -> Print sum of all nodes in subtree of x?
Query 2 -> Change the value of node x to y
subtreeSum(3) = 3 + 8+ 9+ 11 = 31

Preprocess tree via euler tour - mark tIn and tOut time and arrange times in array, find subtree of give nodes, sum of time index ranges
How to get sum between array indexes?
1. prefix sum
2. segment tree

Construct segment tree on euler tour to answer range sum queries
                                        1
                                   /            \
                                   2            3
                            /           \           \
                            4           5           8
                                /       |   \     /      \
                                10      6   7     9     11


TL,DR

1. Flattened a tree to an array using euler tour
2. Operations on subtrees are converted to range based operations




 */
public class EulerTours {

    public static void printEulerTourGoExitNode(Map<Integer, List<Integer>> graph, int current, int parent) {
        // Time IN e.g. tIn[1] = 1, tOut[1] = 22
        System.out.println(current);
        for (int child : graph.get(current)) {
            if (child != parent)
                printEulerTourGoExitNode(graph, child, current);
        }
        //Time OUT
        System.out.println(current);
    }

    public static void printEulerTourGoComingBackNode(Map<Integer, List<Integer>> graph, int current, int parent) {
        System.out.println(current);
        for (int child : graph.get(current)) {
            if (child != parent) {
                printEulerTourGoComingBackNode(graph, child, current);
                System.out.println(current);
            }
        }
    }
    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {5, 10},
                {5, 6},
                {5, 7},
                {3, 8},
                {8, 9},
                {8, 11}
        };
        int vertices = 12;
        Map<Integer, List<Integer>> graph = createGraph(edges, vertices);

        // 2*n
        System.out.println("********** First Euler Tour **************");

        printEulerTourGoExitNode(graph, 1, -1);

        // 2*n-1
        System.out.println("********** Second Euler Tour **************");

        printEulerTourGoComingBackNode(graph, 1, -1);
    }

    private static Map<Integer, List<Integer>> createGraph(int[][] edges, int vertexCount) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < vertexCount; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
