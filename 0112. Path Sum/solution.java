/**
 * My solution of DFS
 *
 * Time: O(N) - Worst case, check all elements and still find no path.
 * Space: O(log(n)) - height of the tree is the max space taken up at any point in the stack
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum); // the sum could be negative value
    }
    
    public boolean dfs(TreeNode node, int pathSum, int sum) {
        if (node == null) return false;
        pathSum += node.val;
        if (pathSum - sum == 0) {
            if (node.left == null && node.right == null)
                return true;
        }
        if (dfs(node.left, pathSum, sum) || dfs(node.right, pathSum, sum))
            return true;
        return false;
    }
}


/**
 * Other's solution of DFS
 *
 * Time: O(N) - Worst case, check all elements and still find no path.
 * Space: O(log(n)) - height of the tree is the max space taken up at any point in the stack
 */
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null) return sum == root.val;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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
