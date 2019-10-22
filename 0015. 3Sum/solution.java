/**
 * Other's solution
 *
 * The idea is to sort an input array and then run through all indices of a possible first element of a triplet.
 * For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array.
 * Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
 *
 * Time Complexity: O(N^2)
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length - 1, sum = 0 - nums[i];
                
                // Since the nums is sorted, if first number is bigger than 0, it is impossible to have a total sum of 0.
                if (nums[i] > 0) break;
                
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
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
        return res;
    }
}
