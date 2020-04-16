/*
 * Other's solution with Two Stacks
 *
 * The basic idea is to track the index of the left bracket and star position.
 * Push all the indices of the star and left bracket to their stack respectively.
 * STEP 1
 * Once a right bracket comes, pop left bracket stack first if it is not empty. If the left bracket stack is empty, pop the star stack
 * if it is not empty. A false return can be made provided that both stacks are empty.
 *
 * STEP 2
 * Now attention is paid to the remaining stuff in these two stacks. Note that the left bracket CANNOT appear after the star as there
 * is NO way to balance the bracket. In other words, whenever there is a left bracket index appears after the Last star, a false
 * statement can be made. Otherwise, pop out each from the left bracket and star stack.
 *
 * STEP 3
 * A correct sequence should have an empty left bracket stack. You don't need to take care of the star stack.
 *
 * Final Remarks:
 * Greedy algorithm is used here. We always want to use left brackets to balance the right one first as the * symbol is a wild card.
 * There is probably an O(1) space complexity but I think this is worth mentioning.
 *
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> leftChar = new Stack<>();
        Stack<Integer> starChar = new Stack<>();
        for (int i = 0; i <s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                leftChar.push(i);
            else if (c == '*')
                starChar.push(i);
            else {
                if (!leftChar.isEmpty())
                    leftChar.pop();
                else if (!starChar.isEmpty())
                    starChar.pop();
                else
                    return false; // ')' occurs before '(' or '*'
            }
        }
        while (!leftChar.isEmpty() && !starChar.isEmpty()) {
            if (leftChar.pop() > starChar.pop())
                return false; // '(' is on right side of '*' without ')' any more
        }
        return leftChar.isEmpty();
    }
}


/*
 * Official solution of Dynamic Programming
 *
 * Intuition and Algorithm
 * Let dp[i][j] be true if and only if the interval s[i], s[i+1], ..., s[j] can be made valid. Then dp[i][j] is true only if:
 * s[i] is '*', and the interval s[i+1], s[i+2], ..., s[j] can be made valid;
 * or, s[i] can be made to be '(', and there is some k in [i+1, j] such that s[k] can be made to be ')', plus the two intervals
 * cut by s[k] (s[i+1: k] and s[k+1: j+1]) can be made valid;
 *
 * Time Complexity: O(N^3), where NN is the length of the string. There are O(N^2) states corresponding to entries of dp, and we
 *                  do an average of O(N) work on each state.
 * Space Complexity: O(N^2), the space used to store intermediate results in dp.
 */
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') dp[i][i] = true;
            if (i < n-1 &&
                    (s.charAt(i) == '(' || s.charAt(i) == '*') &&
                    (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) {
                dp[i][i+1] = true;
            }
        }

        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == '*' && dp[i+1][i+size] == true) {
                    dp[i][i+size] = true;
                } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    for (int k = i+1; k <= i+size; k++) {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                                (k == i+1 || dp[i+1][k-1]) &&
                                (k == i+size || dp[k+1][i+size])) {
                            dp[i][i+size] = true;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}

