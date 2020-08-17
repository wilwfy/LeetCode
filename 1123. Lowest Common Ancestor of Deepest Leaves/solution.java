/**
 * Other's solution of One Pass DFS
 *
 * Algorithm
 * Get Subtree Height and LCA:
 * helper function return the subtree height and lca and at the same time.
 * null node will return depth 0,
 * leaves will return depth 1.
 *
 * Time: O(N) for one pass
 * Space O(H) for recursion management
 */
class Solution {
    class Pair {
        TreeNode node;
        int depth;
        public Pair(TreeNode node, int d) {
            this.node = node;
            this.depth = d;
        }
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair p = getLcaDFS(root, 0);
        return p.node;
    }
    
    private Pair getLcaDFS(TreeNode node, int depth) {
        if (node == null) return new Pair(null, depth);
        Pair pairL = getLcaDFS(node.left, depth + 1);
        Pair pairR = getLcaDFS(node.right, depth + 1);
        if (pairL.depth == pairR.depth)
            return new Pair(node, pairL.depth);
        else
            return pairL.depth > pairR.depth ? pairL : pairR;
    }
}


/**
 * Another's solution of One Pass DFS
 *
 * Algorithm
 * Get Subtree Deepest Depth
 * helper function return the deepest depth of descendants.
 * deepest represent the deepest depth.
 * We use a global variable lca to represent the result.
 *
 * Time: O(N) for one pass
 * Space O(H) for recursion management
 */
class Solution {
    int deepest = 0;
    TreeNode lca;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return lca;
    }
    
    private int dfs(TreeNode node, int depth) {
        deepest = Math.max(deepest, depth);
        if (node == null) return depth;
        
        int depthL = dfs(node.left, depth + 1);
        int depthR = dfs(node.right, depth + 1);
        if (depthL == deepest && depthR == deepest)
            lca = node;
        
        return Math.max(depthL, depthR);
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
