class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, 0, 0, "", res);
        return res;
    }
    
    public void helper(int max, int open_par, int close_par, String combine, List<String> res) {
        if (combine.length() == max * 2) {
            res.add(combine);
            return;
        }
        if (open_par < max) {
            helper(max, open_par+1, close_par, combine+"(", res);
        }
        // The number of ")" depends on the number of "(" generated previously
        if (close_par < open_par) {
            helper(max, open_par, close_par+1, combine+")", res);
        }
    }
}


/*
 * Official Solution: Backtracking
 *
 * We can start an opening bracket if we still have one (of n) left to place. And we can
 * start a closing bracket if it would not exceed the number of opening brackets.
 *
 * Time Complexity : O((4^n)/sqrt(n)). Each valid sequence has at most n steps during the backtracking procedure.
 * Space Complexity : O((4^n)/sqrt(n)), as described above, and using O(n) space to store the sequence. 
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}


/*
 * Official Solution: Closure Number
 *
 * To enumerate something, generally we would like to express it as a sum of disjoint subsets that are
 * easier to count. Consider the closure number of a valid parentheses sequence S: the least index >= 0
 * so that S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence has a unique closure
 * number. We can try to enumerate them individually.
 *
 * Time and Space Complexity : O((4^n)/sqrt(n))
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
