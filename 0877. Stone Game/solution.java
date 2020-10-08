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
