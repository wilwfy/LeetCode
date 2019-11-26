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


/**
 * Other's solution of Dynamic Programming but performance is not good as the Backtracking
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // sort candidates to try them in asc order
        Arrays.sort(candidates); 
        // dp[t] stores all combinations that add up to t
        List<List<Integer>>[] dp = new ArrayList[target+1];
        
        // build up dp
        for(int t=0; t<=target; t++) {
            // initialize
            dp[t] = new ArrayList();
            // initialize
            List<List<Integer>> combList = new ArrayList();
            
            // for each t, find possible combinations
            for(int j=0; j<candidates.length && candidates[j] <= t; j++) {
                if(candidates[j] == t) {
                    combList.add(Arrays.asList(candidates[j])); // itself can form a list
                } else {
                    for(List<Integer> prevlist: dp[t-candidates[j]]) { // here use our dp definition
                        // The author thought it makes more sense to compare with the last element
                        // only add to list when the candidates[j] >= the last element
                        // so the list remains ascending order, can prevent duplicate (ex. has [2 3 3], no [3 2 3]),
                        // if the current candidate is smaller than the last one, that means the current candidate
                        // has been used
                        // equal is needed since we can choose the same element many times   
                        if(candidates[j] >= prevlist.get(prevlist.size()-1)){
                            List temp = new ArrayList(prevlist); // temp is needed since 
                            temp.add(candidates[j]); // cannot edit prevlist inside 4eeach looop
                            combList.add(temp);
                        }
                    }
                }
            }
            dp[t] = combList;
        }
        return dp[target];
    }
}
