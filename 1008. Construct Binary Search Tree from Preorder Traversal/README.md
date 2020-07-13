Medium

Return the root node of a binary **search** tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

**Example 1:**

Input: [8,5,1,7,10,12]  
Output: [8,5,10,1,7,null,12]  
![](https://github.com/wilwfy/LeetCode/blob/master/1008.%20Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal/1008.%20Construct%20Binary%20Search%20Tree%20from%20Preorder%20Traversal.png?raw=true)
 

**Constraints:** 

- 1 <= preorder.length <= 100  
- 1 <= preorder[i] <= 10^8
- The values of preorder are distinct.
