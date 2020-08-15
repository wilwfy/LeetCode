/**
 * Other's solution of Greedy DFS
 *
 * Intuition:
 * Consider a node in the tree.
 * It can be covered by its parent, itself, its two children.
 * Four options.
 * 
 * Consider the root of the tree.
 * It can be covered by left child, or right child, or itself.
 * Three options.
 * 
 * Consider one leaf of the tree.
 * It can be covered by its parent or by itself.
 * Two options.
 * 
 * If we set a camera at the leaf, the camera can cover the leaf and its parent.
 * If we set a camera at its parent, the camera can cover the leaf, its parent and its sibling.
 * 
 * We can see that the second plan is always better than the first.
 * Now we have only one option, set up camera to all leaves' parent.
 * 
 * Here is our greedy solution:
 *   1. Set cameras on all leaves' parents, thenremove all covered nodes.
 *   2. Repeat step 1 until all nodes are covered.
 *
 * Explanation:
 * Apply a recusion function dfs.
 * Return 0 if it's a leaf.
 * Return 1 if it's a parent of a leaf, with a camera on this node.
 * Return 2 if it's coverd, without a camera on this node.
 * 
 * For each node,
 * if it has a child, which is leaf (node 0), then it needs camera.
 * if it has a child, which is the parent of a leaf (node 1), then it's covered.
 * 
 * If it needs camera, then res++ and we return 1.
 * If it's covered, we return 2.
 * Otherwise, we return 0.
 *
 * Time: O(N)
 * Space: O(Hight)
 */
class Solution {
    int camCnt;
	
    public int minCameraCover(TreeNode root) {
        camCnt = 0;
        // Return 0 if it's a leaf. -- not coverd
        // Return 1 if it's a parent of a leaf, with a camera on this node.
        // Return 2 if it's coverd, without a camera on this node.
        return (dfs(root) == 0 ? 1 : 0) + camCnt;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 2;
        int leftState = dfs(node.left)
		int rightState = dfs(node.right);
        if (leftState == 0 || rightState == 0) {
            camCnt++;
            return 1;
        }
        return (leftState == 1 || rightState == 1) ? 2 : 0;
    }
}


/**
 * Another's solution of Greedy DFS
 *
 * Time: O(N)
 * Space: O(Hight)
 */
class Solution {
    private int NOT_MONITORED = 0;
    private int MONITORED_NOCAM = 1;
    private int MONITORED_WITHCAM = 2;
    private int cameras = 0;
	
    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        int top = dfs(root);
        return cameras + (top == NOT_MONITORED ? 1 : 0);
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return MONITORED_NOCAM;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == MONITORED_NOCAM && right == MONITORED_NOCAM) {
            return NOT_MONITORED;
        } else if (left == NOT_MONITORED || right == NOT_MONITORED) {
            cameras++;
            return MONITORED_WITHCAM;
        } else {
            return MONITORED_NOCAM;
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
