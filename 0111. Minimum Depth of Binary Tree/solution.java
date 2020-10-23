/**
 * My solution of BFS
 *
 * Time: O(n). Worst case.
 * Space: O(2^Height).
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        int depth = 0;
        while (!qu.isEmpty()) {
            depth++;
            int size = qu.size();
            while (size-- > 0) {
                TreeNode cur = qu.poll();
                if (cur.left != null) qu.offer(cur.left);
                if (cur.right != null) qu.offer(cur.right);
                if (cur.left == null && cur.right == null) return depth;
            }
        }
        return -1;
    }
}


/**
 * Other's solution of DFS
 *
 * Time: O(n)
 * Space: O(Height)
 */
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
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
