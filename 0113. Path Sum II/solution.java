/**
 * My solution of Backtracking
 *
 * In this problem, do not use LinkedList. Because using ArrayList allows O(1) access to the each node, that means removing the last element takes only O(1).
 * If use LinkedList, initially we have traverse the list to the last node then remove it, which takes O(n) time.
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null && node.val == sum) {
            List<Integer> p = new ArrayList<>();
            p.addAll(path);
            res.add(p);
            path.remove(path.size()-1);
            return;
        }
        dfs(node.left, sum - node.val, path, res);
        dfs(node.right, sum - node.val, path, res);
        path.remove(path.size()-1);
    }
}


/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>>ret = new ArrayList<List<Integer>>(); 
        List<Integer> cur = new ArrayList<Integer>(); 
        pathSum(root, sum, cur, ret);
        return ret;
    }
    
    public void pathSum(TreeNode root, int sum, List<Integer>cur, List<List<Integer>>ret){
        if (root == null){
            return; 
        }
        cur.add(root.val);
        if (root.left == null && root.right == null && root.val == sum){
            ret.add(new ArrayList(cur));
        }else{
            pathSum(root.left, sum - root.val, cur, ret);
            pathSum(root.right, sum - root.val, cur, ret);
        }
        cur.remove(cur.size()-1);
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
