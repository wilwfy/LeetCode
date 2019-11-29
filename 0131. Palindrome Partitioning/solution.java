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
