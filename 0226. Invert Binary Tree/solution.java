/**
 * My solution of Recursion
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }
    
    public void helper(TreeNode node) {
        if (node == null) return;
        
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        helper(node.left);
        helper(node.right);
    }
}


/**
 * My solution of Iteration
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        while (qu.peek() != null) {
            TreeNode node = qu.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) qu.offer(node.left);
            if (node.right != null) qu.offer(node.right);
        }
        return root;
    }
}


/**
 * Official recursive solution
 *
 * Time Complexity: O(n). Since each node in the tree is visited only once, the time complexity is O(n), where n is the number of nodes
 *                  in the tree. We cannot do better than that, since at the very least we have to visit each node to invert it.
 * Space Complexity: O(n). Because of recursion, O(h) function calls will be placed on the stack in the worst case, where h is the height
 *                   of the tree. Because h ∈ O(n), the space complexity is O(n).
 */
public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
}


/**
 * Official iterative solution
 *
 * Time Complexity: O(n). Since each node in the tree is visited/added to the queue only once, the time complexity is O(n), where n is the
 *                  number of nodes in the tree..
 * Space Complexity: O(n). Since in the worst case, the queue will contain all nodes in one level of the binary tree. For a full binary
 *                   tree, the leaf level has ⌈n/2⌉=O(n) leaves.
 */
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        TreeNode temp = current.left;
        current.left = current.right;
        current.right = temp;
        if (current.left != null) queue.add(current.left);
        if (current.right != null) queue.add(current.right);
    }
    return root;
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
