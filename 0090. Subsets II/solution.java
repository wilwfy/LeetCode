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


/**
 * Other's solution without Backtracking
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        
        Arrays.sort(nums);
        
        res.add(new ArrayList<Integer>());
        
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            // If we want to insert an element which is a dup, we can only
            // insert it after the newly inserted elements from last step.
            int start = (i > 0 && nums[i] == nums[i-1]) ? size : 0;
            size = res.size();
            //System.out.println("size = " + size);
            for (int j = start; j < size; j++) {
                List<Integer> subset = new ArrayList<Integer>(res.get(j));
                subset.add(nums[i]);
                res.add(subset);
            }
        }
        return res;
    }
}
