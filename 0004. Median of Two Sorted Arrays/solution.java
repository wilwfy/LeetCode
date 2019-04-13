/*
 * Merge with using three pointers
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] merge = new int[len/2 + 1];
        int i = 0, j = 0, k = 0;
        while ((i < nums1.length) && (j < nums2.length) && (k < len/2 + 1)) {
            if (nums1[i] <= nums2[j]) {
                merge[k] = nums1[i];
                i++;
            } else {
                merge[k] = nums2[j];
                j++;
            }
            k++;
        }
        if (i == nums1.length) {
            for (; k < len/2 + 1; k++, j++)
                merge[k] = nums2[j];
        } else {
            for (; k < len/2 + 1; k++, i++)
                merge[k] = nums1[i];
        }
        double res = (len%2 == 0) ? (merge[len/2 - 1] + merge[len/2])*0.5 : merge[len/2];
        return res;
    }
}


/*
 * Official solution based on Recursive Approach
 *
 * Time complexity: O(log(min(m,n))).
 *                  At first, the searching range is [0, m][0,m]. And the length of this
 *                  searching range will be reduced by half after each loop. So, we only
 *                  need log(m) loops. Since we do constant operations in each loop, so 
 *                  the time complexity is O(log(m)). Since m ≤ n, so the time complexity
 *                  is O(log(min(m,n))).
 * Space complexity: O(1).
 *                   We only need constant memory to store 9 local variables, so the space complexity is O(1)
 */
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}


/*
 * Other's recursive solution with time = O(log(m + n))
 *
 * The key point of this problem is to ignore half part of A and B each step recursively by
 * comparing the median of remaining A and B:
 *                                           if (aMid < bMid) Keep [aRight + bLeft]    
 *                                           else Keep [bRight + aLeft]
 */
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
    	    int m = A.length, n = B.length;
    	    int l = (m + n + 1) / 2;
    	    int r = (m + n + 2) / 2;
    	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    	}
    
    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
    	if (aStart > A.length - 1) return B[bStart + k - 1];            
    	if (bStart > B.length - 1) return A[aStart + k - 1];                
    	if (k == 1) return Math.min(A[aStart], B[bStart]);
    	
    	int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
    	if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
    	if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
    	
    	if (aMid < bMid) 
    	    return getkth(A, aStart + k/2, B, bStart,       k - k/2);// Check: aRight + bLeft 
    	else 
    	    return getkth(A, aStart,       B, bStart + k/2, k - k/2);// Check: bRight + aLeft
    }
}
