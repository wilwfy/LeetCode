Medium

Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)

A node is insufficient if **every** such root to leaf path intersecting this node has sum strictly less than limit.

Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.

 

**Example 1:**

![1080_insufficient-1](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-1.png)
```
Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
```
![1080_insufficient-2](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-2.png)
```
Output: [1,2,3,4,null,null,7,8,9,null,14]
```
**Example 2:**

![1080_insufficient-3](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-3.png)
```
Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
```
![1080_insufficient-4](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-4.png)
```
Output: [5,4,8,11,null,17,4,7,null,null,null,5]
```

**Example 3:**

![1080_insufficient-5](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-5.png)
```
Input: root = [1,2,-3,-5,null,4,null], limit = -1
```
![1080_insufficient-6](https://github.com/wilwfy/LeetCode/blob/master/1080.%20Insufficient%20Nodes%20in%20Root%20to%20Leaf%20Paths/1080_insufficient-6.png)
```
Output: [1,null,-3,4]
``` 

**Note:**

1. The given tree will have between 1 and 5000 nodes.
2. -10^5 <= node.val <= 10^5
3. -10^9 <= limit <= 10^9
