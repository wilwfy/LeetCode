/**
 * Other's solution1 based on pivot element
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        if ((nums == null) || (nums.length == 0)) return -1;
		
        int lo = 0, hi = nums.length-1;
		// search the index of minimum value
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi])
                lo = mid + 1;
            else
                hi = mid;
        }
        int pivot = lo; // now got the index of minimum value
        
        // set the search range based on pivot
        int start = target > nums[nums.length-1] ? 0 : pivot;
        int end = target > nums[nums.length-1] ? pivot : nums.length-1;
        while (start <= end) {
            //System.out.println("start = " + start + ", end = " + end);
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
}


/**
 * Other's solution2 based on pivot element
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int search(int A[], int n, int target) {
        int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        lo=0;hi=n-1;
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)return realmid;
            if(A[realmid]<target)lo=mid+1;
            else hi=mid-1;
        }
        return -1;
    }
}


/**
 * Other's solution3
 *
 * The idea is that when rotating the array, there must be one half of the array that is still in sorted order.
 * For example, 6 7 1 2 3 4 5, the order is disrupted from the point between 7 and 1. So when doing binary search,
 * we can make a judgement that which part is ordered and whether the target is in that range, if yes, continue the
 * search in that half, if not continue in the other half.
 */
public class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
        
            if (nums[start] <= nums[mid]){
                 if (target < nums[mid] && target >= nums[start]) 
                    end = mid - 1;
                 else
                    start = mid + 1;
            } 
        
            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                 else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
