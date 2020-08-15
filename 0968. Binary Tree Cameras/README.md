Hard

Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor **its parent, itself, and its immediate children.**

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

 

**Example 1:**

![968_bst_cameras_01](https://github.com/wilwfy/LeetCode/blob/master/0968.%20Binary%20Tree%20Cameras/968_bst_cameras_01.png)
```
Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
```
**Example 2:**

![968_bst_cameras_02](https://github.com/wilwfy/LeetCode/blob/master/0968.%20Binary%20Tree%20Cameras/968_bst_cameras_02.png)
```
Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree.
The above image shows one of the valid configurations of camera placement.
```
**Note:**

1. The number of nodes in the given tree will be in the range [1, 1000].
2. **Every** node has value 0.
