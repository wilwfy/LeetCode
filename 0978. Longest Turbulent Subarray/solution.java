/**
 * Official solution of Sliding Window
 *
 * Intuition
 * 
 * Evidently, we only care about the comparisons between adjacent elements. If the comparisons are represented
 * by -1, 0, 1 (for <, =, >), then we want the longest sequence of alternating 1, -1, 1, -1, ... (starting with
 * either 1 or -1).
 * 
 * These alternating comparisons form contiguous blocks. We know when the next block ends: when it is the last
 * two elements being compared, or when the sequence isn't alternating.
 * 
 * For example, take an array like A = [9,4,2,10,7,8,8,1,9]. The comparisons are [1,1,-1,1,-1,0,-1,1]. The blocks
 * are [1], [1,-1,1,-1], [0], [-1,1].
 * 
 * Algorithm
 * 
 * Scan the array from left to right. If we are at the end of a block (last elements OR it stopped alternating),
 * then we should record the length of that block as our candidate answer, and set the start of the new block as
 * the next element.
 *
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(1).
 */
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int N = arr.length;
        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < N; ++i) {
            int c = Integer.compare(arr[i-1], arr[i]);
            if (c == 0) {
                anchor = i;
            } else if (i == N-1 || c * Integer.compare(arr[i], arr[i+1]) != -1) {
                ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }

        return ans;
    }
}
