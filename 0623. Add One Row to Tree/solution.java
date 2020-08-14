/**
 * My Iteration solution
 *
 * Time: O(2^d)
 * Space: O(2^d)
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        int level = 1;
        while (!qu.isEmpty()) {
            int size = qu.size();
            while (size > 0) {
                TreeNode node = qu.poll();
                if (level == d - 1) {
                    TreeNode newLeft = new TreeNode(v);
                    TreeNode newRight = new TreeNode(v);
                    newLeft.left = node.left;
                    newRight.right = node.right;
                    node.left = newLeft;
                    node.right = newRight;
                } else {
                    if (node.left != null) qu.offer(node.left);
                    if (node.right != null) qu.offer(node.right);
                }
                size--;
            }
            if (level == d - 1) break;
            level++;
        }
        return root;
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
