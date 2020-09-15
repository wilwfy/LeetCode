/**
 * My solution of Recursion
 */
class Solution {
    int toDelCnt;
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        
        Set<Integer> delSet = new HashSet<>();
        for (int num: to_delete)
            delSet.add(num);
        toDelCnt = delSet.size();
        if (dfs(root, delSet, toDelCnt, res) != null) res.add(root);
        return res;
    }
    
    private TreeNode dfs(TreeNode node, Set<Integer> delSet, int toDelCnt, List<TreeNode> res) {
        if (node == null || toDelCnt == 0) return node;
        if (delSet.contains(node.val)) {
            toDelCnt--;
        }
        node.left = dfs(node.left, delSet, toDelCnt, res);
        node.right = dfs(node.right, delSet, toDelCnt, res);
        if (delSet.contains(node.val)) {
            if (node.left != null) res.add(node.left);
            if (node.right != null) res.add(node.right);
        }
        return delSet.contains(node.val) ? null : node;
    }
}


/**
 * Other's solution of Recursion
 *
 * Intuition
 * As I keep saying in my video "courses",
 * solve tree problem with recursion first.
 * 
 * 
 * Explanation
 * If a node is root (has no parent) and isn't deleted,
 * when will we add it to the result.
 * 
 * 
 * Complexity
 * Time: O(N)
 * Space: O(H + N), where H is the height of tree.
 */
class Solution {
        Set<Integer> to_delete_set;
        List<TreeNode> res;
    
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            to_delete_set = new HashSet<>();
            res = new ArrayList<>();
            for (int i : to_delete)
                to_delete_set.add(i);
            helper(root, true);
            return res;
        }
	    
        private TreeNode helper(TreeNode node, boolean is_root) {
            if (node == null) return null;
            boolean deleted = to_delete_set.contains(node.val);
            if (is_root && !deleted) res.add(node);
            node.left = helper(node.left, deleted);
            node.right =  helper(node.right, deleted);
            return deleted ? null : node;
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
