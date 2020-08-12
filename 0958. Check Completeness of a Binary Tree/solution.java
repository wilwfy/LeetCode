/**
 * Other's BFS solution of Iteration
 *
 * Use BFS to do a level order traversal,
 * add childrens to the bfs queue,
 * until we met the first empty node.
 * 
 * For a complete binary tree,
 * there should not be any node after we met an empty one.
 *
 * Time: O(n)
 * Space: O(2^H). H is the height of the tree
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> bfsQue = new LinkedList<>();
        bfsQue.offer(root);
        while (bfsQue.peek() != null) {
            TreeNode node = bfsQue.poll();
            bfsQue.offer(node.left);
            bfsQue.offer(node.right);
        }
        while (!bfsQue.isEmpty() && bfsQue.peek() == null)
            bfsQue.poll();
        return bfsQue.isEmpty(); // there should not be any node after we met an empty one.
    }
}


/**
 * Other's BFS solution of Iteration with earlier return
 *
 * We can stop the first while loop when met the first null child.
 * From then on there should not be any more child.
 * This optimisation help reduce half of operations.
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> bfsQue = new LinkedList<>();
        bfsQue.offer(root);
        while (true) {
            TreeNode node = bfsQue.poll();
            if (node.left == null) {
                if (node.right != null)
                    return false;
                break;
            }
            bfsQue.offer(node.left);
            if (node.right == null) break;
            bfsQue.offer(node.right);
        }
        while (!bfsQue.isEmpty()) {
            TreeNode node = bfsQue.poll();
            if (node.left != null || node.right != null)
                return false;
        }
        return true;
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
