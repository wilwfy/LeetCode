/**
 * My solution with extra space based on HashSet
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num))
                res.add(num);
            else
                set.add(num);
        }
        return res;
    }
}


/**
 * Other's solution without extra space
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        // when find a number x, flip the number at index x-1 to negative. 
        // if the number at position x-1 is already negative, x is the number that occurs twice.
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                res.add(index + 1); // index + 1 = Math.abs(nums[i])
            nums[index] = -nums[index];
        }
        return res;
    }
}
