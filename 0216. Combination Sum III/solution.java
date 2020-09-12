/**
 * Solution of Backtracking
 */
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0) return res;
        
        backtrack(1, n, k, res, new ArrayList<>());
        return res;
    }
    
    public void backtrack(int start, int n, int k, List<List<Integer>> list, List<Integer> tmpList) {
        if (k == 0 && n == 0) {
            list.add(new ArrayList<Integer>(tmpList));
        } else if (k > 0 && n > 0) {
            for (int i = start; i <= 9; i++) {              
                if (n - i < 0) break;
                
                tmpList.add(i);
                backtrack(i+1, n-i, k-1, list, tmpList);
                tmpList.remove(tmpList.size()-1);
            }
        } else {
            return;
        }
    }
}


/**
 * Another solution of Backtracking
 */
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, new ArrayList<Integer>(), k, 1, n);
        return res;
    }
    
    private void combination(List<List<Integer>> res, List<Integer> comb, int k, int start, int n) {
        if (comb.size() > k || n < 0) return;
        if (comb.size() == k && n == 0) {
            res.add(new ArrayList<Integer>(comb));
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            combination(res, comb, k, i + 1, n - i);
            comb.remove(comb.size() - 1); // backtracking
        }
    }
}
