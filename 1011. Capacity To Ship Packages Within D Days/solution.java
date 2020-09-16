/**
 * Other's solution of Binary Search
 *
 * Explanation
 * Given the number of bags,
 * return the minimum capacity of each bag,
 * so that we can put items one by one into all bags.
 * 
 * We binary search the final result.
 * The left bound is max(A),
 * The right bound is sum(A).
 *
 * Complexity
 * Time O(N*log(Sum(weights) - Max(weights))
 * Space O(1)
 */
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        // Since 1 <= D <= weights.length <= 50000, 
        // We set the left as necessary lowest load for the ship and the right as highest load
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2; // mid is the load capacity of the ship
            int days = 1, cur = 0; // Default day needed is 1, and current accumulated weight to load is cur
            for (int w: weights) {
                if (cur + w > mid) {
                    days++; // Once accumulated weight + current w > ship load capacity, leave this w for next day
                    cur = 0; // And current accumulated weight is shipped away, so reset cur to 0
                }
                cur += w;
            }
            if (days > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
