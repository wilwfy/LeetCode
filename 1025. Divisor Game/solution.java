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


/*
 * Other's solution
 */
class Solution {
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N+1];
        for(int i = 1; i <= N; i++){
            for(int x = 1; x < i; x++){
                if(i%x == 0 && !dp[i-x]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}


/**
Other's Mathematical solution:

Conclusion
If N is even, can win.
If N is odd, will lose.

Recursive Prove （Top-down)
If N is even.
We can choose x = 1.
The opponent will get N - 1, which is a odd.
Reduce to the case odd and he will lose.

If N is odd,
2.1 If N = 1, lose directly.
2.2 We have to choose an odd x.
The opponent will get N - x, which is a even.
Reduce to the case even and he will win.

So the N will change odd and even alternatively until N = 1.

Mathematical Induction Prove （Bottom-up)
N = 1, lose directly
N = 2, will win choosing x = 1.
N = 3, must lose choosing x = 1.
N = 4, will win choosing x = 1.
....

For N <= n, we have find that:
If N is even, can win.
If N is odd, will lose.

For the case N = n + 1
If N is even, we can win choosing x = 1,
give the opponent an odd number N - 1 = n,
force him to lose,
because we have found that all odd N <= n will lose.

If N is odd, there is no even x that N % x == 0.
As a result, we give the opponent a even number N - x,
and the opponent can win,
because we have found that all even N <= n can win.

Now we prove that, for all N <= n,
If N is even, can win.
If N is odd, will lose.

Java/C++:
        return N % 2 == 0;
*/
