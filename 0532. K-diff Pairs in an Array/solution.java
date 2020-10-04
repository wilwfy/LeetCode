/**
 * My solution of Brute Force with Sort (accepted)
 *
 * Time: O(N^2)
 * Space: O(1)
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) return 0;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    res++; // since the array is sorted, so the found pair is unique and
                    break; // enough in this loop, so no need to search further
                }
            }
        }
        return res;
    }
}



/**
 * Other's solution with Hash Map
 *
 * Explanation
 * Count the elements with Counter
 * If k > 0, for each element i, check if i + k exist.
 * If k == 0, for each element i, check if count[i] > 1
 * 
 * 
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int res = 0;
        for (int x : cnt.keySet()) {
            if ((k > 0 && cnt.containsKey(x + k)) || (k == 0 && cnt.get(x) > 1)) {
                res++;
            }
        }
        return res;
    }
}


/**
 * Other's solution of One Pass with Hash Map
 *
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i : nums) {
            if (map.containsKey(i)) {
                if (k == 0 && map.get(i) == 1) {
                    result++;
                }
                map.put(i, map.get(i) + 1);
            } else {
                if (map.containsKey(i - k) || map.containsKey(i + k)) {
                    result++;
                }
                map.put(i, 1);
            }
        }
        return result;
    }
}
