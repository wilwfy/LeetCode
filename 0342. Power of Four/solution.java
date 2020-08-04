/**
 * My solution with loop
 *
 * Time: O(log4(n))
 * Space: O(1)
 */
class Solution {
    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        while (num > 1) {
            if ((num & 3) != 0) return false;
            num /= 4;
        }
        return true;
    }
}


/**
 * Other's solution without loop
 *
 * Time: O(1)
 * Space: O(1)
 */
class Solution {
    public boolean isPowerOfFour(int num) {
        return (num > 0 && ((num & num-1) == 0) && ((num & 0x55555555) == num));
    }
}
