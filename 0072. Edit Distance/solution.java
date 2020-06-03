/**
 * Other's solution of DP
 *
 * Time & Space: O(m*n)
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    cost[i + 1][j + 1] = Math.min(cost[i][j], Math.min(cost[i][j + 1], cost[i + 1][j]));
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
}


/**
 * Other's solution with less space
 *
 * Time: O(m*n)
 * Space: O(n)
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[] cost = new int[n + 1];
        for(int i = 1; i <= n; i++)
            cost[i] = i;
        
        int prevDiagonal;
        for(int i = 0; i < m; i++) {
            prevDiagonal = cost[0];
            cost[0] = i+1;
            for(int j = 0; j < n; j++) {
                int tmp = cost[j+1];
                if(word1.charAt(i) == word2.charAt(j))
                    cost[j + 1] = prevDiagonal;
                else {
                    cost[j + 1] = Math.min(prevDiagonal, Math.min(cost[j + 1], cost[j]));
                    cost[j + 1]++;
                }
                prevDiagonal = tmp;
            }
        }
        return cost[n];
    }
}
