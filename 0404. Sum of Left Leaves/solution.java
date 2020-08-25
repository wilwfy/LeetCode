/**
 * My solution of DFS with global variable
 *
 * Time: O(n) in worst case that there are only left leaves in the tree
 * Space: O(height). Space for recursion call.
 */
class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return sum;
    }
    
    private void dfs(TreeNode node, boolean isLeft) {
        if (node == null) return;
        if (node.left == null && node.right == null && isLeft)
            sum += node.val;
        dfs(node.left, true);
        dfs(node.right, false);
    }
}


/**
 * Other's solution of DFS without global variable
 *
 * Time: O(n) in worst case that there are only left leaves in the tree
 * Space: O(height). Space for recursion call.
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }
    
    private int helper(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return helper(root.left, true) + helper(root.right, false);
    }
}


/**
 * Other's solution of DFS without helper function
 *
 * Time: O(n) in worst case that there are only left leaves in the tree
 * Space: O(height). Space for recursion call.
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        if(root.left != null) {
            if(root.left.left == null && root.left.right == null) ans += root.left.val;
            else ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);
        
        return ans;
    }
}


/**
 * Other's BFS solution (Iteration)
 *
 * Time: O(n) in worst case that there are only left leaves in the tree
 * Space: O(height) where height = n, in worst case that there are only left leaves or only right leaves in the tree
 */
 class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.push(node.left);
            }
            if(node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return ans;
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
