Medium

Given a binary search tree, return a **balanced** binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.

 

**Example 1:**

![1382_ex1](https://github.com/wilwfy/LeetCode/blob/master/1382.%20Balance%20a%20Binary%20Search%20Tree/1382_ex1.png)  ![1382_ex1_out](https://github.com/wilwfy/LeetCode/blob/master/1382.%20Balance%20a%20Binary%20Search%20Tree/1382_ex1_out.png)
```
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
```

**Constraints:**

- The number of nodes in the tree is between 1 and 10^4.
- The tree nodes will have distinct values between 1 and 10^5.

**Hint 1**  
Convert the tree to a sorted array using an in-order traversal.

**Hint 2**  
Construct a new balanced tree from the sorted array recursively.
