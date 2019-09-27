/**
 * Other's solution by using HashMap
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * It is used for covering the case where the running sum(sum_i) is exactly equal to k(or n*k). (Recall that sum_i represents the running sum starting from index 0 and ending at i)
After the execution of runningSum %= k, the mod becomes 0, so we have to put a k-v pair with key = 0 to the map in advance to handle this case.
value = -1 is due to the fact that the size of the subarray is required to be least two.
         */
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            Integer prev = map.get(sum);
            if (prev != null) {
                if ((i - prev) > 1) return true;
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
