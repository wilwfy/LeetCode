/**
 * The critical point of this problem is:
 *      it could also be possible that two negative numbers lying at the left extreme end could also contribute to lead to a larger
 *      product if the third number in the triplet being considered is the largest positive number in the nums array.
 *      Thus, either the product (nums[0] × nums[1] × nums[n−1]) or (nums[n−3] × nums[n−2] × nums[n−1]) will give the required result.
 *      Thus, we need to find the larger one from out of these values.
 */

/**
 * Official solution by using Sorting
 *
 * Time complexity : O(nlogn). Sorting the nums array takes nlogn time.
 * Space complexity : O(logn). Sorting takes O(logn) space.
 */
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1]*nums[nums.length-1], nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
    }
}


/**
 * Official solution of single scan
 *
 * Time complexity : O(n). Only one iteration over the nums array of length n is required.
 * Space complexity : O(1). Constant extra space is used.
 */
class Solution {
    public int maximumProduct(int[] nums) {
        // we need the most two minimum elements and the most three maximum elements for the result.
        // Initialize these values
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        
        // the value order is:
        // min1 <= min2 ... < max3 <= max2 <= max1
        for (int num: nums) {
            // Update the minimum values
            if (num <= min1) {
                // n is smaller than min1 and min2
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                // n lies between min1 and min2
                min2 = num;
            }
            
            // Update the maximum values
            if (num >= max1) {
                // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                // n lies betweeen max1 and max2
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                // n lies betwen max2 and max3
                max3 = num;
            }
        }
        return Math.max(min1*min2*max1, max3*max2*max1);
    }
}
