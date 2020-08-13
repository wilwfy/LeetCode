/**
 * Official solution of Recursion
 *
 * Intuition
 * If root1 and root2 have the same root value, then we only need to check if their children are equal
 * (up to ordering.)
 * 
 * Algorithm
 * 
 * There are 3 cases:
 * 1. If root1 or root2 is null, then they are equivalent if and only if they are both null.
 * 2. Else, if root1 and root2 have different values, they aren't equivalent.
 * 3. Else, let's check whether the children of root1 are equivalent to the children of root2. There are two
 * different ways to pair these children.
 *
 * Time Complexity: O(min(N_1, N_2)), where N_1, N_2 are the lengths of root1 and root2.
 * Space Complexity: O(min(H_1, H_2)), where H_1, H_2 are the heights of the trees of root1 and root2.
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
            || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}


/**
 * Official solution of Canonical Traversal
 *
 * Intuition
 * Flip each node so that the left child is smaller than the right, and call this the canonical representation.
 * All equivalent trees have exactly one canonical representation.
 * 
 * Algorithm
 * We can use a depth-first search to compare the canonical representation of each tree. If the traversals are
 * the same, the representations are equal.
 * When traversing, we should be careful to encode both when we enter or leave a node.
 *
 * Time Complexity: O(N_1 + N_2), where N_1, N_2 are the lengths of root1 and root2.
 *                  (In Python, this is min(N_1, N_2).)
 * Space Complexity: O(N_1 + N_2). (In Python, this is min(H_1, H_2), where H_1, H_2 are the heights of the trees of
 *                   root1 and root2.)
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
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
