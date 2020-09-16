/**
 * Official solution of Brute Force [Accepted]
 *
 * Intuition
 * We store an array self.overlaps of intervals that are double booked, and self.calendar for
 * intervals which have been single booked. We use the line start < j and end > i to check if
 * the ranges [start, end) and [i, j) overlap. This is because:
 * Evidently, two events [s1, e1) and [s2, e2) do not conflict if and only if one of them starts
 * after the other one ends: either e1 <= s2 OR e2 <= s1. By De Morgan's laws, this means the events
 * conflict when s1 < e2 AND s2 < e1.
 * 
 * The clever idea is we do not need to "clean up" ranges in calendar: if we have [1, 3] and [2, 4],
 * this will be calendar = [[1,3],[2,4]] and overlaps = [[2,3]]. We don't need to spend time transforming
 * the calendar to calendar = [[1,4]].
 *
 * Time Complexity: O(N^2), where N is the number of events booked. For each new event,
 *                  we process every previous event to decide whether the new event can
 *                  be booked. This leads to ∑k~N O(k) = O(N^2) complexity.
 * Space Complexity: O(N), the size of the calendar.
 */
class MyCalendarTwo {
    List<int[]> calendar;
    List<int[]> overlaps;
    
    public MyCalendarTwo() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] interval: overlaps) {
            if (start < interval[1] && end > interval[0])
                return false;
        }
        for (int[] interval: calendar) {
            if (start < interval[1] && end > interval[0])
                overlaps.add(new int[]{Math.max(start, interval[0]), Math.min(end, interval[1])});
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}


/**
 * Official solution of Boundary Count [Accepted]
 * 
 * Intuition and Algorithm
 * 
 * When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing
 * the values of delta in sorted order of their keys, the running sum active is the number of
 * events open at that time. If the sum is 3 or more, that time is (at least) triple booked.
 *
 * Think of this as "scanning" from left to right with a "vertical laser". Every
 * endpoint (a start point, or an end point) is an event. A start point is +1, an
 * end point is -1. The accumulated value "count" would be the number of "active"
 * intervals which is cut by the vertical laser.
 *
 * Time Complexity: O(N^2), where N is the number of events booked. For each new event,
 *                  we traverse delta, here is eventCntMap, in O(N) time.
 * Space Complexity: O(N), the size of delta, here is eventCntMap.
 */
class MyCalendarTwo {
    TreeMap<Integer, Integer> eventCntMap;
    
    public MyCalendarTwo() {
        eventCntMap = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        eventCntMap.put(start, eventCntMap.getOrDefault(start, 0) + 1); // For start, increase event num by 1
        eventCntMap.put(end, eventCntMap.getOrDefault(end, 0) - 1); // For end, decrease event num by 1
        // Then check if there is triple booking after adding start and end of this event
        int activeCnt = 0;
        for (int cnt: eventCntMap.values()) {
            activeCnt += cnt; // cnt is negative for ends of events
            if (activeCnt >= 3) { // with previously adding the current start and end, there is triple booking
                // roll back the impact of this event
                eventCntMap.put(start, eventCntMap.get(start) - 1);
                eventCntMap.put(end, eventCntMap.get(end) + 1);
                if (eventCntMap.get(start) == 0) eventCntMap.remove(start);
                if (eventCntMap.get(end) == 0) eventCntMap.remove(end);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
