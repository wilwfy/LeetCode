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

/**
 * Other's solution of using prefix sum and array
 *
 */
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if (K == 0) return 0;
        int cnt = 0, sum = 0;
        int[] remainders = new int[K];
        /*
         * It's a common technique in prefix sum. Since sum[i, j] = presum[j] - presum[i - 1], how to calculate sum[0, j]?
For current j, we need to know how many sum[i, j] is divisible by K. So i could be 0.
So in prefix sum, we add 0 ahead. For example, A = [1,2,3,4], presum=[0,1,3,6,10] And now we can culculate every sum[i, j] through the equation.
         */
        remainders[0] = 1;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            sum %= K;
            if (sum < 0) sum += K;
            cnt += remainders[sum];
            remainders[sum]++;
        }
        return cnt;
    }
}
