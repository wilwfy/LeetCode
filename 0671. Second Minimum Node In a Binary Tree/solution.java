/**
 * Official solution of DFS with HashSet
 *
 * Time Complexity: O(N), where N is the total number of nodes in the given tree. We visit each node exactly once, and scan through
 *                  the O(N) values in unique once.
 * Space Complexity: O(N), the information stored in uniques.
 */
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        int min1 = root.val;
        // The value could be greater than Integer.MAX_VALUE
        long min2 = Long.MAX_VALUE;
        for (int val: set) {
            if (min1 < val && val < min2) min2 = val;
        }
        return min2 < Long.MAX_VALUE ? (int) min2 : -1;
    }
    
    public void dfs(TreeNode node, Set<Integer> uniques) {
        if (node != null) {
            uniques.add(node.val);
            dfs(node.left, uniques);
            dfs(node.right, uniques);
        }
    }
}


/**
 * Official solution of Ad-Hoc DFS
 *
 * Intuition and Algorithm
 * Let min1 = root.val. When traversing the tree at some node, node, node.val > min1, we know all values in the subtree at node are at
 * least node.val. Thus, we do not need to search this subtree.
 * Also, as we only care about the second minimum min2, we do not need to record any values that are larger than our current candidate
 * for the second minimum.
 *
 * Time Complexity: O(N), where N is the total number of nodes in the given tree. We visit each node at most once.
 * Space Complexity: O(N). The information stored in min2 and min1 is O(1), but our depth-first search may store up to O(h)=O(N) information
 *                   in the call stack, where h is the height of the.
 */
class Solution {
    int min1;
    // The value could be greater than Integer.MAX_VALUE
    long min2 = Long.MAX_VALUE;
    
    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return min2 < Long.MAX_VALUE ? (int) min2 : -1;
    }
    
    public void dfs(TreeNode node) {
        if (node != null) {
            if (min1 < node.val && node.val < min2) {
                // the subtree values of current node will be greater, so
                // no need to go down further to check those values when
                // current node value is greater than min1
                min2 = node.val;
            } else if (min1 == node.val) {
                dfs(node.left);
                dfs(node.right);
            }
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
