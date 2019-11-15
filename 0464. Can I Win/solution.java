/**
 * Other's solution by using DFS + backtracking with memoization
 */
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        // The player1 can never win if the sum of number 1 ~ maxChoosableInteger is smaller than desiredTotal
        if (maxChoosableInteger*(maxChoosableInteger+1)/2 < desiredTotal) return false;
        
        /* We will use int to record the state of usage of integers from pool [1,maxChoosableInteger]
        For example: maxChoosableInteger=2, we can choose from [1,2]
        we use 0b00 to represent no number has been used
               0b01 to represent 1 has been used
               0b10 to represent 2 has been used
               0b11 to represent 1,2 has been used
        */
        return helper(desiredTotal, new int[maxChoosableInteger], new HashMap<>());
    }
    
    public boolean helper(int total, int[] state, HashMap<String, Boolean> map) {
        String curState = Arrays.toString(state);
        if (map.containsKey(curState)) return map.get(curState);
        
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                // the number value = index + 1, since index starts from 0
                if ( (total <= i+1) || (!helper(total - (i+1), state, map)) ) {
                    // The player1 will win if the rest needed value is not greater
                    // than the number he chooses in this turn, or the player2
                    // looses in next turn after the number is choosen to be used
                    map.put(curState, true);
                    
                    // since the player1 will win at this number at this depth,
                    // game is over and nothing needs to do, so just revert the state
                    // of choosen number
                    state[i] = 0;
                    return true;
                }
                // Choosing number i+1 doesn't work for player1 to win,
                // set it unused and then try next number
                state[i] = 0;
            }
        }
        // player1 has still not win yet after trying all possible numbers
        map.put(curState, false);
        return false;
    }
}
