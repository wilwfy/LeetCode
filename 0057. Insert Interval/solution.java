/*
 * My solution by referring to other's
 *
 * Time: O(n). n is the length of intervals
 * Space: O(2n) = O(n). Use n arrays in which there are 2 elements for each.
 */
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        
        List<int[]> list = new LinkedList<>();
        // Add all the intervals ending before newInterval starts since no overlap so far
        int i = 0;
        for (; i < intervals.length && intervals[i][1] < newInterval[0]; i++)
            list.add(intervals[i]);
        
        // merge all overlapping intervals to one considering newInterval
        while ((i < intervals.length) && (intervals[i][0] <= newInterval[1])) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);  // add the union of intervals we got
        
        // add all the rest
        for (; i < intervals.length; i++)
            list.add(intervals[i]);
        
        return list.toArray(int[][]::new);
    }
}


/*
 * Other's solution
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++)); 
        return result;
    }
}


/*
 * Other's solution of in-place merging
 *
 * Time: O(n^2).  Because In the while loop the remove() operation takes O(n).
 * Space: O(1)
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {``
            int i=0;
            while(i<intervals.size() && intervals.get(i).end<newInterval.start) i++;
            while(i<intervals.size() && intervals.get(i).start<=newInterval.end){
                newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(intervals.get(i).end, newInterval.end));
                intervals.remove(i);
            }
            intervals.add(i,newInterval);
            return intervals;
    }
}


/*
 * Other's solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        for (Interval i : intervals) {
            if (newInterval == null || i.end < newInterval.start)
                result.add(i);
            else if (i.start > newInterval.end) {
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(newInterval.start, i.start);
                newInterval.end = Math.max(newInterval.end, i.end);
            }
        }
        if (newInterval != null)
            result.add(newInterval);
        return result;
    }
}
