Hard

Given a non-empty array of unique positive integers A, consider the following graph:

- There are A.length nodes, labelled A[0] to A[A.length - 1];
- There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.

Return the size of the largest connected component in the graph.

 

**Example 1:**
```
Input: [4,6,15,35]
Output: 4
```
![952_example1](https://github.com/wilwfy/LeetCode/blob/master/0952.%20Largest%20Component%20Size%20by%20Common%20Factor/952_example1.png)

**Example 2:**
```
Input: [20,50,9,63]
Output: 2
```
![952_example2](https://github.com/wilwfy/LeetCode/blob/master/0952.%20Largest%20Component%20Size%20by%20Common%20Factor/952_example2.png)

**Example 3:**
```
Input: [2,3,6,7,4,12,21,39]
Output: 8
```
![952_example3](https://github.com/wilwfy/LeetCode/blob/master/0952.%20Largest%20Component%20Size%20by%20Common%20Factor/952_example3.png)

**Note:**

1. 1 <= A.length <= 20000
2. 1 <= A[i] <= 100000
