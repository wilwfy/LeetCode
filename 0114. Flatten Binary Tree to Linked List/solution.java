/**
 * My Solution of Recursion
 */
class Solution {
    public void flatten(TreeNode root) {
        dfs(root);
        return;
    }
    
    private TreeNode dfs(TreeNode node) {
        if (node == null) return node;
        if (node.left == null && node.right == null) return node;
        if (node.left == null) return dfs(node.right);
        TreeNode leafOfLeftChild = dfs(node.left);
        leafOfLeftChild.right = node.right;
        
        TreeNode leafOfRightChild = dfs(node.right);
        
        node.right = node.left;
        node.left = null;
        
        return leafOfRightChild == null ? leafOfLeftChild : leafOfRightChild;
    }
}


/**
 * Other's Solution of Recursion
 *
 * The process is shown in below pictures:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * -----------        
 * pre = 5
 * cur = 4
 * 
 *     1
 *    / \
 *   2   \
 *  / \  |
 * 3   4 |
 *      \|
 *       5
 *        \
 *         6
 * -----------        
 * pre = 4
 * cur = 3
 * 
 *     1
 *    / \
 *   2  |
 *  /|  | 
 * 3 |  |
 *  \|  |
 *   4  |
 *    \ |
 *     5
 *      \
 *       6
 * -----------        
 * cur = 2
 * pre = 3
 * 
 *     1
 *    / \
 *   2   \
 *    \   \
 *     3   \
 *      \  |
 *       4 |
 *        \|
 *         5
 *          \
 *           6
 * -----------        
 * cur = 1
 * pre = 2
 * 
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
class Solution {
    
    //version that uses the global variable
    // private TreeNode prev = null;
    // public void flatten(TreeNode root) {
    //     if (root == null)
    //         return;
    //     flatten(root.right);
    //     flatten(root.left);
    //     root.right = prev;
    //     root.left = null;
    //     prev = root;
    // }
    
    
    //version that does not use the global variable
    public void flatten(TreeNode root) {
        root = helper(root, null);
    }
    // helper function takes in the previous head, do the flattening and returns the head of the flatten binary tree
    private TreeNode helper(TreeNode root, TreeNode prev) {
        if (root == null) {
            return prev;
        }
        prev = helper(root.right, prev);
        prev = helper(root.left, prev);
        root.right = prev;
        root.left = null;
        prev = root;
        return root;
    }
}


/**
 * Other's Solution of Iteration
 *
 * Time: O(n), here's why:
 *       We're moving cur over all nodes and for each one potentially dive down deep into its left subtree, so one might think it's more than O(n) time.
 *       But... a long path down the left subtree immediately pays off, as you then insert that entire path into the "right border" of the whole tree,
 *       where cur will walk over it once more but prev will never have to walk over it again.
 *       To put it differently: Every node is visited by cur exactly once and by prev at most once, and the runtime is proportional to the number of steps
 *       taken by cur and prev, so O(n) overall.
 * Space: O(1).
 */
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode prev = cur.left;
                while (prev.right != null) prev = prev.right;
                prev.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
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
