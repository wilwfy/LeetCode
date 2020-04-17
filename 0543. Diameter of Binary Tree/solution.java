/*
 * Official solution of Depth-First Search
 *
 * Intuition:
 * Any path can be written as two arrows (in different directions) from some node, where an arrow is a path that starts at some
 * node and only travels down to child nodes.
 * If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
 *
 * Algorithm:
 * Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1. While we do, a path
 * "through" this node uses 1 + (depth of node.left) + (depth of node.right) nodes. Let's search each node and remember the highest
 * number of nodes used in some path. The desired length is this number minus 1.
 *
 * Time Complexity: O(N). We visit every node once.
 * Space Complexity: O(N), the size of our implicit call stack during our depth-first search.
 */
class Solution {
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        res = 1;
        dfsHelper(root);
        return res - 1;
    }
    
    public int dfsHelper(TreeNode node) {
        if (node == null) return 0;
        int L = dfsHelper(node.left);
        int R = dfsHelper(node.right);
        res = Math.max(res, L+R+1);
        return Math.max(L, R) + 1;
    }
}


/*
 * Other's solution of Depth-First Search
 */
public class Solution {
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1;
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
