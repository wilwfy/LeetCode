/**
 * My solution of Binary Search
 *
 * Time: O(logN). In worst case (all elements in array are as same as the target value), it is O(N).
 * Space: O(1)
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target)
                hi = mid;
            else if (nums[mid] < target)
                lo = mid + 1;
            else {
                lo = mid;
                hi = mid;
                break;
            }
        }
        while ((lo >= 0) && (nums[lo] == target)) {
            res[0] = lo;
            lo--;
        }
        while ((hi < nums.length) && (nums[hi] == target)) {
            res[1] = hi;
            hi++;
        }
        return res;
    }
}


/**
 * Other's solution of two rounds of Binary Search
 *
 * Time: O(logN)
 * Space: O(1)
 */
public class Solution {
    public  int[] searchRange(int[] nums, int target) {
        int hi = nums.length - 1;
        int low = 0;
        int[] rs = new int[2];

        // base case
        if(nums == null || nums.length == 0)
        	return new int[]{-1, -1 };
        
        // left side. First round of binary search
        while(low < hi){
        	int mid = low + (hi - low) /2;
        	if(target > nums[mid]){
        		low = mid + 1;
        	}else{
        		hi = mid;
        	}
        }
        if(target == nums[low]){
    		rs[0] = low;
        }else{
        	rs[0] = -1;
			rs[1] = -1;
			return rs; // not find target, no need to run Binary Search again
        }
       
        // right side. Second round of binary search
        hi = nums.length - 1;
        while(low < hi){
        	int mid = (low + (hi - low) /2 ) + 1;
        	if(target < nums[mid]){
        		hi = mid - 1;
        	}else{
        		low = mid;
        	}
        }   
        if(target == nums[hi]){
    		rs[1] = hi;
        }else{
        	rs[1] = -1;
        }
        
        return rs;
    }
}
