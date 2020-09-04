/**
 * My DFS solution
 *
 * Time: O(n)
 * Space: O(height)
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    
    private void dfs(TreeNode node, int dep, List<Integer> res) {
        if (node == null) return;
        if (dep == res.size()) { // The first non-null node at current depth
            res.add(node.val);
        } else {
            res.set(dep, Math.max(node.val, res.get(dep)));
        }
        dfs(node.left, dep + 1, res);
        dfs(node.right, dep + 1, res);
    }
}


/**
 * My BFS solution
 *
 * Time: O(n)
 * Space: O(2^height)
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int len = q.size();
            while (len-- > 0) {
                TreeNode cur = q.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(max);
        }
        return res;
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
