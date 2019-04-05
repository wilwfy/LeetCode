/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// Recursive Solution
class Solution {
    List<Integer> inorder_nodes = new ArrayList<>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return inorder_nodes;
        if (root.left != null) inorderTraversal(root.left);
        inorder_nodes.add(root.val);
        if (root.right != null) inorderTraversal(root.right);
        return inorder_nodes;
    }
}

// Iterating method using Stack
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res_nodes = new ArrayList<>();
        Stack<TreeNode> parent_nodes = new Stack<>();
        TreeNode cur_node = root;
        if (cur_node == null) return res_nodes;
        while ((cur_node != null) || (!parent_nodes.isEmpty())) {
            while (cur_node != null) {
                parent_nodes.push(cur_node);
                cur_node = cur_node.left;
            }
            cur_node = parent_nodes.pop();
            res_nodes.add(cur_node.val);
            cur_node = cur_node.right;
        }      
        return res_nodes;
    }
}
