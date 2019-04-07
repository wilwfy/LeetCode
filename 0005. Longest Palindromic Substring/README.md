Medium

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"

Output: "bb"

Hint 1: How can we reuse a previously computed palindrome to compute a larger palindrome?
Hint 2: If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
Hint 3: Complexity based hint:
If we use brute-force and check whether for every start and end position a substring is a
palindrome we have O(n^2) start - end pairs and O(n) palindromic checks. Can we reduce the
time for palindromic checks to O(1) by reusing some previous computation.
