/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;      
        return helper(root, null, null);
    }
    
    public boolean helper(TreeNode cur, Integer low_limit, Integer up_limit) {
        boolean valid = true;
        if (cur == null) return true;
        if ((low_limit != null) && (low_limit >= cur.val)) return false;
        if ((up_limit != null) && (up_limit <= cur.val)) return false;
        
        if (cur.left != null)
            valid = valid && (cur.left.val < cur.val) && helper(cur.left, low_limit, cur.val);
        if (cur.right != null)
            valid = valid && (cur.right.val > cur.val) && helper(cur.right, cur.val, up_limit);
        return valid;
    }
}
