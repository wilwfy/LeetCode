/**
 * My DFS solution
 *
 * Time: O(n)
 * Space: O(height)
 */
class Solution {
    int ans, depth;
    public int findBottomLeftValue(TreeNode root) {
        ans = -1;
        depth = 0;
        dfs(root, 1);
        return ans;
    }
    
    private void dfs(TreeNode node, int dep) {
        if (node == null) return;
        
        if (node.left == null && node.right == null && dep > depth) {
            ans = node.val;
            depth = dep;
        }
        dfs(node.left, dep + 1);
        dfs(node.right, dep + 1);
    }
}


/**
 * My BFS solution
 *
 * Time: O(n)
 * Space: O(2^height)
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode left = null;
        q.offer(root);
        while (!q.isEmpty()) {
            int len = q.size();
            left = q.peek();
            while (len-- > 0) {
                TreeNode cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        return left.val;
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
