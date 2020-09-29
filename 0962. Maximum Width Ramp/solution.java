/**
 * Official solution of Sort
 *
 * Intuition and Algorithm
 * 
 * For all elements like A[i] = v, let's write the indices i in sorted order of their values v.
 * For example with A[0] = 7, A[1] = 2, A[2] = 5, A[3] = 4, we can write the order of indices
 * i=1, i=3, i=2, i=0.
 * 
 * Then, whenever we write an index idx, we know there was a ramp of width idx - min(indexes_previously_written)
 * (if this quantity is positive). We can keep track of the minimum of all indexes previously written as prevMinIdx.
 *
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(N), depending on the implementation of the sorting function.
 */
class Solution {
    public int maxWidthRamp(int[] A) {
        if (A == null || A.length == 0) return 0;
        Integer[] idxArr = new Integer[A.length];
        for (int i = 0; i < idxArr.length; i++)
            idxArr[i] = i;
        
        Arrays.sort(idxArr, (a, b) -> ((Integer)A[a]).compareTo(A[b]));
        
        int res = 0, prevMinIdx = A.length;
        for (int idx: idxArr) {
            res = Math.max(res, idx - prevMinIdx);
            prevMinIdx = Math.min(prevMinIdx, idx);
        }
        return res;
    }
}


/**
 * Official solution of Binary Search Candidates
 *
 * Intuition
 * 
 * Consider i in decreasing order. We want to find the largest j with A[j] >= A[i]
 * if it exists.
 * 
 * Thus, the candidates for j are decreasing: if there is j1 < j2 and A[j1] <= A[j2]
 * then we strictly prefer j2.
 * 
 * Algorithm
 * 
 * Let's keep a list of these candidates j. For example, with A = [0,8,2,7,5], the
 * candidates for i = 0 would be candidates = [(v=5, i=4), (v=7, i=3), (v=8, i=1)].
 * We keep the list of candidates in decreasing order of i and increasing order of v.
 * 
 * Now we can binary search to find the largest j with A[j] >= A[i]: it's the first
 * one in this list of candidates with v >= A[i].
 *
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(N).
 */
import java.awt.Point;

class Solution {
    public int maxWidthRamp(int[] A) {
        int N = A.length;

        int ans = 0;
        List<Point> candidates = new ArrayList();
        candidates.add(new Point(A[N-1], N-1));

        // candidates: i's decreasing, by increasing value of A[i]
        for (int i = N-2; i >= 0; --i) {
            // Find largest j in candidates with A[j] >= A[i]
            int lo = 0, hi = candidates.size();
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (candidates.get(mi).x < A[i])
                    lo = mi + 1;
                else
                    hi = mi;
            }

            if (lo < candidates.size()) {
                int j = candidates.get(lo).y;
                ans = Math.max(ans, j - i);
            } else {
                candidates.add(new Point(A[i], i));
            }
        }
        return ans;
    }
}


/**
 * Other's solution of Binary Search with Stack
 *
 * Intuition:
 * Keep a decreasing stack.
 * For each number,
 * binary search the first smaller number in the stack.
 * 
 * When the number is smaller the the last,
 * push it into the stack.
 * 
 * Time Complexity: O(NlogN)
 */
class Solution {
    public int maxWidthRamp(int[] A) {
        // stack of ascending index with which the array element values are descending
        List<Integer> stack = new ArrayList<>();
        int res = 0, n = A.length;
        for (int i = 0; i < n; i++) {
            if (stack.size() == 0 || A[i] < A[stack.get(stack.size() - 1)]) {
                stack.add(i); // add index into stack when it can keep the descending element value
            } else { // otherwise search the previous minimum index with which the element value is smaller than current element
                int left = 0, right = stack.size() - 1, mid = 0;
                while (left < right) {
                    mid = left + (right - left) / 2;
                    if (A[stack.get(mid)] > A[i]) {
                        left = mid + 1;
                    } else {
                        right = mid; // index in the stack is ascending
                    }
                }
                res = Math.max(res, i - stack.get(left));
            }
        }
        return res;
    }
}


/**
 * Other's optimized solution of Binary Search with Stack
 *
 * Still one pass and keep a decraesing stack.
 * 
 * Time Complexity: O(N)
 */
class Solution {
    public int maxWidthRamp(int[] A) {
        Stack<Integer> s = new Stack<>();
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i)
            if (s.empty() || A[s.peek()] > A[i])
                s.add(i);
        for (int i = n - 1; i > res; --i)
            while (!s.empty() && A[s.peek()] <= A[i])
                res = Math.max(res, i - s.pop());
        return res;
    }
}
