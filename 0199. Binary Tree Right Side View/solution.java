/**
 * My solution of Recursion
 *
 * Time: O(N). N is the number of nodes in the tree.
 * Space: O(H). Recursion space for stack. H is the height of the tree.
 *        Not considering the O(H) space for result.
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode node, List<Integer> list, int level) {
        if (node == null) return;
        if (list.size() == level)
            list.add(node.val);
        else
            list.set(level, node.val); // override previous stored value at the same level
        dfs(node.left, list, level + 1);
        // The value of right child will override the previous
        // stored value of left child at the same level
        dfs(node.right, list, level + 1); 
    }
}


/**
 * Other's solution of Recursion
 *
 * Algorithm
 * 1. Each depth of the tree only select one node.
 * 2. View depth is current size of result list.
 *
 * Time: O(N). N is the number of nodes in the tree.
 * Space: O(H). Recursion space for stack. H is the height of the tree.
 *        Not considering the O(H) space for result.
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}


/**
 * Other's solution of Iteration
 *
 * Time: O(N). N is the number of nodes in the tree.
 * Space: O(1). Not considering the O(H) space for result.
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        List<Integer> res = new ArrayList();
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            while (size-- > 0){
                TreeNode cur = queue.poll();
                if (size == 0)
                    res.add(cur.val);
                
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
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
