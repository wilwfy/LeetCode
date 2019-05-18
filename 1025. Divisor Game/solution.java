class Solution {
    public boolean divisorGame(int N) {
        if (N <= 1) return false;
        // dp[i] = true denotes the input number for the game is i and the player
        // who takes first move will win.
        boolean[] dp = new boolean[N+1];
        // the default initialized value of boolean array is false alreay.
        // boolean[0] = false;
        // boolean[1] = false;
        
        // Need firstly get all the result for the numbers i prior to N,
        // then get result for N
        for (int i = 2; i <= N; i++) {
            // For each number i, check if there's a previous winning result already
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    if (dp[i-j] == false) {
                        // since the opponent is losing in this previous number i-j,
                        // then the player will win for input i by moving with j
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[N];
    }
}
