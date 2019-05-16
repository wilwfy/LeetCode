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
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
