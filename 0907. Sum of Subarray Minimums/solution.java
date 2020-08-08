/**
 * Other's solution with Monotone Stack
 *
 * Explanation:
 *   https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
 *
 * Time: O(N). All elements will be pushed twice and popped at most twice
 * Space: O(N).
 */
class Solution {
    public int sumSubarrayMins(int[] A) {
        // in the array, first element is A[i], second element is the index i
        Stack<int[]> prevLess = new Stack<>();
        // The distance value equals to the number of possible subarray on the left of A[i]
        int[] leftDistance = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            // use ">=" to deal with duplicate elements
            while (!prevLess.isEmpty() && prevLess.peek()[0] >= A[i])
                prevLess.pop();
            // If there is no less value on the left, then there are i+1 possible subarrays on the left of A[i]
            leftDistance[i] = prevLess.isEmpty() ? i+1 : i - prevLess.peek()[1];
            prevLess.push(new int[]{A[i], i});
        }
        
        Stack<int[]> nextLess = new Stack<>();
        // The distance value equals to the number of possible subarray on the right of A[i]
        int[] rightDistance = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            while (!nextLess.isEmpty() && nextLess.peek()[0] > A[i])
                nextLess.pop();
            // If there is no less value on the right, then there are A.length-i possible subarrays on the right of A[i]
            rightDistance[i] = nextLess.isEmpty() ? A.length - i : nextLess.peek()[1] - i;
            nextLess.push(new int[]{A[i], i});
        }
        
        int sum = 0, mod = (int)1e9 + 7; // the data type of 1e9 is double
        for (int i = 0; i < A.length; i++)
            sum = (sum + A[i] * leftDistance[i] * rightDistance[i]) % mod;
        return sum;
    }
}


/**
 * Other's solution with Monotone Stack in One Pass
 *
 * Explanation:
 *   https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C%2B%2BJavaPython-Stack-Solution
 *
 * Improvement
 * 1. Here I record (A[i], count) in the stack.
 *    We can also only record index.
 * 2. For left part and right part, the logic is same.
 * So for each, I used one stack and one pass.
 * This process can be optimized to one pass using one stack in total.
 *
 * Time: O(N).
 * Space: O(N).
 */
class Solution {
    public int sumSubarrayMins(int[] A) {
        Stack<Integer> s = new Stack<>();
        int n = A.length, res = 0, mod = (int)1e9 + 7, j,k;
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && A[stack.peek()] > (i == n ? 0 : A[i])) {
                j = stack.pop();
                k = stack.isEmpty() ? -1 : stack.peek();
                res = (res + A[j] * (i - j) * (j - k)) % mod;
            }
            stack.push(i);
        }
        return res;
    }
}
