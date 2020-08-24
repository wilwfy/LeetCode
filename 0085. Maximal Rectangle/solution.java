/**
 * Other's solution with Stack
 *
 * Intuition
 * This question is similar as 84. Largest Rectangle in Histogram:
 * 
 * You can maintain a row length of Integer array H recorded its height of '1's, and scan and update row by row
 * to find out the largest rectangle of each row.
 * 
 * For each row, if matrix[row][i] == '1'. H[i] +=1, or reset the H[i] to zero.
 * and accroding the algorithm of [Largest Rectangle in Histogram], to update the maximum area.
 *
 * More Explanation:
 *     https://leetcode.wang/leetCode-85-Maximal-Rectangle.html
 *
 * Time: O(m * n)
 * Space: O(n)
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // height array
        int[] h = new int[n+1]; // height at each column
        int maxArea = 0;
        
        for (int row = 0; row < m; row++) {
            Stack<Integer> stack = new Stack<>();
            for (int col = 0; col < n + 1; col++) {
                // set height
                if (col < n && matrix[row][col] == '1')
                    h[col]++;
                else
                    h[col] = 0;
                
                // compute area
                while (!stack.isEmpty() && h[stack.peek()] >= h[col]) {
                    int height = h[stack.pop()];
                    int width = stack.isEmpty() ? col : (col - stack.peek() - 1);
                    int area = height * width;
                    maxArea = Math.max(maxArea, area);
                }
                stack.push(col);
            }
        }
        return maxArea;
    }
}


/**
 * Other's solution of DP
 *
 * Intuition and Algorithm
 *
 * we start from the first row, and move downward;
 * height[i] record the current number of countinous '1' in column i;
 * left[i] record the left most index j which satisfies that for any index k from j to  i, height[k] >= height[i];
 * right[i] record the right most index j which satifies that for any index k from i to  j, height[k] >= height[i];
 * by understanding the definition, we can easily figure out we need to update maxArea with value (height[i] * (right[i] - left[i] + 1));
 * 
 * Then the question is how to update the array of height, left, and right
 * =================================
 * for updating height, it is easy
 * for (int j = 0; j < n; j++) {
 *    if (matrix[i][j] == '1') height[j]++;
 *    else height[j] = 0;
 * }
 * =============================================================================
 * It is a little bit tricky for initializing and updating left and right array
 * for initialization: 
 * we know initially, height array contains all 0, so according to the definition of left and right array, left array should contains all 0, and right array
 * should contain all n - 1
 * for updating left and right, it is kind of tricky to understand...
 *     ==============================================================
 *     Here is the code for updating left array, we scan from left to right
 *     int lB = 0;  //lB is indicating the left boundry for the current row of the matrix (for cells with char "1"), not the height array...
 *     for (int j = 0; j < n; j++) {
 *          if (matrix[i][j] == '1') {
 *              left[j] = Math.max(left[j], lB); // this means the current boundry should satisfy two conditions: within the boundry of the previous height array,
 *                                               // and within the boundry of the current row...
 *          } else {
 *              left[j] = 0; // when matrix[i][j] = 0, height[j] will get update to 0, so left[j] becomes 0, since all height in between 0 - j satisfies the
 *                           // condition of height[k] >= height[j];
 *              lB = j + 1; //and since current position is '0', so the left most boundry for next "1" cell is at least j + 1;
 *          }
 *     }
 *     ==============================================================
 *     the idea for updating the right boundary is similar, we just need to iterate from right to left
 *     int rB = n - 1;
 *     for (int j = n - 1; j >= 0; j--) {
 *         if (matrix[i][j] == '1') {
 *              right[j] = Math.min(right[j], rB);
 *         } else {
 *              right[j] = n - 1;
 *              rB = j - 1;
 *      }
 *
 * If you think this algorithm is not easy to understand, you can try this example:
 * 
 * 0 0 0 1 0 0 0 
 * 0 0 1 1 1 0 0 
 * 0 1 1 1 1 1 0
 * 
 * The vector "left" and "right" from row 0 to row 2 are as follows
 * 
 * row 0:
 * 
 * l: 0 0 0 3 0 0 0
 * r: 7 7 7 4 7 7 7
 * 
 * row 1:
 * 
 * l: 0 0 2 3 2 0 0
 * r: 7 7 5 4 5 7 7
 * 
 * row 2:
 * 
 * l: 0 1 2 3 2 1 0
 * r: 7 6 5 4 5 6 7
 * 
 * The vector "left" is computing the left boundary. Take (i,j)=(1,3) for example.
 * On current row 1, the left boundary is at j=2. However, because matrix[1][3] is 1,
 * you need to consider the left boundary on previous row as well, which is 3. So the
 * real left boundary at (1,3) is 3.
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, maxArea = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(right, n - 1);
        
        for (int i = 0; i < m; i++) {
            int rB = n - 1; // default right Boundry
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], rB);
                } else {
                    right[j] = n - 1;
                    rB = j - 1;
                }
            }
            int lB = 0; // default left Boundry
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], lB);
                    height[j]++;
                    maxArea = Math.max(maxArea, height[j] * (right[j] - left[j] + 1));
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    lB = j + 1;
                }
            }
        }
        return maxArea;
    }
}
