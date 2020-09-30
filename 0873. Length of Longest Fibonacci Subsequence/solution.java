/**
 * Official solution of Brute Force with Set
 *
 * Intuition
 * 
 * Every Fibonacci-like subsequence has each two adjacent terms determine the next expected term.
 * For example, with 2, 5, we expect that the sequence must continue 7, 12, 19, 31, etc.
 * 
 * We can use a Set structure to determine quickly whether the next term is in the array A or not.
 * Because of the exponential growth of these terms, there are at most 43 terms in any Fibonacci-like
 * subsequence that has maximum value ≤10^9.
 * 
 * Algorithm
 * 
 * For each starting pair A[i], A[j], we maintain the next expected value y = A[i] + A[j] and the
 * previously seen largest value x = A[j]. If y is in the array, then we can then update these
 * values (x, y) -> (y, x+y).
 * 
 * Also, because subsequences are only fibonacci-like if they have length 3 or more, we must perform
 * the check ans >= 3 ? ans : 0 at the end.
 * 
 * 
 * Time Complexity: O(N^2 * logM), where N is the length of A, and M is the maximum value of A.
 * Space Complexity: O(N), the space used by the set S.
 */
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x: A) set.add(x);
        int res = 2;
        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                int a = A[i], b = A[j], len = 2;
                while (set.contains(a + b)) {
                    b = a + b;
                    a = b - a; // now a is previous b
                    len++;
                }
                res = Math.max(res, len);
            }
        }
        return res > 2 ? res : 0;
    }
}


/**
 * Official solution of Dynamic Programming
 *
 * Intuition
 * 
 * Think of two consecutive terms A[i], A[j] in a fibonacci-like subsequence as a single node (i, j),
 * and the entire subsequence is a path between these consecutive nodes. For example, with the
 * fibonacci-like subsequence (A[1] = 2, A[2] = 3, A[4] = 5, A[7] = 8, A[10] = 13), we have the path
 * between nodes (1, 2) <-> (2, 4) <-> (4, 7) <-> (7, 10).
 * 
 * The motivation for this is that two nodes (i, j) and (j, k) are connected if and only if A[i] + A[j] == A[k],
 * and we needed this amount of information to know about this connection. Now we have a problem similar to
 * Longest Increasing Subsequence.
 * 
 * Algorithm
 * 
 * Let longest[i, j] be the longest path ending in [i, j]. Then longest[j, k] = longest[i, j] + 1 if (i, j) and
 * (j, k) are connected. Since i is uniquely determined as A.index(A[k] - A[j]), this is efficient: we check for
 * each j < k what i is potentially, and update longest[j, k] accordingly.
 *
 * Time Complexity: O(N^2), where N is the length of A.
 * Space Complexity: O(NlogM), where M is the largest element of A. We can show that the number
 *                   of elements in a subsequence is bounded by O(log(M/a)) where a is the minimum
 *                   element in the subsequence.
 */
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;

        for (int k = 0; k < N; ++k)
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // Encoding tuple (i, j) as integer (i * N + j)
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }

        return ans >= 3 ? ans : 0;
    }
}
