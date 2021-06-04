**Medium**

Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

**Example 1:**

![1465_max_area_1](https://github.com/wilwfy/LeetCode/blob/master/1465.%20Maximum%20Area%20of%20a%20Piece%20of%20Cake%20After%20Horizontal%20and%20Vertical%20Cuts/1465_max_area_1.png)
```
Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines
are the horizontal and vertical cuts. After you cut the cake, the green piece of
cake has the maximum area.
```
**Example 2:**

![1465_max_area_2](https://github.com/wilwfy/LeetCode/blob/master/1465.%20Maximum%20Area%20of%20a%20Piece%20of%20Cake%20After%20Horizontal%20and%20Vertical%20Cuts/1465_max_area_2.png)
```
Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are
the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces
of cake have the maximum area.
```
**Example 3:**
```
Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
```

**Constraints:**

- `2 <= h, w <= 10^9`
- `1 <= horizontalCuts.length < min(h, 10^5)`
- `1 <= verticalCuts.length < min(w, 10^5)`
- `1 <= horizontalCuts[i] < h`
- `1 <= verticalCuts[i] < w`
- It is guaranteed that all elements in `horizontalCuts` are distinct.
- It is guaranteed that all elements in `verticalCuts` are distinct.

**Hint 1**  
Sort the arrays, then compute the maximum difference between two consecutive elements for horizontal cuts and vertical cuts.

**Hint 2**  
The answer is the product of these maximum values in horizontal cuts and vertical cuts.
