/**
 * Official solution of using Recursion
 *
 * Time complexity : O(2^n). Size of recursion tree will be 2^n. Here, n refers to the length of nums array.
 * Space complexity : O(n). The depth of the recursion tree can go upto n.
 */
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // player1's turn is 1, player2's turn is -1
        return winner(nums, 0, nums.length-1, 1) >= 0;
    }
    
    public int winner(int[] nums, int start, int end, int turn) {
        if (start == end)
            return turn * nums[start];
        
        // player select the number at head
        int a = turn * nums[start] + winner(nums, start+1, end, -turn);
        // player select the number at tail
        int b = turn * nums[end] + winner(nums, start, end-1, -turn);
        
        // Below is equivalent to the statement max(a,b) for Player 1's turn and min(a,b) for Player 2's turn
        return turn * Math.max(turn * a, turn * b);
    }
}

/**
 * Official solution of using Recursion with Memoization
 *
 * Time complexity : O(n^2). The entire memo array of size n x n is filled only once. Here, n refers to the size of nums array.
 * Space complexity : O(n^2). memo array of size n x n is used for memoization.
 */
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // Use Integer[][] not int[][] because the calculation result
        // could be 0 which is the default value of int[][] element,
        // So we need use null as the default value.
        Integer[][] memo = new Integer[nums.length][nums.length];
        return winner(nums, 0, nums.length-1, memo) >= 0;
    }
    
    public int winner(int[] nums, int start, int end, Integer[][] memo) {
        if (start == end)
            return nums[start];
        
        if (memo[start][end] != null)
            return memo[start][end];
        
        // player select the number at head then substract the other player for next turn
        int a = nums[start] - winner(nums, start+1, end, memo);
        // player select the number at tail then substract the other player for next turn
        int b = nums[end] - winner(nums, start, end-1, memo);
        
        // select the greater value for the player under the subarray from indices start~end
        memo[start][end] = Math.max(a, b);
        return memo[start][end];
    }
}
