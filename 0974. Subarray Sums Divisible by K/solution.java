/**
 * Other's solution of using HashMap.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(K)
 */
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if (K == 0) return 0;
        int cnt = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            sum %= K;
            if (sum < 0) sum += K;
            cnt += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
