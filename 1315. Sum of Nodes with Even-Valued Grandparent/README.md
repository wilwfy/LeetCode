Medium

Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.

 

**Example 1:**

![1315_example_1](https://github.com/wilwfy/LeetCode/blob/master/1315.%20Sum%20of%20Nodes%20with%20Even-Valued%20Grandparent/1315_example_1.png)
```
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
```

**Constraints:**

- The number of nodes in the tree is between 1 and 10^4.
- The value of nodes is between 1 and 100.

**Hint 1**  
Traverse the tree keeping the parent and the grandparent.

**Hint 2**  
If the grandparent of the current node is even-valued, add the value of this node to the answer.
