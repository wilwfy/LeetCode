/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        
        backtrack(0, candidates, target, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int[] candidates, int target, List<List<Integer>> list, List<Integer> tmpList) {
        if (target >= 0) {
            if (target == 0) {
                list.add(new ArrayList<Integer>(tmpList));
            } else {
                for (int i = start; i < candidates.length; i++) {
                    tmpList.add(candidates[i]);
                    //target -= candidates[i];
                    
                    // not i + 1 because we can reuse same elements
                    backtrack(i, candidates, target - candidates[i], list, tmpList);
                    
                    tmpList.remove(tmpList.size()-1);
                    //target += candidates[i];
                }
            }
        }
    }
}
