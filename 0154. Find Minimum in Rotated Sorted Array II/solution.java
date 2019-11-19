class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] < nums[end]) return nums[start];
            
            int mid = (start + end) / 2;
            
            if (nums[mid] > nums[start]) {
                start = mid + 1;
            } else if (nums[mid] < nums[start]) {
                end = mid;
            } else {
                // being here means nums[mid] == nums[start],
                // so just skip nums[start]
                start++;
            }
        }
        return nums[start];
    }
}
