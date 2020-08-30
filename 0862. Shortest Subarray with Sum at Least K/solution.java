/**
 * Official solution of Sliding Window with Deque
 *
 * Intuition
 * 
 * We can rephrase this as a problem about the prefix sums of A. Let preSum[i] = A[0] + A[1] + ... + A[i-1].
 * We want the smallest y-x such that y > x and preSum[y] - preSum[x] >= K.
 * 
 * Motivated by that equation, let opt(y) be the largest x such that preSum[x] <= preSum[y] - K. We need two
 * key observations:
 * 
 * If x1 < x2 and preSum[x2] <= preSum[x1], then opt(y) can never be x1, as if preSum[x1] <= preSum[y] - K,
 * then preSum[x2] <= preSum[x1] <= preSum[y] - K but y - x2 is smaller. This implies that our candidates x
 * for opt(y) will have increasing values of preSum[x].
 * 
 * If opt(y1) = x, then we do not need to consider this x again. For if we find some y2 > y1 with opt(y2) = x,
 * then it represents an answer of y2 - x which is worse (larger) than y1 - x.
 * 
 * 
 * Algorithm
 * 
 * Maintain a "monoqueue" of indices of preSum:
 * a deque of indices x_0, x_1, ... such that preSum[x_0], preSum[x_1], ... is increasing.
 * 
 * When adding a new index y, we'll pop x_i from the end of the deque so that
 * preSum[x_0], preSum[x_1], ..., preSum[y] will be increasing.
 * 
 * If preSum[y] >= preSum[x_0] + K, then (as previously described), we don't need to consider
 * this x_0 again, and we can pop it from the front of the deque.
 *
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(N).
 */
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] preSum = new long[N+1];
        for (int i = 0; i < N; i++)
            preSum[i + 1] = preSum[i] + (long) A[i];
        
        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N + 1; // N + 1 is impossible
        Deque<Integer> monoq = new LinkedList<>(); // opt(y) candidates, as indices of preSum
        // Want opt(y) = largest x with preSum[x] <= preSum[y] - K;
        for (int y = 0; y < preSum.length; y++) {
            // If the preSum is descending, we just need keep the minimum one
            while (!monoq.isEmpty() && preSum[y] <= preSum[monoq.getLast()])
                monoq.removeLast();
            // If the preSum is ascending, we check if there is an answer desired
            while (!monoq.isEmpty() && preSum[y] >= preSum[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());
            
            monoq.addLast(y);
        }
        
        return ans < N + 1 ? ans : -1;
    }
}


/**
 * Other's solution of Sliding Window with Deque
 *
 * Prepare
 * 209. Minimum Size Subarray Sum
 * 
 * Explanation
 * Calculate prefix sum B of list A.
 * B[j] - B[i] represents the sum of subarray A[i] ~ A[j-1]
 * Deque d will keep indexes of increasing B[i].
 * For every B[i], we will compare B[i] - B[d[0]] with K.
 * 
 * 
 * Complexity:
 * Every index will be pushed exactly once.
 * Every index will be popped at most once.
 * 
 * Time O(N)
 * Space O(N)
 * 
 * 
 * How to think of such solutions?
 * Basic idea, for array starting at every A[i], find the shortest one with sum at leat K.
 * In my solution, for B[i], find the smallest j that B[j] - B[i] >= K.
 * Keep this in mind for understanding two while loops.
 * 
 * 
 * What is the purpose of first while loop?
 * For the current prefix sum B[i], it covers all subarray ending at A[i-1].
 * We want know if there is a subarray, which starts from an index, ends at A[i-1] and has at least sum K.
 * So we start to compare B[i] with the smallest prefix sum in our deque, which is B[D[0]], hoping that [i] - B[d[0]] >= K.
 * So if B[i] - B[d[0]] >= K, we can update our result res = min(res, i - d.popleft()).
 * The while loop helps compare one by one, until this condition isn't valid anymore.
 * 
 * 
 * Why we pop left in the first while loop?
 * This the most tricky part that improve my solution to get only O(N).
 * D[0] exists in our deque, it means that before B[i], we didn't find a subarray whose sum at least K.
 * B[i] is the first prefix sum that valid this condition.
 * In other words, A[D[0]] ~ A[i-1] is the shortest subarray starting at A[D[0]] with sum at least K.
 * We have already find it for A[D[0]] and it can't be shorter, so we can drop it from our deque.
 * 
 * 
 * What is the purpose of second while loop?
 * To keep B[D[i]] increasing in the deque.
 * 
 * 
 * Why keep the deque increase?
 * If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
 * B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.
 */
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && B[i] <= B[d.getLast()])
                d.pollLast();
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
}
