/**
 * Other's solution of One Pass
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger
        // than the samll and big both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= small)
                small = num; // update small if num is smaller than both
            else if (num <= big)
                big = num; // update big only if num is greater than small but smaller than big
            else
                return true; // return if you find a number bigger than both
        }
        return false;
    }
}
