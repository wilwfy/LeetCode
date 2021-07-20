/**
 * My solution of DFS
 */
class Solution {
    TreeNode LCA = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //LCA = root;
        dfs(root, p, q);
        return LCA;
    }
    
    private TreeNode dfs(TreeNode cur, TreeNode p, TreeNode q) {
        if (LCA != null) return null;
        if (cur == null) return cur;
        TreeNode leftResult = dfs(cur.left, p, q);
        TreeNode rightResult = dfs(cur.right, p, q);
        if (leftResult != null && rightResult != null) LCA = cur;
        if (leftResult == null && rightResult == null) {
            if (cur.val != p.val && cur.val != q.val) cur = null;
        } else {
            if (cur.val == p.val || cur.val == q.val) LCA = cur;
        }
        return cur;
    }
}


/**
 * Official solution of Recursive Approach
 *
 * Intuition
 * Lowest common ancestor for two nodes p and q would be the last ancestor node common to both of them.
 * Here last is defined in terms of the depth of the node. The below diagram would help in understanding
 * what lowest means.
 * 
 * Algorithm
 * 1. Start traversing the tree from the root node.
 * 2. If both the nodes p and q are in the right subtree, then continue the search with right subtree starting step 1.
 * 3. If both the nodes p and q are in the left subtree, then continue the search with left subtree starting step 1.
 * 4. If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's
 *    subtrees. and hence we return this common node as the LCA.
 * 
 * Time Complexity: O(N), where N is the number of nodes in the BST. In the worst case we might be visiting all the
 *                  nodes of the BST.
 * Space Complexity: O(N). This is because the maximum amount of space utilized by the recursion stack would be N since
 *                   the height of a skewed BST could be N.
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }
}


/**
 * Official solution of Iterative Approach
 *
 * Algorithm
 * The steps taken are also similar to approach 1. The only difference is instead of recursively calling the
 * function, we traverse down the tree iteratively. This is possible without using a stack or recursion since
 * we don't need to backtrace to find the LCA node. In essence of it the problem is iterative, it just wants
 * us to find the split point. The point from where p and q won't be part of the same subtree or when one is
 * the parent of the other.
 * 
 * Time Complexity : O(N), where N is the number of nodes in the BST. In the worst case we might be visiting
 *                   all the nodes of the BST.
 * Space Complexity : O(1).
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {

            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
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
