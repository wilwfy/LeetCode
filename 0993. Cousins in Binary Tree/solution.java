/**
 * Other's solution of BFS with Queue - FIFO
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		boolean isAexist = false;		
    		boolean isBexist = false;		
    		for (int i = 0; i < size; i++) {
    			TreeNode cur = queue.poll();
                if (cur.val == x) isAexist = true;
                if (cur.val == y) isBexist = true;
    			if (cur.left != null && cur.right != null) { 
    				if (cur.left.val == x && cur.right.val == y) { 
    					return false;
    				}
    				if (cur.left.val == y && cur.right.val == x) { 
    					return false;
    				}
    			}
    			if (cur.left != null) {
    				queue.offer(cur.left);
    			}
    			if (cur.right != null) {
    				queue.offer(cur.right);
    			}
    		}
    		if (isAexist && isBexist)
                return true;
            else if (isAexist || isBexist)
                return false;
    	}
    	return false;
    }
}


/**
 * Other's solution of DFS with recursion
 */
class Solution {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent ? true : false;
    }
    
    //get both the depth and parent for x and y
    public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent) {
        if (root == null) return;
        if (root.val == x) {
            xParent = parent;
            xDepth = depth;
        } else if (root.val == y) {
            yParent = parent;
            yDepth = depth;
        } else {
            getDepthAndParent(root.left, x, y, depth + 1, root);
            getDepthAndParent(root.right, x, y, depth + 1, root);
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
