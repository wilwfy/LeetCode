/**
 * My Iteration solution
 *
 * Time: O(2^d). But O(n) in worst case.
 * Space: O(2^d)
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        int level = 1;
        while (!qu.isEmpty()) {
            int size = qu.size();
            while (size > 0) {
                TreeNode node = qu.poll();
                if (level == d - 1) {
                    TreeNode newLeft = new TreeNode(v);
                    TreeNode newRight = new TreeNode(v);
                    newLeft.left = node.left;
                    newRight.right = node.right;
                    node.left = newLeft;
                    node.right = newRight;
                } else {
                    if (node.left != null) qu.offer(node.left);
                    if (node.right != null) qu.offer(node.right);
                }
                size--;
            }
            if (level == d - 1) break;
            level++;
        }
        return root;
    }
}


/**
 * Other's Iteration (BFS) solution
 *
 * Time: O(2^d). But O(n) in worst case.
 * Space: O(2^d)
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newroot = new TreeNode(v);
            newroot.left = root;
            return newroot;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < d-2; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode t = queue.poll();
                if (t.left != null) queue.add(t.left);
                if (t.right != null) queue.add(t.right);
            }
        }
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            TreeNode tmp = t.left;
            t.left = new TreeNode(v);
            t.left.left = tmp;
            tmp = t.right;
            t.right = new TreeNode(v);
            t.right.right = tmp;
        }
        return root;
    }
}


/**
 * Other's DFS solution with helper function
 *
 * Time: O(2^d). But O(n) in worst case.
 * Space: O(d).
 */
class Solution {
    private void dfs(TreeNode root, int depth, int v, int d) {
        if (root == null) return;
        if (depth < d-1) {
            dfs(root.left, depth+1, v, d);
            dfs(root.right, depth+1,v, d);
        } else {
            TreeNode tmp = root.left;
            root.left = new TreeNode(v);
            root.left.left = tmp;
            tmp = root.right;
            root.right = new TreeNode(v);
            root.right.right = tmp;
        }
    }
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newroot = new TreeNode(v);
            newroot.left = root;
            return newroot;
        }
        dfs(root, 1, v, d);
        return root;
    }
}


/**
 * Other's DFS solution without helper function
 *
 * Time: O(2^d). But O(n) in worst case.
 * Space: O(d).
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d < 2) {
            TreeNode newroot = new TreeNode(v);
            // to use 1 to indicate attach to left node as required, we can also use 0 to indicate attach to right node
            if (d == 1) newroot.left = root;
            else newroot.right = root;
            return newroot;
        }
        if (root == null) return null;
        root.left = addOneRow(root.left, v, d == 2 ? 1 : d-1);
        root.right = addOneRow(root.right, v, d == 2 ? 0 : d-1);
        return root;
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
