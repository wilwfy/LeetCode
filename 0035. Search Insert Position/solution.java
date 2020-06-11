/**
 * My solution of Binary Search
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target < nums[0]) return 0;
        if (target > nums[nums.length-1]) return nums.length;
        
        int low = 0, high = nums.length-1;
        int mid = low + (high - low) / 2;
        while (low < high) {
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                high = mid;
            else
                low = mid + 1;
            
            mid = low + (high - low) / 2;
        }
        return low;
    }
}


/**
 * Other's solution of Binary Search
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length;
        while(low < high) {
        	int mid = low + (high-low)/2;
        	if (nums[mid] == target) return mid;
        	if (nums[mid] > target) high = mid;
        	else low = mid + 1;
        }
        return low;
    }
}
