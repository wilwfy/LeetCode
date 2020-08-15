/**
 * Other's solution of Post Order DFS
 *
 * Algorithm: (shown in the picture below)
 *     https://github.com/wilwfy/LeetCode/blob/master/0979.%20Distribute%20Coins%20in%20Binary%20Tree/979_solution_diagram.png
 *
 * We traverse childs first (post-order traversal), and return the ballance of coins. For example, if we get '+3' from the left child, that means that the left
 * subtree has 3 extra coins to move out. If we get '-1' from the right child, we need to move 1 coin in. So, we increase the number of moves by 4 (3 moves out
 * left + 1 moves in right). We then return the final ballance: r->val (coins in the root) + 3 (left) + (-1) (right) - 1 (keep one coin for the root).
 */
class Solution {
    int moves;
    public int distributeCoins(TreeNode root) {
        moves = 0;
        dfs(root);
        return moves;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int extraCoinLeft = dfs(node.left);
        int extraCoinRight = dfs(node.right);
        moves += Math.abs(extraCoinLeft) + Math.abs(extraCoinRight);
        return node.val + extraCoinLeft + extraCoinRight - 1;
    }
}


/**
 * Other's solution of Post Order DFS without global variable and helper.
 */
class Solution {
    public int distributeCoins(TreeNode root) {
        int res = 0;
        if (root.left != null) {
            res += distributeCoins(root.left);
            root.val += root.left.val - 1;
            res += Math.abs(root.left.val - 1);
        }
        if (root.right != null) {
            res += distributeCoins(root.right);
            root.val += root.right.val - 1;
            res += Math.abs(root.right.val - 1);
        }
        return res;
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
