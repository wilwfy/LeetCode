/**
 * My solution of Sorting
 *
 * Time: O(nlogn)
 * Space: O(1)
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        Arrays.sort(nums);
        
        int res = 1;
        if (nums[0] > 1)
            return 1;
        else if (nums[0] == 1)
            res++;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (nums[i] == res)
                    res++;
                else if (nums[i] == nums[i-1])
                    continue;
                else
                    return res;
            }
        }
        return res;
    }
}
