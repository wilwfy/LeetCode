/**
 * My iterative solution
 *
 * Time complexity : O(N) since each node is visited exactly once.
 * Space complexity : O(log(N)) in the best case of completely balanced tree and O(N) in the worst case of completely unbalanced tree, to keep a deque.
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode pNode = q1.poll();
            TreeNode qNode = q2.poll();
            if (pNode == null && qNode == null) continue;
            if (pNode != null && qNode != null) {
                if (pNode.val != qNode.val) return false;
                q1.offer(pNode.left);
                q1.offer(pNode.right);
                q2.offer(qNode.left);
                q2.offer(qNode.right);
            } else
                return false;
        }
        return true;
    }
}


/**
 * Official recursive solution
 *
 * Time complexity : O(N), where N is a number of nodes in the tree, since one visits each node exactly once.
 * Space complexity : O(log(N)) in the best case of completely balanced tree and O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.
 */
class Solution {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // p and q are both null
    if (p == null && q == null) return true;
    // one of p and q is null
    if (q == null || p == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.right, q.right) &&
            isSameTree(p.left, q.left);
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
