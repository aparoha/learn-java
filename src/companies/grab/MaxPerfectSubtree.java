package companies.grab;

/**
 * Given Binary tree, in it we call a perfect tree as when it has 0 or 2 childern for a node.
 * Question: We need to return the maximum possible perfect subtree size ( i.e count of all nodes in a perfect subtree ) that can formed in a given binary tree by removing some nodes.
 *
 * Example:
 *
 * 		      18
 *        /       \
 *      15         30
 *     /  \        /  \
 *   40    50    100   40
 *  /  \   /
 * 8   7  9
 *
 *  	By removing node 9, we get the maximum possible perfect tree with size  is 9
 *
 * 		      18
 *        /       \
 *      15         30
 *     /  \        /  \
 *   40    50    100   40
 *  /  \
 * 8   7
 *
 * Test it
 *
 *            18
 *        /       \
 *      15         30
 *     /  \          \
 *   40    50         40
 *  /  \   /         /  \
 * 8   7  9         10   15
 *                 / \   / \
 *                7   9  4  6
 *               / \
 *              1   2
 */
public class MaxPerfectSubtree {

    public static int maxPerfectSubtree(TreeNode root) {
        return findMaxPerfectSubtree(root).maxSize;
    }

    private static Result findMaxPerfectSubtree(TreeNode node) {
        Result curr = new Result();
        if (node == null) return curr;

        Result left = findMaxPerfectSubtree(node.left);
        Result right = findMaxPerfectSubtree(node.right);

        curr.size = isPerfect(node) ? 1 + left.size + right.size : 1;
        curr.maxSize = Math.max(curr.size, Math.max(left.maxSize, right.maxSize));
        return curr;
    }

    private static boolean isPerfect(TreeNode node) {
        int children = 0;
        children += node.left != null ? 1 : 0;
        children += node.right != null ? 1 : 0;
        return children != 1;
    }

    private static class Result {
        int maxSize;
        int size;

        @Override
        public String toString() {
            return "Result{" +
                    "maxSize=" + maxSize +
                    ", size=" + size +
                    '}';
        }
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(18);
        root.left = new TreeNode(15);
        root.left.left = new TreeNode(40);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(7);
        root.left.right = new TreeNode(50);
        root.left.right.left = new TreeNode(9);
        root.right = new TreeNode(30);
        root.right.right = new TreeNode(40);
        root.right.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(7);
        root.right.right.left.left.left = new TreeNode(1);
        root.right.right.left.left.right = new TreeNode(2);
        root.right.right.left.right = new TreeNode(9);
        root.right.right.right = new TreeNode(15);
        root.right.right.right.left = new TreeNode(4);
        root.right.right.right.right = new TreeNode(6);
        System.out.println(findMaxPerfectSubtree(root));
    }

}
