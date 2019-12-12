/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0) return res;
        
        backtrack(0, s, res, new ArrayList<>());
        return res;
    }
    
    public void backtrack(int start, String s, List<List<String>> list, List<String> tmpList) {
        if (start == s.length()) {
            list.add(new ArrayList<String>(tmpList));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tmpList.add(s.substring(start, i+1));
                    backtrack(i+1, s, list, tmpList);
                    tmpList.remove(tmpList.size()-1);
                }
            }
        }
    }
    
    public boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi))
                return false;
            
            lo++;
            hi--;
        }
        return true;
    }
}


/**
 * Other's solution of DP + DFS
 * https://leetcode.com/problems/palindrome-partitioning/discuss/41982/Java-DP-%2B-DFS-solution
 */
class Solution {   
    public List<List<String>> partition(String s) {
        // generate the DP array of palindrome
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && ((i-j <= 2) || dp[j+1][i-1]))
                    dp[j][i] = true;
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        backtrack(0, s, dp, res, new ArrayList<>());
        return res;
    }
    
    public void backtrack(int start, String s, boolean[][] dp, List<List<String>> list, List<String> tmpList) {
        if (start == s.length()) {
            // the whole string has been scanned
            list.add(new ArrayList<>(tmpList));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (dp[start][i]) {
                    tmpList.add(s.substring(start, i+1));
                    backtrack(i+1, s, dp, list, tmpList);
                    tmpList.remove(tmpList.size()-1);
                }
            }
        }
    }
}
