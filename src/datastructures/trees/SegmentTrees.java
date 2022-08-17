package datastructures.trees;

import java.util.Arrays;

/*
    1. It is used for query optimization and updates - logN
    Example - given array of length n. We have given m queries to find sum between given index range [l, r]
    We cna do this efficiently using prefix sum
    https://www.geeksforgeeks.org/range-sum-queries-without-updates/

    Find minimum between given index range [l, r]


    2   1   5   9   3   4   2   8

    - Every node of segment tree represents a range in array
    - Leaf nodes cover single array element - [0,0],[1,1],[2,2],[3,3],[4,4],[5,5],[6,6],[7,7]
    - For even no of elements, its binary tree
    - For odd no of elements, extend it to next integer where it make perfect power of 2 i.e 8 is 2^3
        If we have 9 elements then we'll create segment tree over array of length 2^4 = 16, extra indexes 9-15 filled with 0


                                [0,7]

                [0,3]                           [4,7]
         [0,1]          [2,3]           [4,5]           [6,7]
    [0,0]   [1,1]   [2,2]   [3,3]   [4,4]   [5,5]   [6,6]   [7,7]

    How to build it?
    1. Start from the leaf node
    2. Divide and Conquer

    We can build segment tree based on query type e.g. to get minimum in given range, to get summation of values in given range.
    Each node's value depends on query type e.g. minimum, sum of values in given range

    How to query it?
    1. Query range - find sum of [l,r], find sum of values between index ranges [2, 5]

                                                    34[0,7]
                                              /             \
                                             17[0,3]         17[4,7]
                                       /        \         /     \
                                      3[0,1]    14[2,3]  7[4,5] 10[6,7]
                                    /   \     /    \    /  \   /  \
                                    2   1     5     9   3   4  2   8
                                [0,0] [1,1] [2,2] [3,3] [4,4] [5,5] [6,6] [7,7]

           Start with root node and check if [0,7] completely overlaps with [2,5]?
           - No (partial overlap) - Go left and go right and find answer
           - No overlap - return 0
           - Yes - return the node value

           Update
           With update operation, we need to update leaf nodes and update ancestors while coming back from recursion

           Segment Tree only requires a linear number of vertices. The first level of the tree contains a single node (the root), the
           second level will contain two vertices, in the third it will contain four vertices, until the number of vertices reaches n.
           Thus the number of vertices in the worst case can be estimated by the sum  1+2+4+...+2^logn<2^logn+1<4n.
           It is worth noting that whenever  is not a power of two, not all levels of the Segment Tree will be completely filled.
           We can see that behavior in the image. For now we can forget about this fact, but it will become important later during the
           implementation.
           The height of the Segment Tree is O(logn), because when going down from the root to the leaves the size of the segments decreases
           approximately by half.

           Properties
           left child = 2*parent + 1
           right child = 2*parent + 2

           https://maratona.ic.unicamp.br/MaratonaVerao2016/material/segment_tree_lecture.pdf
           https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/

 */

// Segment tree of summation
public class SegmentTrees {

    private int n;
    private int[] st;

    public SegmentTrees(int n) {
        this.n = n;
        this.st = new int[4 * n];
    }

    public void build(int[] arr) {
        build(0, n - 1, 0, arr);
    }

    private void build(int start, int end, int node, int[] arr) {
        // leaf node base case
        if (start == end) {
            st[node] = arr[end];
            return;
        }
        int mid = (start + end) / 2;

        // left subtree
        build(start, mid, 2 * node + 1, arr);

        // right subtree
        build(mid + 1, end, 2 * node + 2, arr);

        st[node] = st[node * 2 + 1] + st[node * 2 + 2];
    }

    public int query(int l, int r) {
        return query(0, n - 1, l, r, 0);
    }

    public int query(int start, int end, int l, int r, int node) {
        //non overlapping
        if (start > r || end < l)
            return 0;

        //complete overlap
        if (start >= l && end <= r)
            return st[node];

        //partial
        int mid = (start + end) / 2;

        int q1 = query(start, mid, l, r, 2 * node + 1);
        int q2 = query(mid + 1, end, l, r, 2 * node + 2);

        return q1 + q2;
    }

    public void update(int index, int value) {
        update(0, n - 1, 0, index, value);
    }

    public void update(int start, int end, int node, int index, int value) {
        // base case
        if (start == end) {
            st[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (index <= mid) {
            // left subtree
            update(start, mid, 2 * node + 1, index, value);
        } else {
            // right subtree
            update(mid + 1, end, 2 * node + 2, index, value);
        }
        st[node] = st[node * 2 + 1] + st[node * 2 + 2];
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8};
        SegmentTrees segmentTrees = new SegmentTrees(arr.length);
        segmentTrees.build(arr);
        System.out.println(Arrays.toString(segmentTrees.st));
        System.out.println(segmentTrees.query(0,4));
        segmentTrees.update(4, 10);
        System.out.println(segmentTrees.query(2,6));
        segmentTrees.update(2, 20);
        System.out.println(segmentTrees.query(0,4));
    }
}
