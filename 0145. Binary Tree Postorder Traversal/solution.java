/**
 * My solution of Recursion
 *
 * Time: O(N)
 * Space: O(N) for result. O(H) for stack used for recursion space.
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderDFS(root, res);
        return res;
    }
    
    private void postorderDFS(TreeNode node, List<Integer> list) {
        if (node == null) return;
        postorderDFS(node.left, list);
        postorderDFS(node.right, list);
        list.add(node.val);
    }
}


/**
 * Other's solution of Iteration
 *
 * Time: O(N)
 * Space: O(N) for result. O(H) for stack used to store the root nodes at each level.
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left;           // Reverse the process of preorder
            }
        }
        return result;
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
