Hard

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

**Example:**
```
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
```
**Note:**

- If there is no such window in S that covers all characters in T, return the empty string "".
- If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

**Hint 1**  
Use two pointers to create a window of letters in S, which would have all the characters from T.

**Hint 2**  
Since you have to find the minimum window in S which has all the characters from T, you need to expand and contract the window using the two pointers and keep checking the window for all the characters. This approach is also called Sliding Window Approach.

L ------------------------ R , Suppose this is the window that contains all characters of T 
                          
        L----------------- R , this is the contracted window. We found a smaller window that still contains all the characters in T

When the window is no longer valid, start expanding again using the right 
