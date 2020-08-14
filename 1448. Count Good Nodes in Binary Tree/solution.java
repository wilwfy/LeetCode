/**
 * My solution of DFS
 *
 * Time: O(N)
 * Space: O(Height)
 */
class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
    
    private int dfs(TreeNode node, int maxVal) {
        if (node == null) return 0;
        int goodCnt = 0;
        if (node.val >= maxVal) {
            goodCnt++;
            maxVal = node.val;
        }
        goodCnt += dfs(node.left, maxVal) + dfs(node.right, maxVal);
        return goodCnt;
    }
}


/**
 * Other's solution of DFS
 *
 * Time: O(N)
 * Space: O(Height)
 */
 class Solution {
    public int goodNodes(TreeNode root) {
        return goodNodes(root, -10000);
    }

    public int goodNodes(TreeNode root, int ma) {
        if (root == null) return 0;
        int res = root.val >= ma ? 1 : 0;
        res += goodNodes(root.left, Math.max(ma, root.val));
        res += goodNodes(root.right, Math.max(ma, root.val));
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
