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


/*
 * Official solution of Greedy and One Pass
 *
 * Intuition
 * When checking whether the string is valid, we only cared about the "balance": the number of extra, open left brackets as we parsed
 * through the string. For example, when checking whether '(()())' is valid, we had a balance of 1, 2, 1, 2, 1, 0 as we parse through
 * the string: '(' has 1 left bracket, '((' has 2, '(()' has 1, and so on. This means that after parsing the first i symbols, (which
 * may include asterisks,) we only need to keep track of what the balance could be.
 * For example, if we have string '(***)', then as we parse each symbol, the set of possible values for the balance is [1] for '(';
 * [0, 1, 2] for '(*'; [0, 1, 2, 3] for '(**'; [0, 1, 2, 3, 4] for '(***', and [0, 1, 2, 3] for '(***)'.
 * Furthermore, we can prove these states always form a contiguous interval. Thus, we only need to know the left and right bounds of
 * this interval. That is, we would keep those intermediate states described above as [lo, hi] = [1, 1], [0, 2], [0, 3], [0, 4], [0, 3].
 *
 * Algorithm
 * Let lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current character in
 * the string.
 * If we encounter a left bracket (c == '('), then lo++, otherwise we could write a right bracket, so lo--. If we encounter what can be
 * a left bracket (c != ')'), then hi++, otherwise we must write a right bracket, so hi--. If hi < 0, then the current prefix can't be
 * made valid no matter what our choices are. Also, we can never have less than 0 open left brackets. At the end, we should check that
 * we can have exactly 0 open left brackets.
 *
 * Time Complexity: O(N), where N is the length of the string. We iterate through the string once.
 * Space Complexity: O(1), the space used by our lo and hi pointers. However, creating a new character array will take O(N) space.
 */
class Solution {
    public boolean checkValidString(String s) {
       int lo = 0, hi = 0;
       for (char c: s.toCharArray()) {
           lo += c == '(' ? 1 : -1;
           hi += c != ')' ? 1 : -1;
           if (hi < 0) break;
           lo = Math.max(lo, 0);
       }
       return lo == 0;
    }
}


/*
 * Other's solution of Greedy and One Pass
 *
 * Intuition:
 * One pass on the string S, we need to know, how many ')' we are waiting for.
 * If we meet too many ')', we can return false directly.
 * If we wait for no ')' at the end, then we are good.
 *
 * Explanation:
 * We count the number of ')' we are waiting for, and it's equal to the number of open parenthesis.
 * This number will be in a range and we count it as [cmin, cmax]
 * cmax counts the maximum open parenthesis, which means the maximum number of unbalanced '(' that COULD be paired.
 * cmin counts the minimum open parenthesis, which means the number of unbalanced '(' that MUST be paired.
 *
 * Example:
 * It's quite straight forward actually.
 * When you met "(", you know you need one only one ")", cmin = 1 and cmax = 1.
 * When you met "(*(", you know you need one/two/three ")", cmin = 1 and cmax = 3.
 *
 * The string is valid for 2 condition:
 * cmax will never be negative.
 * cmin is 0 at the end.
 *
 * Time Complexity: One pass, O(N) time.
 * Space Complexity: O(1)
 */
class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0, cmax = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }
}
