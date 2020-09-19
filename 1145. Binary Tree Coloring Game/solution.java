/**
 * Other's solution of Recursion
 *
 * Intuition
 * The first player colors a node,
 * there are at most 3 nodes connected to this node.
 * Its left, its right and its parent.
 * Take this 3 nodes as the root of 3 subtrees.
 * 
 * The second player just color any one root,
 * and the whole subtree will be his.
 * And this is also all he can take,
 * since he cannot cross the node of the first player.
 * 
 * 
 * Explanation
 * count will recursively count the number of nodes,
 * in the left and in the right.
 * n - left - right will be the number of nodes in the "subtree" of parent.
 * Now we just need to compare max(left, right, parent) and n / 2
 * 
 * 
 * Complexity
 * Time: O(N)
 * Space: O(height) for recursion
 */
class Solution {
    int leftCnt, rightCnt, val;
    
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        val = x;
        countDFS(root);
        return Math.max(Math.max(leftCnt, rightCnt), n - leftCnt - rightCnt - 1) > (n / 2);
    }
    
    private int countDFS(TreeNode node) {
        if (node == null) return 0;
        int l = countDFS(node.left);
        int r = countDFS(node.right);
        if (node.val == val) {
            leftCnt = l;
            rightCnt = r;
        }
        return l + r + 1;
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
