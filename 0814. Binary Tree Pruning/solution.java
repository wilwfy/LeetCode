/**
 * My solution of DFS
 */
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root) ? root : null;
    }
    
    private boolean dfs(TreeNode node) {
        if (node == null) return false;
        
        boolean leftHasOne = dfs(node.left);
        if (!leftHasOne) node.left = null;
        boolean rightHasOne = dfs(node.right);
        if (!rightHasOne) node.right = null;
        return (!leftHasOne && !rightHasOne && node.val == 0) ? false : true;
    }
}


/**
 * Official solution of DFS
 *
 * Intuition
 * 
 * Prune children of the tree recursively. The only decisions at each node are whether to prune the left
 * child or the right child.
 * 
 * Algorithm
 * 
 * We'll use a function containsOne(node) that does two things: it tells us whether the subtree at this
 * node contains a 1, and it also prunes all subtrees not containing 1.
 * 
 * If for example, node.left does not contain a one, then we should prune it via node.left = null.
 * 
 * Also, the parent needs to be checked. If for example the tree is a single node 0, the answer is an empty tree.
 *
 * Time Complexity: O(N), where N is the number of nodes in the tree. We process each node once.
 * Space Complexity: O(H), where H is the height of the tree. This represents the size of the implicit
 *                   call stack in our recursion.
 */
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode node) {
        if (node == null) return false;
        boolean a1 = containsOne(node.left);
        boolean a2 = containsOne(node.right);
        if (!a1) node.left = null;
        if (!a2) node.right = null;
        return node.val == 1 || a1 || a2;
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
