/**
 *
 * A very nice and detailed explanation and conclusion on this type of problem:
 *        'Summary of solutions for problems "reducible" to LeetCode 378' at link:
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378
 */


/**
 * Referring to other's solution of Priority Queue
 * 
 * Reference Post: https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115486/Java-AC-O(max(nk)-*-logn)-Short-Easy-PriorityQueue/116025
 */
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        
        // 0: numerator idx, 1: denominator idx
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
            (a, b) -> A[a[0]] * A[b[1]] - A[b[0]] * A[a[1]]
        );
        
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{i, n - 1});
        }
        
        for (int i = 0; i < K - 1; i++) {
            int[] fr = pq.poll();
            int nuIndex = fr[0], deIndex = fr[1];
            if (deIndex > nuIndex + 1) {
                fr[1]--;
                pq.offer(fr);
            }
        }
        
        return new int[]{A[pq.peek()[0]], A[pq.peek()[1]]};
    }
}
