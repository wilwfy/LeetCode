/**
 * Other's solution of Backtracking
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;
    }
    
    public void backtrack(int start, int[] nums, List<List<Integer>> list, List<Integer> tmpList) {
        list.add(new ArrayList<Integer>(tmpList));
        
        for (int i = start; i < nums.length; i++) {
            // At the same level of iteration, skip the same number in the sorted array
            if (i > start && nums[i] == nums[i-1]) continue;
            
            tmpList.add(nums[i]);
            backtrack(i+1, nums, list, tmpList);
            
            tmpList.remove(tmpList.size()-1);
        }
    }
}
