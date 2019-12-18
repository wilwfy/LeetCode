/**
 * Other's solution of BFS
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> qu = new LinkedList<>();
        
        qu.offer(root);
        while (!qu.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            
            int levelNum = qu.size();
            for (int i = 0; i < levelNum; i++) {
                if (qu.peek().left != null) qu.offer(qu.peek().left);
                if (qu.peek().right != null) qu.offer(qu.peek().right);
                levelList.add(qu.poll().val);
            }
            
            res.add(0, levelList);
        }
        
        return res;
    }
}


/**
 * Other's solution of DFS
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackDFS(res, root, 0); // level is 0 by default
        return res;
    }
    
    public void backtrackDFS(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null)  return;
        
        // each element of the list reflexes to a list of
        // number values at a level of the tree
        if (level >= list.size())
            // if we come to a new level then need create a new element list
            // and put it at the head of result
            list.add(0, new ArrayList<Integer>());
        
        if (root.left != null) backtrackDFS(list, root.left, level+1);
        if (root.right != null) backtrackDFS(list, root.right, level+1);

        // the number value list of current level of tree is at
        // index list.size()-1-level of the result
        list.get(list.size()-1-level).add(root.val);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
