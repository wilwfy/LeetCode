/**
 * My solution of Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        int left = timeSeries[0], right = timeSeries[0] + duration - 1;
        int sum = 0, n = timeSeries.length - 1; // slide the window to the element before the last one
        for (int i = 1; i < n; i++) {
            if (timeSeries[i] > right) {
                sum += (right - left + 1);
                left = timeSeries[i];
                right = timeSeries[i] + duration - 1;
            } else {
                right = timeSeries[i] + duration - 1;
            }
        }
        // check the last element
        if (timeSeries[n] > right)
            sum += (right - left + 1) + duration;
        else
            sum += ((timeSeries[n] + duration - 1) - left + 1);
        return sum;
    }
}
