/**
 * Other's solution of Recursion with In-order-traverse
 *
 * Explanation
 * We need to find the first and second elements that are not in order right?
 * 
 * How do we find these two elements? For example, we have the following tree
 * that is printed as in order traversal:
 * 
 * 6, 3, 4, 5, 2
 * 
 * We compare each node with its next one and we can find out that 6 is the first
 * element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.
 * 
 * Really, what we are comparing is the current node and its previous node in the
 * "in order traversal".
 * 
 * Let us define three variables, firstElement, secondElement, and prevElement. Now
 * we just need to build the "do some business" logic as finding the two elements.
 *
 * Time: O(N)
 * Space: O(Height)
 */
class Solution {
    TreeNode firstElement;
    TreeNode secondElement;
    TreeNode prevElement;
    
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        
        firstElement = null;
        secondElement = null;
        prevElement = null;
        
        // In order traversal to find the two elements
        inorderTraverse(root);
        
        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    
    private void inorderTraverse(TreeNode cur) {
        if (cur == null) return;
        
        inorderTraverse(cur.left);
        
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && (prevElement == null || prevElement.val >= cur.val)) {
            firstElement = prevElement;
        }
        // If first element is found, assign the second element to the cur (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= cur.val) {
            secondElement = cur;
        }
        
        prevElement = cur;
        
        inorderTraverse(cur.right);
    }
}


/**
 * Other's solution of Morris Traversal
 *
 * Explanation
 *
 * To understand this, you need to first understand Morris Traversal or Morris Threading Traversal.
 * It take use of leaf nodes' right/left pointer to achieve O(1) space Traversal on a Binary Tree.
 * Below is a standard Inorder Morris Traversal, referred from
 *   http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html (a Chinese Blog, while
 *   the graphs are great for illustration)
 *   
 * How to specify the first wrong node and second wrong node.
 * 
 * When they are not consecutive, the first time we meet pre.val > root.val ensure us the first node
 * is the pre node, since root should be traversal ahead of pre, pre should be at least at small as
 * root. The second time we meet pre.val > root.val ensure us the second node is the root node, since
 * we are now looking for a node to replace with out first node, which is found before.
 * 
 * When they are consecutive, which means the case pre.val > cur.val will appear only once. We need
 * to take case this case without destroy the previous analysis. So the first node will still be pre,
 * and the second will be just set to root. Once we meet this case again, the first node will not be
 * affected.
 *
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
        while (root != null) {
            if (root.left != null) {
                // connect threading for root
                temp = root.left;
                while (temp.right != null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if (temp.right != null) {
                    if (pre != null && pre.val > root.val) {
                        if (first == null) first = pre;
                        second = root;
                    }
                    pre = root;
                    temp.right = null;
                    root = root.right;
                } else {
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            } else {
                if (pre != null && pre.val > root.val) {
                    if (first == null) first = pre;
                    second = root;
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if (first != null && second != null) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
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
