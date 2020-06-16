/**
 * Other's solution of Recursion
 *
 * Intuitively, we should be traversing from the children to the parent and calculate the
 * height from bottom. So the null nodes would have height 0. The leaf nodes would have the
 * height 1 and the root would have the max height.
 
 * At each node, we keep a pair<height_of_node_from_bottom, node>. At a given node, if we
 * realize that the leftHeight == rightHeight, it means we have found the deepest subtree
 * rooted at node. If leftHeight > rightHeight, it means the deepest subtree must be rooted
 * at left child. If rightHeight > leftHeight, it means the deepest subtree must be rooted
 * at right child.
 *
 * Which traversal allows us to traverse from bottom-up? Postorder! So we use it in the code.
 *
 * Time: O(N)
 * Space: O(height)
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return height(root).getValue();
    }

    private Pair<Integer, TreeNode> height(TreeNode root) {
        if (root == null) return new Pair(0, null);
        
        Pair<Integer, TreeNode> left = height(root.left);
        Pair<Integer, TreeNode> right = height(root.right);
        
        int leftHeight = left.getKey(), rightHeight = right.getKey();
        if (leftHeight == rightHeight)
            return new Pair(leftHeight+1, root);
        else if (leftHeight > rightHeight)
            return new Pair(leftHeight+1, left.getValue());
        else
            return new Pair(rightHeight+1, right.getValue());
    }
}


/**
 * Official solution of Recursion
 *
 * Intuition
 * 
 * We can combine both depth first searches in Approach #1 into an approach that does both steps in one pass. We will have some function
 * dfs(node) that returns both the answer for this subtree, and the distance from node to the deepest nodes in this subtree.
 * 
 * Algorithm
 * 
 * The Result (on some subtree) returned by our (depth-first search) recursion will have two parts:
 *   Result.node: the largest depth node that is equal to or an ancestor of all the deepest nodes of this subtree.
 *   Result.dist: the number of nodes in the path from the root of this subtree, to the deepest node in this subtree.
 *
 * We can calculate these answers disjointly for dfs(node):
 *   To calculate the Result.node of our answer:
 *     If one childResult has deeper nodes, then childResult.node will be the answer.
 *     If they both have the same depth nodes, then node will be the answer.
 *   The Result.dist of our answer is always 1 more than the largest childResult.dist we have.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree.
 * Space Complexity: O(N).
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    // Return the result of the subtree at this node.
    public Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result L = dfs(node.left),
               R = dfs(node.right);
        if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
        if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
        return new Result(node, L.dist + 1);
    }
}

/**
 * The result of a subtree is:
 *       Result.node: the largest depth node that is equal to or
 *                    an ancestor of all the deepest nodes of this subtree.
 *       Result.dist: the number of nodes in the path from the root
 *                    of this subtree, to the deepest node in this subtree.
 */
class Result {
    TreeNode node;
    int dist;
    Result(TreeNode n, int d) {
        node = n;
        dist = d;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
