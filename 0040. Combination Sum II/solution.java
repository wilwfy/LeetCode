/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        
        Arrays.sort(candidates);

        backtrack(0, candidates, target, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int[] candid, int target, List<List<Integer>> list, List<Integer> tmpList) {
        if (target < 0)
            return;
        else if (target == 0) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = start; i < candid.length; i++) {
                if (i > start && candid[i] == candid[i-1]) continue;
                
                tmpList.add(candid[i]);
                
                backtrack(i+1, candid, target-candid[i], list, tmpList);
                
                tmpList.remove(tmpList.size()-1);
            }
        }
    }
}


/**
 * Other's improved solution of Backtracking with line of 'break'
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        
        Arrays.sort(candidates);

        backtrack(0, candidates, target, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int[] candid, int target, List<List<Integer>> list, List<Integer> tmpList) {
        if (target < 0)
            return;
        else if (target == 0) {
            list.add(new ArrayList<Integer>(tmpList));
        } else {
            for (int i = start; i < candid.length; i++) {
                if (i > start && candid[i] == candid[i-1]) continue;
                
                // The array is sorted, no need to try rest numbers if negtive value got here
                if (target-candid[i] < 0) break;
                
                tmpList.add(candid[i]);
                
                backtrack(i+1, candid, target-candid[i], list, tmpList);
                
                tmpList.remove(tmpList.size()-1);
            }
        }
    }
}
