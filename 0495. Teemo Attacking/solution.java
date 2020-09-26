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


/**
 * Other's solution of Two Pointers
 *
 * The same idea as https://leetcode.com/problems/merge-intervals/
 * Algorithm:
 * 
 * 1. Use two variable to record current start and end point.
 * 2. If the start of new interval is greater than current end, meaning NO overlapping, we can
 *    sum the current interval length to result and then update start and end.
 * 3. Otherwise just update the current end;
 *
 * Time: O(n)
 * Space: O(1)
 */
public class Solution {
    public int findPosisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) return 0;
        
        int result = 0, start = timeSeries[0], end = timeSeries[0] + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] > end) {
                result += end - start;
                start = timeSeries[i];
            }
            end = timeSeries[i] + duration;
        }
        result += end - start;
        
        return result;
    }
}

/**
 * Official solution of One Pass
 *
 * Intuition
 * 
 * The problem is an example of merge interval questions which are now quite popular in Google.
 * 
 * Typically such problems could be solved in a linear time in the case of sorted input, like here,
 * and in O(NlogN) time otherwise, here is an example.
 * 
 * Here one deals with a sorted input, and the problem could be solved in one pass with a constant
 * space. The idea is straightforward: consider only the interval between two attacks. Ashe spends
 * in a poisoned condition the whole time interval if this interval is shorter than the poisoning
 * time duration duration, and duration otherwise.
 * 
 * Algorithm
 * 
 * Initiate total time in poisoned condition total = 0.
 * 
 * Iterate over timeSeries list. At each step add to the total time the minimum between interval
 * length and the poisoning time duration duration.
 * 
 * Return total + duration to take the last attack into account.
 *
 * Time complexity : O(N), where N is the length of the input list, since we iterate the entire list.
 * Space complexity : O(1), it's a constant space solution.
 */
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        if (n == 0) return 0;
	    
        int total = 0;
        for (int i = 0; i < n - 1; ++i)
            total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        return total + duration;
    }
}
