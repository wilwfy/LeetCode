class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    public void update(int i, int val) {
        if ((i >= 0) && (i < nums.length))
            nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        if ((i > j) || (i < 0) || (i > nums.length) ||
            (j < 0) || (j > nums.length))
            return Integer.MIN_VALUE;
        int sum = 0;
        for (int k = i; k <= j; ++k)
            sum += nums[k];
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
