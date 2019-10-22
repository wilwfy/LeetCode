/**
 * Other's solution
 *
 * Similar to 3 Sum problem, use 3 pointers to point current element, next element and the last element.
 * If the sum is less than target, it means we have to add a larger element so next element move to the next.
 * If the sum is greater, it means we have to add a smaller element so last element move to the second last
 * element. Keep doing this until the end. Each time compare the difference between sum and target, if it is
 * less than minimum difference so far, then replace result with it, otherwise keep iterating.
 *
 * Time Complexity: O(N^2)
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length-1];
        for (int i = 0; i < nums.length-2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target)
                    return target;
                else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
                if (Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;
            }
        }
        return res;
    }
}

/**
 * Optimized other's solution by skipping the duplicated numbers
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length-1];
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (nums[i] != nums[i-1])) {
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if (sum == target)
                        return target;
                    else if (sum < target) {
                        while (lo < hi && (nums[lo] == nums[lo+1])) lo++;
                        lo++;
                    } else {
                        while (lo < hi && (nums[hi] == nums[hi-1])) hi--;
                        hi--;
                    }
                    if (Math.abs(sum - target) < Math.abs(res - target))
                        res = sum;
                }
            }
        }
        return res;
    }
}
