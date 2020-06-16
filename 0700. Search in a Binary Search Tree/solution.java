/**
 * My solution of BFS
 *
 * Time: O(N) in worst case.
 * Space: O(N).
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                if (cur.val == val) return cur;
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return null;
    }
}


/**
 * My solution of DFS
 *
 * Time: O(N) in worst case.
 * Space: O(h) where h is maximum height of Binary Tree.
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        
        TreeNode res = searchBST(root.left, val);
        if (res != null) return res;
        res = searchBST(root.right, val);
        if (res != null) return res;
        return null;
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
