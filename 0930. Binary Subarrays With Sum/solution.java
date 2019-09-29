/**
 * Official solution of using Prefix Sums and HashMap
 *
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(N).
 */
class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        if ((A == null) || (A.length == 0)) return 0;
        int cnt = 0;
        int[] preSum = new int[A.length+1];
        for (int i = 0, j = 0; i < A.length; i++) {
            preSum[i+1] = preSum[i] + A[i];
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int sum: preSum) {
            cnt += map.getOrDefault(sum, 0);
            map.put(sum+S, map.getOrDefault(sum+S, 0) + 1);
        }
        return cnt;
    }
}
