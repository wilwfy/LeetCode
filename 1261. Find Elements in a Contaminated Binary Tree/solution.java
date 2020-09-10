/**
 * My solution of DFS without HashSet
 */
class FindElements {
    TreeNode root;

    public FindElements(TreeNode root) {
        this.root = root;
        recoverDFS(root, 0);
    }
    
    public boolean find(int target) {
        return searchDFS(this.root, target);
    }
    
    private void recoverDFS(TreeNode node, int val) {
        if (node == null) return;
        node.val = val;
        recoverDFS(node.left, val * 2 + 1);
        recoverDFS(node.right, val * 2 + 2);
    }
    
    private boolean searchDFS(TreeNode node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        return searchDFS(node.left, target) ? true : searchDFS(node.right, target);
    }
}


/**
 * My solution of DFS with HashSet
 */
class FindElements {
    TreeNode root;
    Set<Integer> nodeSet;

    public FindElements(TreeNode root) {
        nodeSet = new HashSet<>();
        this.root = root;
        recoverDFS(root, 0);
    }
    
    public boolean find(int target) {
        return nodeSet.contains(target);
    }
    
    private void recoverDFS(TreeNode node, int val) {
        if (node == null) return;
        node.val = val;
        nodeSet.add(val);
        recoverDFS(node.left, val * 2 + 1);
        recoverDFS(node.right, val * 2 + 2);
    }
}


/**
 * Other's solution of DFS with HashSet
 */
class FindElements {
    private Set<Integer> seen = new HashSet<>();
    
    public FindElements(TreeNode root) {
        dfs(root, 0);
    }
    private void dfs(TreeNode n, int v) {
        if (n == null) return;
        seen.add(v);
        n.val = v;
        dfs(n.left, 2 * v + 1);
        dfs(n.right, 2 * v + 2);
    }
    
    public boolean find(int target) {
        return seen.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
 
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
