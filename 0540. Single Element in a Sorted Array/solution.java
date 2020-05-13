/**
 * My solution of One Pass
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = 0, sign = -1;
        for (int num: nums) {
            res += sign * num;
            sign *= -1;
        }
        return Math.abs(res);
    }
}


/**
 * Other's solution of Binary Search
 *
 * intuition:
 * The basic idea here is that there's only one element that appears once. Suppose a series of number that all the elements appear
 * twice, then elements always change at even positions. If one element only appears once, then the rule will be broken and we can
 * use binary search based on this rule.
 *
 * Time: O(log(n))
 * Space: O(1)
 */
class Solution {
   public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            // Example:
            // Index: 0 1 2 3 4 5 6
            // Array: 1 1 3 3 4 8 8
            //            ^
            int mid = (start + end) / 2;
            if (mid % 2 == 1) mid--;

            // We didn't find a pair. The single element must be on the left.
            // (pipes mean start & end)
            // Example: |0 1 1 3 3 6 6|
            //               ^ ^
            // Next:    |0 1 1|3 3 6 6
            if (nums[mid] != nums[mid + 1]) end = mid;

            // We found a pair. The single element must be on the right.
            // Example: |1 1 3 3 5 6 6|
            //               ^ ^
            // Next:     1 1 3 3|5 6 6|
            else start = mid + 2;
        }

        // 'start' should always be at the beginning of a pair.
        // When 'start > end', start must be the single element.
        return nums[start];
    }
}


/**
 * Other's solution of Binary Search
 *
 * Time: O(log(n))
 * Space: O(1)
 */
class Solution {
	public int singleNonDuplicate(int[] nums) {
		// corner case
		if(nums == null || nums.length == 0) return -1;
		
		int lo = 0;
		int hi = nums.length - 1;
		while(lo < hi){
			int mid = lo + (hi - lo)/2;
			// trick here
			// int temp = mid % 2 == 0 ? mid + 1: mid - 1;
			int temp = mid ^ 1; // if even, mid + 1; if odd, mid - 1
			if(nums[mid] == nums[temp]){
				// if mid is even, then nums[mid] = nums[mid + 1], single number is >= mid + 2
				// if mid is odd, then nums[mid] = nums[mid - 1], single number is >= mid + 1
				// so we choose mid + 1
				lo = mid + 1;
			}else{
				// maybe nums[hi] is the single numer or
				// maybe the single number is to the left of nums[hi]
				// <= hi
				hi = mid; 
			}
		}
		
		return nums[lo];
	}
}
