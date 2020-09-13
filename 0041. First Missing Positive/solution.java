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


/**
 * Other's solution without Sort
 *
 * Algorithm
 * Put each number in its right place.
 * 
 * For example:
 * When we find 5, then swap it with A[4].
 * 
 * At last, the first place where its number is not right, return the place + 1.
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // Put each number in its right place.
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // swap nums[i] with nums[nums[i] - 1]
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[tmp - 1] = tmp;
            }
        }
        // Find the first place where its number is not right
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return n + 1;
    }
}
