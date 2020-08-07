/**
 * My solution based on Counting Sort with extra space
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] cnt = new int[nums.length + 1];
        for (int num: nums) {
            cnt[num]++;
        }
        for (int i = 1; i < cnt.length; i++) {
            if (cnt[i] == 0)
                res.add(i);
        }
        return res;
    }
}


/**
 * My solution without extra space
 *
 * Time: O(n)
 * Space: O(1). Worst case O(n) for the length of result list if all numbers are one same value.
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // Get a number x, then change the element at index x-1 to be negative
        for (int num: nums) {
            int index = Math.abs(num) - 1;
            nums[index] = nums[index] > 0 ? -nums[index] : nums[index];
        }
        // If the value of the element at index i is still negative,
        // then i+1 is the missed number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res.add(i+1);
        }
        return res;
    }
}
