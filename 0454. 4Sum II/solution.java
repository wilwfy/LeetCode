/**
 * Other's solution by replacing 4 loops by two 2 loops.
 *
 * Time Complexity: O(N^2)
 * Space Complexity: O(N^2)
 */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int a: A) {
            for (int b: B) {
                int sum1 = a + b;
                map.put(sum1, map.getOrDefault(sum1, 0) + 1);
            }
        }
        
        for (int c: C) {
            for (int d: D) {
                int sum2 = c + d;
                res += map.getOrDefault(-1 * sum2, 0);
            }
        }
        
        return res;
    }
}
