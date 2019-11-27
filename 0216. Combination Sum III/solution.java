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
