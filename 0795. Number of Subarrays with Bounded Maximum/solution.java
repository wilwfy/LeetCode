/**
 * Other's solution of Greedy with Two Pointers
 *
 */
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int j = 0, count = 0, res = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= left && nums[i] <= right){
                res += i - j + 1;
                count = i - j + 1;
            } else if(nums[i] < left){
                res += count;
            } else {
                j = i + 1;
                count = 0;
            }
        }
        return res;
    }
}
