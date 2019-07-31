/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;      
        return helper(root, null, null);
    }
    
    public boolean helper(TreeNode cur, Integer low_limit, Integer up_limit) {
        boolean valid = true;
        if (cur == null) return true;
        if ((low_limit != null) && (low_limit >= cur.val)) return false;
        if ((up_limit != null) && (up_limit <= cur.val)) return false;
        
        if (cur.left != null)
            valid = valid && (cur.left.val < cur.val) && helper(cur.left, low_limit, cur.val);
        if (cur.right != null)
            valid = valid && (cur.right.val > cur.val) && helper(cur.right, cur.val, up_limit);
        return valid;
    }
}


/*
 * Official recursive solution
 */
class Solution {
  public boolean helper(TreeNode node, Integer lower, Integer upper) {
    if (node == null) return true;

    int val = node.val;
    if (lower != null && val <= lower) return false;
    if (upper != null && val >= upper) return false;

    if (! helper(node.right, val, upper)) return false;
    if (! helper(node.left, lower, val)) return false;
    return true;
  }

  public boolean isValidBST(TreeNode root) {
    return helper(root, null, null);
  }
}


/**
 * Solution by using Iteration
 */
class Solution {
    private LinkedList<TreeNode> stack = new LinkedList<>();
    private LinkedList<Integer> low = new LinkedList<>(),
                                high = new LinkedList<>();
    
    public boolean isValidBST(TreeNode root) {
        Integer low_limit = null, high_limit = null, val;
        updateStack(root, low_limit, high_limit);
        
        while(!stack.isEmpty()){
            root = stack.poll();
            low_limit = low.poll();
            high_limit = high.poll();
            
            if (root == null) continue;
            val = root.val;
            if ((low_limit != null) && (val <= low_limit)) return false;
            if ((high_limit != null) && (val >= high_limit)) return false;
            updateStack(root.right, val, high_limit);
            updateStack(root.left, low_limit, val);
        }
        return true;
    }
    
    public void updateStack(TreeNode node, Integer low_limit, Integer high_limit){
        stack.add(node);
        low.add(low_limit);
        high.add(high_limit);
    }
}

/**
 * Official Solution: Inorder traversal
 *
 * Time complexity : O(N) since we visit each node exactly once.
 * Space complexity : O(N) since we keep up to the entire tree.
 */
class Solution {
  public boolean isValidBST(TreeNode root) {
    Stack<TreeNode> stack = new Stack();
    double inorder = - Double.MAX_VALUE;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      // If next element in inorder traversal
      // is smaller than the previous one
      // that's not BST.
      if (root.val <= inorder) return false;
      inorder = root.val;
      root = root.right;
    }
    return true;
  }
}
