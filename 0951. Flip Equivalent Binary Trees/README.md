Medium

For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.

 

**Example 1:**
```
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Flipped Trees Diagram
```
![951_tree_ex](https://github.com/wilwfy/LeetCode/blob/master/0951.%20Flip%20Equivalent%20Binary%20Trees/951_tree_ex.png)


**Note:**

1. Each tree will have at most 100 nodes.
2. Each value in each tree will be a unique integer in the range [0, 99].
