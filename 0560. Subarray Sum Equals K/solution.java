/**
 * My solution
 *
 * Time complexity : O(n^2). We need to consider every subarray possible.
 * Space complexity : O(1). Constant space is used.
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }
}

/**
 * Official's solution: Using HashMap
 *
 * Time complexity : O(n). The entire numsnums array is traversed only once.
 * Space complexity : O(n). Hashmap mapmap can contain upto nn distinct entries in the worst case.
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int cnt = 0;
        int sum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1); // Initialize the map of sum
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cnt += sumMap.getOrDefault(sum - k, 0);
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
