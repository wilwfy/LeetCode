/**
 * Other's solution of One Pass
 *
 * Intuition:
 * Supposed that we have known how to solve max subarray sum (without circular): 53. Maximum Subarray.
 * So there are two case:
 * The first is that the subarray take only a middle part, and we know how to find the max subarray sum.
 * The second is that the subarray take a part of head array and a part of tail array.
 * We can transfer this case to the first one.
 * The maximum result equals to the total sum minus the minimum subarray sum.
 *
 * According to the diagram of two_cases_of_max_subarray.png:
 * So the max subarray circular sum equals to:
 * max(the max subarray sum, the total sum - the min subarray sum)
 *
 * Corner case
 * Just one to pay attention:
 * If all numbers are negative, maxSum = max(A) and minSum = sum(A).
 * In this case, max(maxSum, total - minSum) = 0, which means the sum of an empty subarray.
 * According to the deacription, We need to return the max(A), instead of sum of am empty subarray.
 * So we return the maxSum to handle this corner case.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int maxSubarraySumCircular(int[] A) {      
        int total = 0, curMax = 0, maxSum = Integer.MIN_VALUE, curMin = 0, minSum = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            curMax = Math.max(A[i], curMax + A[i]);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(A[i], curMin + A[i]);
            minSum = Math.min(minSum, curMin);
            total += A[i];
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
