/**
 * My solution of DFS
 */
class Solution {
    class Result {
        private boolean hasA, hasB;
        private TreeNode lca;
        public Result(boolean hasA, boolean hasB, TreeNode lca) {
            this.hasA = hasA;
            this.hasB = hasB;
            this.lca = lca;
        }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Result res = dfs(root, p, q);
        return res.lca;
    }
    
    private Result dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return new Result(false, false, null);
        
        if (node.val == p.val || node.val == q.val) // p and q both will exist in the binary tree. {
            return new Result(false, false, node);
        
        Result leftRes = dfs(node.left, p, q);
        Result rightRes = dfs(node.right, p, q);
        if (leftRes.lca != null && rightRes.lca != null)
            return new Result(true, true, node);
        
        if (leftRes.lca != null) return leftRes;
        if (rightRes.lca != null) return rightRes;
        
        return new Result(false, false, null);
    }
}


/**
 * Other's solution of DFS
 */
class Solution {   
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
