/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if ((k <= 0) || (n == 0) || (k > n)) return res;
        backtrack(1, n, k, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int n, int k, List<List<Integer>> res, List<Integer> tmpList) {
        if (tmpList.size() == k) {         
            res.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = start; i <= n; i++) {                
                tmpList.add(i);
                backtrack(i+1, n, k, res, tmpList);
                tmpList.remove(tmpList.size()-1);
            }
        }
    }
}
