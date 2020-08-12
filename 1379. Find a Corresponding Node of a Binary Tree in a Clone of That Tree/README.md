Medium

Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a **copy of** the original tree.

Return a reference to the same node in the cloned tree.

**Note** that you are **not allowed** to change any of the two trees or the target node and the answer **must be** a reference to a node in the cloned tree.

**Follow up:** Solve the problem if repeated values on the tree are allowed.

 

**Example 1:**

![1379_example_1](https://github.com/wilwfy/LeetCode/blob/master/1379.%20Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/1379_example_1.png)
```
Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree.
The answer is the yellow node from the cloned tree.
```
**Example 2:**

![1379_example_2](https://github.com/wilwfy/LeetCode/blob/master/1379.%20Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/1379_example_2.png)
```
Input: tree = [7], target =  7
Output: 7
```
**Example 3:**

![1379_example_3](https://github.com/wilwfy/LeetCode/blob/master/1379.%20Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/1379_example_3.png)
```
Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
Output: 4
```
**Example 4:**

![1379_example_4](https://github.com/wilwfy/LeetCode/blob/master/1379.%20Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/1379_example_4.png)
```
Input: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
Output: 5
```
**Example 5:**

![1379_example_5](https://github.com/wilwfy/LeetCode/blob/master/1379.%20Find%20a%20Corresponding%20Node%20of%20a%20Binary%20Tree%20in%20a%20Clone%20of%20That%20Tree/1379_example_5.png)
```
Input: tree = [1,2,null,3], target = 2
Output: 2
```

**Constraints:**

- The number of nodes in the tree is in the range [1, 10^4].
- The values of the nodes of the tree are unique.
- target node is a node from the original tree and is not null.
