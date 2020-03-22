# LeetCode
LeetCode solutions.

Always try to optimize time complexity O(n^2) -> O(nlogn) or O(n) in most cases.

Python Tips:
1. Dictionary build-in method keys() will not return a list object. To get a list of keys, need try list(newdict.keys()). This will convert the dict_keys object to a list.
2. Dictionary (Hash Table) should be considered in priority to deal with the duplicated information or the mapping relationship.
3. For Linked List problem, using/creating a dummy head is usually needed.
4. For Linked List problem, the middle element of a list can be found by using a slow and a fast pointers together.

Java Tips:
1. The ASCII values of '`' = 96, 'a' = 97, 'z' = 122, '{' = 123.
   The full ASCII table can be found at: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html

Data Structure Implementation:
1. An implementation of Trie can be found in 720. Longest Word in Dictionary
2. An implementation of Max Heap can be found in 692. Top K Frequent Words


Array Problems:  
1. Using 2 pointers.  
2. Or using Priority Queue (Heap) if some kinds of ordering is required.

Memoization:  
1. For a tree-like data structure, probably need a Hash table such as HashMap<TreeNode, Integer> to store the calculated values for Java implementation.

Sorting:  
1. If the number of data is not big and related to integer, could using Counting Sort for better Time Complexity.
2. TreeMap is a choice when handle sorting of large number data.

Binary Search:
1. Use 'index space' as searching space in 1-dimension data like array
2. Use 'number range space' as searching space in 2-dimension data like matrix

Dynamic Programming:
1. the Math.max() and Math.min() are often used in Java solutions
2. a local extremum (optimal) value is always used
3. a matrix dp[i][j] is used to store result in most cases

Using HashMap to reduce Time Complexity:  
0560. Subarray Sum Equals K


Good discussion and explanation:
1. Approach the problem using the "trial and error" algorithm:  
https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
2. Summary of solutions for problems "reducible" to LeetCode 378:  
https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378


Heuristic Solutions:

0014. Longest Common Prefix  
0053. Maximum Subarray 
0123. Best Time to Buy and Sell Stock III 
0141. Linked List Cycle 
0142. Linked List Cycle II 
0160. Intersection of Two Linked Lists 
0169. Majority Element 
0234. Palindrome Linked List 
0304. Range Sum Query 2D - Immutable
