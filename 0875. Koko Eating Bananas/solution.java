/**
 * Other's solution of Binary Search
 *
 * Intuition
 * Each hour, Koko chooses some pile of bananas, and eats K bananas from that pile.
 * There is a limited range of K's to enable her to eat all the bananas within H hours.
 * We ought to reduce the searching space and to return the minimum valid K.
 * Binary Search is born for that.
 * Initially, we know that K belongs to [1, 1000000000] since 1 <= piles[i] <= 10^9. And
 * we follow the pattern of lower-bound Binary Search except that if (K == target) is
 * replaced with if (canEatAll(piles, K, H)).
 */
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1, hi = 1000000000; // 1 <= piles[i] <= 10^9
        // Binary search to find the smallest valid K.
        while (lo < hi) {
            int mid = (lo + hi) / 2; // no risk of overflow for lo + hi here
            if (!canEatAll(piles, H, mid))
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
    
    private boolean canEatAll(int[] piles, int H, int K)  {
        int hours = 0; // Hours take to eat all bananas at speed K
        for (int pile: piles) {
            hours += (pile - 1) / K + 1; // minus 1 for the case when pile % K == 0
        }
        return hours <= H;
    }
}


/**
 * Another solution of Binary Search
 *
 * Explanation
 * Binary search between [1, 10^9] or [1, max(piles)] to find the result.
 * Time complexity: O(NlogM)
 * 
 * (p + m - 1) / m equal to ceil(p / m) (just personal behavior)
 * 
 * Here you find another similar problem.
 * 774. Minimize Max Distance to Gas Station
 * 
 * Complexity
 * Time O(N*log(MaxP))
 * Space O(1)
 */
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int l = 1, r = 1000000000;
        while (l < r) {
            int m = (l + r) / 2, total = 0;
            for (int p : piles) total += (p + m - 1) / m;
            if (total > H) l = m + 1; else r = m;
        }
        return l;
    }
}
