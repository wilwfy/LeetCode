/**
 * Solution of Backtracking, but Time Limit Exceeded
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        if (nums[0] > target) return 0;
        
        //int res = 0;
        //res = backtrack(nums, target);
        //return res;
        return backtrack(nums, target);
    }
    
    public int backtrack(int[] nums, int target) {
        int count = 0;
        if (target < 0)
            return 0;
        else if (target == 0) {
            return 1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] < 0) break;
                
                count += backtrack(nums, target - nums[i]);
            }
            return count;
        }
    }
}


/**
 * Recursive Solution, but Time Limit Exceeded
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int count = 0;
        if (target < 0)
            return 0;
        else if (target == 0) {
            return 1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] < 0) break;
                
                count += combinationSum4(nums, target - nums[i]);
            }
        }
        return count;
    }
}
