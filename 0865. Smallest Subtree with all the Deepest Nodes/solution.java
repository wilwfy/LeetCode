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
