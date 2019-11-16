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
