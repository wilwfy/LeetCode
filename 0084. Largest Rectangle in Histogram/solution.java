/**
 * Other's solution with Memoization
 *
 * Algorithm
 * For any bar i the maximum rectangle is of width r - l - 1 where r - is the last coordinate of the bar to the
 * right with height h[r] >= h[i] and l - is the last coordinate of the bar to the left which height h[l] >= h[i]
 * 
 * So if for any i coordinate we know his utmost higher (or of the same height) neighbors to the right and to the
 * left, we can easily find the largest rectangle:
 * 
 *     int maxArea = 0;
 *     for (int i = 0; i < height.length; i++) {
 *         maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
 *     }
 * 
 * The main trick is how to effectively calculate lessFromRight and lessFromLeft arrays. The trivial solution is
 * to use O(n^2) solution and for each i element first find his left/right heighbour in the second inner loop just
 * iterating back or forward:
 * 
 *     for (int i = 1; i < height.length; i++) {              
 *         int p = i - 1;
 *         while (p >= 0 && height[p] >= height[i]) {
 *             p--;
 *         }
 *         lessFromLeft[i] = p;              
 *     }
 * The only line change shifts this algorithm from O(n^2) to O(n) complexity: we don't need to rescan each item to
 * the left - we can reuse results of previous calculations and "jump" through indices in quick manner:
 * 
 *     while (p >= 0 && height[p] >= height[i]) {
 *           p = lessFromLeft[p];
 *     }
 *
 * Time: amortized O(N)
 * Space: O(N)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int N = heights.length;
        int[] lessFromLeft = new int[N]; // idx of the first bar that is lower than current bar on the left
        int[] lessFromRight = new int[N]; // idx of the first bar that is lower than current bar on the right
        lessFromLeft[0] = -1;
        lessFromRight[N - 1] = N;
        
        for (int i = 1; i < N; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        
        for (int i = N - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < N && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        
        int maxArea = 0;
        for (int i = 0; i < N; i++)
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        
        return maxArea;
    }
}
