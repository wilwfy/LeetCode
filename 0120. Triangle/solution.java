/**
 * Other's solution of bottom-up DP
 *
 * Intuition and algorithm
 *
 * 'Bottom-up' DP is very straightforward:
 * we start from the nodes on the bottom row; the min pathsums for these nodes are the values of the
 * nodes themselves. From there, the min pathsum at the ith node on the kth row would be the lesser
 * of the pathsums of its two children plus the value of itself, i.e.:
 * 
 * minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];
 * 
 * Or even better, since the row minpath[k+1] would be useless after minpath[k] is computed, we can
 * simply set minpath as a 1D array, and iteratively update itself:
 * 
 * For the kth level:
 * minpath[i] = min( minpath[i], minpath[i+1]) + triangle[k][i];
 *
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowNum = triangle.size();
        int[] dp = new int[rowNum]; // the size of the bottom row is rowNum
        for (int i = 0; i < rowNum; i++)
            dp[i] = triangle.get(rowNum - 1).get(i);
        
        for (int row = rowNum - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++)
                dp[col] = Math.min(dp[col], dp[col + 1]) + triangle.get(row).get(col);
        }
        
        return dp[0]; // at top row there is only one element
    }
}


/**
 * Other's solution of top-down DP
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowNum = triangle.get(triangle.size() - 1).size();
        int colNum = triangle.size();
        int[][] dp = new int[rowNum][colNum];
        int i = 0;
        for (Integer n : triangle.get(colNum - 1)) {
            dp[rowNum - 1][i++] = n;
        }
        for (int row = rowNum - 2, m = 0; row >= 0; row--, m++) {
            for (int col = 0; col <= colNum - 2 - m; col++) {
                dp[row][col] = Math.min(dp[row + 1][col], dp[row + 1][col + 1]) + triangle.get(row).get(col);
            }
        }
        return dp[0][0];
    }
}


/**
 * Another's solution of top-down DP
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // corner case
        if(triangle == null || triangle.size() == 0) return 0;
        
        // M[i] represents the min total from bottom to current position
        int m = triangle.size();
        int n = triangle.get(m - 1).size(); // last row size
        int[] M = new int[n];
        M[0] = triangle.get(0).get(0);
        
        // induction rule
        // M[j] = min(M[j - 1], M[j]) + curVal
        for(int i = 1; i < m; i++){
            List<Integer> cur = triangle.get(i);
            for(int j = cur.size() - 1; j >= 0; j--){
                if(j == 0){
                    M[0] = M[0] + cur.get(j);
                }else if(j == cur.size() - 1){
                    M[j] = M[j - 1] + cur.get(j);
                }else{
                    M[j] = Math.min(M[j - 1], M[j]) + cur.get(j);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){ 
            min = Math.min(min, M[i]);
        }
                
        return min;
    }
}
