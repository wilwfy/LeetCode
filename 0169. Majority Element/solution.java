/**
 * My solution with HashMap
 *
 * Time and Space: O(n)
 */
class Solution {
    public int majorityElement(int[] nums) {
        if (nums.length <= 2) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (; i < nums.length; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], 1);
            else {
                int cnt = map.get(nums[i]);
                if (cnt >= nums.length/2)
                    break;
                else
                    map.put(nums[i], cnt+1);
            }
        }
        return nums[i];
    }
}


/**
 * Official solution with Sorting
 *
 * Intuition
 * If the elements are sorted in monotonically increasing (or decreasing) order, the majority element can be found at 
 * index ⌊n/2⌋ (and ⌊n/2⌋+1, incidentally, if nn is even).
 *
 * Time Complexity: O(nlgn)
 * Space Complexity: O(1) or O(n). 
 *                   We sorted nums in place here - if that is not allowed, then we must spend linear additional space on a copy
 *                   of nums and sort the copy instead.
 */
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}


/**
 * Official solution with Boyer-Moore Voting Algorithm
 *
 * Intuition
 * If we had some way of counting instances of the majority element as +1 and instances of any other element as -1, summing them
 * would make it obvious that the majority element is indeed the majority element.
 *
 * Time complexity : O(n)
 *                   Boyer-Moore performs constant work exactly nn times, so the algorithm runs in linear time.
 * Space complexity : O(1)
 *                    Boyer-Moore allocates only constant additional memory.
 */
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
