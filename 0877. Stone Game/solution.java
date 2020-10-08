/**
 * Other's solution Minimax + DP with Recursion + Memoization
 *
 * This is a Minimax problem. Each player plays optimally to win, but you can't greedily choose
 * the optimal strategy so you have to try all strategies, this is DP now.
 * 
 * What does it mean for Alex to win? Alex will win if score(Alex) >= score(Lee), but this also
 * means score(Alex) - score(Lee) >= 0, so here you have a common parameter which is a score variable.
 * The score parameter really means score = score(Alex) - score(Lee).
 * 
 * Now, if each player is suppoed to play optimally, how do you decide this with one variable?
 * 
 * Well since Alex is playing optimally, he wants to maximize the score variable because remember,
 * Alex only wins if score = score(Alex) - score(Lee) >= 0 Alex should add to the score because he
 * wants to maximize it.
 * Since Lee is also playing optimally, he wants to minimize the score variable, since if the score
 * variable becomes negative, Lee has more individual score than Alex. But since we have only one
 * variable, Lee should damage the score (or in other words, subtract from the score).
 * 
 * Now, let's think of the brute force solution. You are at the current state, say this is Alex's turn.
 * Alex can choose either left or right, but he can't greedily pick so you try both of them, this is O(2^n)
 * for runtime.
 * 
 * But realize the state you are in. You can easily memoize the this, the varying parameters are l, r, ID,
 * where ID is the player ID (1 for Alex, 0 for Lee), hence you can make a DP/Cache table and return answer
 * if you have discovered the state.
 * 
 * Finally, in your main function you call this helper function and check if you were able to get a score >= 0.
 */
class Solution {
    int [][][] memo;
    
    public boolean stoneGame(int[] piles) {
        memo = new int[piles.length + 1][piles.length + 1][2];
        for(int [][] arr : memo)
            for(int [] subArr : arr)
                Arrays.fill(subArr, -1);
        
        return (helper(0, piles.length - 1, piles, 1) >= 0); // Alex's ID is 1
    }
    
    public int helper(int l, int r, int [] piles, int ID){
        if(r < l)
            return 0;
        if(memo[l][r][ID] != -1)
            return memo[l][r][ID];
        
        int next = Math.abs(ID - 1);
        if(ID == 1)
            memo[l][r][ID] = Math.max(piles[l] + helper(l + 1, r, piles, next), piles[r] + helper(l, r - 1, piles, next));
        else
            memo[l][r][ID] = Math.min(-piles[l] + helper(l + 1, r, piles, next), -piles[r] + helper(l, r - 1, piles, next));
        
        return memo[l][r][ID];
    }
}


/**
 * Official solution of Dynamic Programming
 * 
 * Intuition
 * 
 * Let's change the game so that whenever Lee scores points, it deducts from Alex's score instead.
 * 
 * Let dp(i, j) be the largest score Alex can achieve where the piles remaining are piles[i], piles[i+1],
 * ..., piles[j]. This is natural in games with scoring: we want to know what the value of each position
 * of the game is.
 * 
 * We can formulate a recursion for dp(i, j) in terms of dp(i+1, j) and dp(i, j-1), and we can use dynamic
 * programming to not repeat work in this recursion. (This approach can output the correct answer, because
 * the states form a DAG (directed acyclic graph).)
 * 
 * Algorithm
 * 
 * When the piles remaining are piles[i], piles[i+1], ..., piles[j], the player who's turn it is has at most 2 moves.
 * 
 * The person who's turn it is can be found by comparing j-i to N modulo 2.
 * 
 * If the player is Alex, then she either takes piles[i] or piles[j], increasing her score by that amount. Afterwards,
 * the total score is either piles[i] + dp(i+1, j), or piles[j] + dp(i, j-1); and we want the maximum possible score.
 * 
 * If the player is Lee, then he either takes piles[i] or piles[j], decreasing Alex's score by that amount. Afterwards,
 * the total score is either -piles[i] + dp(i+1, j), or -piles[j] + dp(i, j-1); and we want the minimum possible score.
 *
 * Time Complexity: O(N^2), where N is the number of piles.
 * Space Complexity: O(N^2), the space used storing the intermediate results of each subgame.
 */
class Solution {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        
        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2; // j - i - N; but +x = -x (mod 2)
                
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }
        
        return dp[1][N] > 0;
    }
}
