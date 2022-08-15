package companies.grab.indeed;

import java.util.ArrayList;
import java.util.List;

/*
Given an n ary tree every tree edge has a cost， find the least
cost or find the leaf node that the cost of path that from root to leaf is the
least.
 */
public class NAryTreeRootToLeafMinCost {

    private static int minCost = Integer.MAX_VALUE;

//    public List<Integer> findMinCostFromRootToLeaf(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        List<Integer> curr = new ArrayList<>();
//        curr.add(root.val);
//        findMinCostHelper(root, root.val, curr, result);
//        return result;
//    }
//
//    private void findMinCostHelper(TreeNode root, int cost, List<Integer> curr, List<Integer> result) {
//        if (root.children == null || root.children.length == 0) {
//            if (cost < minCost) {
//                minCost = cost;
//                result.clear();
//                result.addAll(curr);
//            }
//            return;
//        }
//        for (TreeNode child : root.children) {
//            curr.add(child.val);
//            findMinCostHelper(child, cost + child.val, curr, result);
//            curr.remove(curr.size() - 1);
//        }
//    }
//
//    static class TreeNode {
//        int val;
//        TreeNode[] children;
//
//        public TreeNode(int val, int n) {
//            this.val = val;
//            this.children = new TreeNode[n];
//        }
//
//    }

    public static List<Edge> getMinPath(Node root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfs(res, temp, root, 0);
        return res;
    }

    private static void dfs(List<Edge> res, List<Edge> temp, Node root, int curCost){
        if (root == null){
            return;
        }
        if (root.edges.size() == 0){
            if (curCost < minCost){
                minCost = curCost;
                res.clear();
                res.addAll(temp);
                return;
            }
        }
        for (Edge e : root.edges){
            Node next = e.node;
            temp.add(e);
            dfs(res, temp, next, curCost + e.cost);
            temp.remove(temp.size() - 1);
        }
    }

    static class Edge{
        private Node node;
        private int cost;
        public Edge(Node n, int cost) {
            this.node = n;
            this.cost = cost;
        }
    }
    static class Node {
        private List<Edge> edges;
        public Node(){
            this.edges = new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        /*
         *       n1
         *   e1 /  \ e3
         *     n2   n3
         * e2 /
         *   n4
         *
         * */
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        Edge e1 = new Edge(n2,1);
        Edge e2 = new Edge(n4,2);
        Edge e3 = new Edge(n3,5);
        n1.edges.add(e1);
        n1.edges.add(e3);
        n2.edges.add(e2);
        List<Edge> res = getMinPath(n1);
        System.out.println("3 = "+res);
    }
}
