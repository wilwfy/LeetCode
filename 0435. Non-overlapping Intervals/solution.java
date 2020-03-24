// the problem is the same as "Given a collection of intervals, find the maximum
// number of intervals that are non-overlapping." (the classic Greedy problem: Interval Scheduling)

// Which interval would be the best first (leftmost) interval to keep? 
// One that ends first, as it leaves the most room for the rest. 

/*
 * Other's Greedy solution with sorting by the start of interval
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) return 0;
        
        // sort by start
        Arrays.sort(intervals, (i1, i2) -> (i1[1] - i2[1]));
        //Arrays.sort(intervals, Comparators.compareInt(i -> i[1]));
        
        int cnt = 0;
        int preNonOverlapEnd = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] < preNonOverlapEnd) {
                cnt++;
                
                // when 2 intervals overlap, the one with smaller end should be removed,
                // since it leaves more room for the rest, so less intercal should be remove later
                preNonOverlapEnd = Math.min(preNonOverlapEnd, intervals[i][1]);
                
                // This is not necessary if we sort by the end
                // when 2 interval overlap and sort by the end, the previous end is always gonna be
                // smaller than the next end
            } else
                preNonOverlapEnd = intervals[i][1];
        }
        return cnt;
    }
}


/*
 * Other's Greedy solution with sorting by the end of interval
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) return 0;
        
        // sort by end
        Arrays.sort(intervals, (i1, i2) -> (i1[1] - i2[1]));
        
        int cnt = 0;
        int preNonOverlapEnd = Integer.MIN_VALUE;
        for (int[] interval: intervals) {
            if (interval[0] < preNonOverlapEnd) {
                cnt++;
                //preNonOverlapEnd = interval[1];
            } else
                preNonOverlapEnd = interval[1];
        }
        
        return cnt;
    }
}
