/**
 * Other's solution of Column Operation
 *
 * Intuition:
 * For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set. Then, if there are n integers in the array and k of them
 * have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.
 *
 * Time: O(32n) = O(n)
 * Space: O(1)
 */
class Solution {
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int num: nums) {
                bitCount += (num >> i) % 2;
            }
            total += bitCount * (n - bitCount);
        }
        return total;
    }
}
