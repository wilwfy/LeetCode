/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

class NumArray {
    int[] numbers;
    int len;
    int[] mem;
    public NumArray(int[] nums) {
        numbers = nums;
        len = nums.length;
        mem = new int[len];
        // mem[i] is the sum from nums[0] to nums[i]
        if (len > 0) {
            mem[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                mem[i] = mem[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        if ((i >= 0) && (i < len) && (j >= 0) && (j < len)) {
            return mem[j] - mem[i] + numbers[i];
        } else
            return 0;
    }
}


/**
 * Official solution: Caching
 *
 * Time complexity : O(1) time per query, O(n) time pre-computation. Since the
 * cumulative sum is cached, each sumRange query can be calculated in O(1) time.
 * Space complexity : O(n).
 */
class NumArray {
    private int[] sum;
    
    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
