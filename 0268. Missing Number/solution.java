/*
 * Official solution of Sorting
 *
 * Time complexity : O(nlgn)
 *                   The only elements of the algorithm that have asymptotically nonconstant time complexity are the main for loop
 *                   (which runs in O(n) time), and the sort invocation (which runs in O(nlgn) time for Python and Java). Therefore,
 *                   the runtime is dominated by sort, and the entire runtime is O(nlgn).
 * Space complexity : O(1) (or O(n))
 *                    In the sample code, we sorted nums in place, allowing us to avoid allocating additional space. If modifying nums
 *                    is forbidden, we can allocate an O(n) size copy and sort that instead.
 */
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        // Ensure that n is at the last index
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        // Ensure that 0 is at the first index
        else if (nums[0] != 0) {
            return 0;
        }

        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i-1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // Array was not missing any numbers
        return -1;
    }
}


/*
 * Official solution of HashSet
 *
 * Time complexity : O(n)
 *                   Because the set allows for O(1) containment queries, the main loop runs in O(n) time. Creating 
 *                   num_set costs O(n) time, as each set insertion runs in amortized O(1) time, so the overall runtime is O(n+n)=O(n).
 * Space complexity : O(n)
 *                    nums contains n−1 distinct elements, so it costs O(n) space to store a set containing all of them.
 */
 class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
}
 
 
 /*
 * Official solution of Bit Manipulation
 *
 * missing = 4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
 *         = (4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
 *         = 0∧0∧0∧0∧2
 *         = 2
 *
 * Time complexity : O(n)
 *                   Assuming that XOR is a constant-time operation, this algorithm does constant work on n iterations, so the runtime
 *                   is overall linear.
 * Space complexity : O(1)
 *                    This algorithm allocates only constant additional space.
 */
 class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
 
 
 
 /*
 * Official solution of Gauss' Formula
 *
 * Time complexity : O(n)
 * Space complexity : O(1)
 */
 class Solution {
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}
