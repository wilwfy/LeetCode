/**
 * Other's solution with HashMap of Prefix Sum
 *
 * Intuition
 * Preaquis: 560. Subarray Sum Equals K
 * --  https://leetcode.com/problems/subarray-sum-equals-k
 * Find the Subarray with Target Sum in linear time.
 * 
 * Explanation
 * For each row, calculate the prefix sum.
 * For each pair of columns,
 * calculate the accumulated sum of rows.
 * Now this problem is same to, "Find the Subarray with Target Sum".
 * 
 * Complexity
 * Time O(M * N^2)
 * Space O(N)
 */
 
/**
 * Let's consider a concrete example
 *
 * matrix = {
 *          0 1 0
 *          1 1 1
 *          0 1 0
 *          }
 *
 * Find the number of submatrix that sum to a target 4:
 *
 * [0 1]  [1 0]               [0 1 0]
 * [1 1]  [1 1]  [1 1 1]  and [1 1 1]
 * [0 1]  [1 0]  [0 1 0]
 *
 * Line by Line Analysis
 *
 *  int m = A.length, n = A[0].length;
 *         for (int i = 0; i < m; i++)
 *             for (int j = 1; j < n; j++)
 *                 A[i][j] += A[i][j - 1];
 *
 *   The code above goes over rows and calculates prefix sum [0:col]:
 *
 *     0 1 1
 *  A= 1 2 3
 *     0 1 1
 *
 *  int res = 0;
 *         for (int i = 0; i < n; i++) {
 *             for (int j = i; j < n; j++) {
 *                 Map<Integer, Integer> counter = new HashMap<>();
 *                 counter.put(0, 1);
 *                 int cur = 0;
 *
 *  The code above sets up two for loops - both transverse columns.
 *  We reset map for each cols range [i:j]  and add an empty sub-matrix with sum 0 (for cases when sub-matrix sum == tgt).
 *  Also reset cur range sum [0:k][i:j] to 0;
 *
 *  for (int k = 0; k < m; k++) {
 *       cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
 *       res += counter.getOrDefault(cur - target, 0);
 *       counter.put(cur, counter.getOrDefault(cur, 0) + 1);
 *       }
 *    }
 *  }
 *     return res;
 *   }
 *
 *   In above block k goes over all rows and adds up their prefix sum - from range sum of cols [i:j]
 *   Then checks for how many complement rows we have - that can give delta == tgt
 *
 *   lets visualize this loop
 *
 *   3 X loops i j k; 
 *   - i goes from 0 to n (cols)
 *   - j goes from i to n (cols)
 *   - k goes from 0 to m (rows)
 *   
 *    cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
 *
 *    0 1 1                     0  1  1
 *    1 2 3  -> since i = 0  -> 1  3  4  -> j went from 0 to 2 and k also changed from 0 to 2;
 *    0 1 1                     1  4  5
 *                                                        x 1 1   0   x 1 1 
 *    Now i = 1, then we subtract A[k][j] - A[k][i-1]     x 2 3 - 1 = x 2 3  -> j only goes from 1 to 2
 *                                                        x 1 1   0   x 3 4
 *
 *                                        x x 1   1   x x 0
 * finally i is 2 and j goes from 2 to 2  x x 3 - 2 = x x 1   
 *                                        x x 1   1   x x 1
 *
 *                                    0  1  1   1  1   0
 * Lets stack all cur values we have  1  3  4   2  3   1
 *                                    1  4  5   3  4   1
 *
 *  for each step we added count for cur sum in map :  cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0); 
 *  
 *  lets see what our hashmap looks like + get # of submatrices w/ sum == target 4;
 *
 * cur-count (added to map after we finished each step)
 *    |
 *   0-1  inititally
 *  *0-2  complement we need = cur-target = 0-4 = (-4)   - we don't have -4 in our map
 *  *1-1  (-3)
 *  *1-2  (-3)
 *
 *   0-1  inititally
 *  *1-1  (-3)
 *  *3-1  (-1)
 *  *4-1  (0) - we have 1
 *
 *   0-1  inititally
 *  *1-1  (-3)
 *  *4-1  (0) - we have 1
 *  *5-1  (1) - we have 1
 *
 *   0-1  inititally
 *  *1-1  (-3)
 *  *2-1  (-2)
 *  *3-1  (-1)
 *
 *   0-1  inititally
 *  *1-1  (-3)
 *  *3-1  (-1)
 *  *4-1  (0) - we have 1
 *
 *   0-1  inititally
 *  *0-2  (-2)
 *  *1-1  (-3)
 *  *1-2  (-3)
 *  *
 */
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0, m = matrix.length, n = matrix[0].length;
        // calculate prefix sum for each row
        for (int i = 0; i < m; i++)
            for (int j = 1; j < n; j++)
                matrix[i][j] += matrix[i][j - 1];
        // for every possible range between two columns, accumulate the
        // prefix sum of submatrices that can be formed between these
        // two columns by adding up the sum of values between these two
        // columns for every row
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    // if sum[i] - sum[j] = target, the sum of elements lying between indices i and j is target.
                    // let cur = sum[j], and if sum[j] - target ever occured in the map, then there is a submatrix
                    // whose sum is target between sum[i] and sum[j] where i < j
                    res += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
