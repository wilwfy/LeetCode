class Solution {
    public void rotate(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return;
        int n = nums.length;
        int shift = (n - k >= 0) ? k : k % n;
        if (shift == 0) return;
        
        int[] tmp = new int[n];
        for (int i = 0; i < shift; i++) {
            tmp[i] = nums[n-shift+i];
        }
        for (int i = shift; i < n; i++) {
            tmp[i] = nums[i-shift];
        }
        
        System.arraycopy(tmp, 0, nums, 0, n);
        return;
    }
}
