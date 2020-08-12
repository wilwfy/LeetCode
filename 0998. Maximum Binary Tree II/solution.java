/**
 * My solution of Recursion
 *
 * Time: O(n)
 * Space: O(n). Recursion space.
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;
        return helper(root, newNode);
    }
    
    public TreeNode helper(TreeNode node, TreeNode newNode) {
        if (node.val < newNode.val) {
            newNode.left = node;
            return newNode;
        }
        if (node.right == null) {
            node.right = newNode;
        } else {
            if (node.right.val < newNode.val) {
                newNode.left = node.right;
                node.right = newNode;
            } else {
                helper(node.right, newNode);
            }
        }
        return node;
    }
}


/**
 * Other's solution of Recursion
 *
 * If root.val > val, recusion on the right.
 * Else, put right subtree on the left of new node TreeNode(val)
 *
 * Time: O(n)
 * Space: O(n). Recursion space.
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root != null && root.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }
}


/**
 * Other's solution of Iteration
 *
 * Search on the right, find the node that cur.val > val > cur.right.val
 * Then create new node TreeNode(val),
 * put old cur.right as node.left,
 * put node as new cur.right.
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val), cur = root;
        if (root == null) return node;
        if (root.val < val) {
            node.left = root;
            return node;
        }
        
        while (cur.right != null && cur.right.val > node.val)
            cur = cur.right;
        
        node.left = cur.right;
        cur.right = node;
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
