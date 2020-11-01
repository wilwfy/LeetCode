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
