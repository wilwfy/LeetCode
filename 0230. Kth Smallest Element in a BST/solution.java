/**
 * My solution of Recursion and DFS
 */
class Solution {
    int cnt = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        cnt = k;
        return helper(root);
    }
    
    public int helper(TreeNode node) {
        if (node == null) return -1;
        
        int res = helper(node.left);
        if (res > -1) return res;
        
        cnt--;
        if (cnt == 0) return node.val;
        
        res = helper(node.right);
        if (res > -1) return res;
        else return -1;
    }
}


/**
 * Official solution of Recursion
 *
 * It's a very straightforward approach with O(N) time complexity. The idea is to build an inorder traversal of BST which is an array
 * sorted in the ascending order. Now the answer is the k - 1th element of this array.
 *
 * Time complexity : O(N) to build a traversal.
 * Space complexity : O(N) to keep an inorder traversal.
 */
class Solution {
  public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
    if (root == null) return arr;
    inorder(root.left, arr);
    arr.add(root.val);
    inorder(root.right, arr);
    return arr;
  }

  public int kthSmallest(TreeNode root, int k) {
    ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
    return nums.get(k - 1);
  }
}


/**
 * Official solution of Iteration
 *
 * The above recursion could be converted into iteration, with the help of stack. This way one could speed up the solution because
 * there is no need to build the entire inorder traversal, and one could stop after the kth element.
 *
 * Time complexity : O(H+k), where H is a tree height. This complexity is defined by the stack, which contains at least H + k elements,
 *                   since before starting to pop out one has to go down to a leaf. This results in O(logN+k) for the balanced tree and
 *                   O(N+k) for completely unbalanced tree with all the nodes in the left subtree.
 * Space complexity : O(H+k), the same as for time complexity, O(N+k) in the worst case, and O(logN+k) in the average case.
 */
class Solution {
  public int kthSmallest(TreeNode root, int k) {
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    while (true) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();
      if (--k == 0) return root.val;
      root = root.right;
    }
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
