package datastructures.trees;

/*

Cut'em All!

You are given a tree with n vertices numbered 1 to n.
The tree is given as an array of edges where edges[i] = [ui, vi] is a bidirectional edge between vertex ui and vertex vi.
Your task is to determine the maximum possible number of edges that can be removed in such a way that all the remaining connected components will have even size.
Return the maximum number of edges that can be removed to leave all connected components with even size, or −1 if it is impossible to remove edges in order to satisfy this property.

Constraints:

1≤ n ≤10^5
1<= edges.length <= n-1
1<= ui, vi <=n

Example:

Input

n = 4
edges = [
    [2, 4],
    [4, 1],
    [3, 1]
]
Output

1
Explanation

In the example, you can remove the edge between vertices 1 and 4. The graph after that will have two connected components with two vertices in each.
Expected Time Complexity: O(n)
 */
public class _07_CutemAll {
}
