/**
 * Solution based on threeSum()
 *
 * Time Complexity:  O(N^3)
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || (nums[i] != nums[i-1])) {
                int left = i+1, right = nums.length - 1, target1 = target - nums[i];
                threeSum(nums, left, right, target1, nums[i], res);
            }
        }
        return res;
    }
    
    public void threeSum(int[] nums, int left, int right,
                         int target, int first, List<List<Integer>> res) {
        //System.out.println("left = " + left + ", target = " + target + ", first = "+ first);
        for (int i = left; i < nums.length - 2; i++) {
            if (i == left || (nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length - 1, sum = target - nums[i];
                //if (nums[i] > target) break;
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(first, nums[i], nums[lo], nums[hi]));
                        while (lo < hi && (nums[lo+1] == nums[lo])) lo++;
                        while (lo < hi && (nums[hi-1] == nums[hi])) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
    }
}
