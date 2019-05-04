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



