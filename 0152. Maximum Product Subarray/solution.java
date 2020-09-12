/**
 * Solution by refering to other's O(n) solution
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int maxProduct(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        
        int globalMax = nums[0];
        
        // need track the minimum value because multiplying a negative number
        // could make the positive max value to be min or the negative
        // min value to be max
        int localMax = 1, localMin = 1;
        for (int num: nums) {
            // negative could cause localMax and localMin swap
            int tmpMax = Math.max(localMax * num, localMin * num);
            int tmpMin = Math.min(localMax * num, localMin * num);
            
            localMax = Math.max(num, tmpMax);
            localMin = Math.min(num, tmpMin);
            
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }
}


/**
 * Other's O(n) solution1
 */
class Solution {
    public int maxProduct(int A[], int n) {
	    // store the result that is the max we have found so far
        int r = A[0];
	    
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < n; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0)
                swap(imax, imin);
	    
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = max(A[i], imax * A[i]);
            imin = min(A[i], imin * A[i]);
	    
            // the newly computed max value is a candidate for our global result
            r = max(r, imax);
        }
        return r;
    }
}


/**
 * Other's O(n) solution2
 */
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;    // global max
        int maxloc = 1, minloc = 1;     // max or min end here
        for (int num : nums) {          // negative could cause maxloc and minloc swap
            int prod1 = maxloc * num, prod2 = minloc * num;
            maxloc = Math.max(num, Math.max(prod1, prod2));
            minloc = Math.min(num, Math.min(prod1, prod2));
            max = Math.max(max, maxloc);
        }
        return max;
    }
}


/**
 * Other's solution with Prefix and Suffix Product
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int maxProduct(int[] nums) {
        // Calculate prefix product in nums.
        // Calculate suffix product in nums.
        // Return the max.
        int n = nums.length, res = nums[0], l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l = (l == 0 ? 1 : l) * nums[i];
            r = (r == 0 ? 1 : r) * nums[n - 1 - i];
            res = Math.max(res, Math.max(l, r));
        }
        return res;
    }
}
