/**
 * My solution of Boundary Count (Similiar to official solution)
 *
 * This solution is also used for 0731. My Calendar II
 *
 * Time Complexity: O(N^2), where N is the number of events booked. For each new event,
 *                  we traverse delta, here is eventCntMap, in O(N) time.
 * Space Complexity: O(N), the size of delta, here is eventCntMap.
 */
class MyCalendarThree {
    TreeMap<Integer, Integer> eventCntMap;
    
    public MyCalendarThree() {
        eventCntMap = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        eventCntMap.put(start, eventCntMap.getOrDefault(start, 0) + 1);
        eventCntMap.put(end, eventCntMap.getOrDefault(end, 0) - 1);
        
        int activeCnt = 0, max = Integer.MIN_VALUE;
        for (int cnt: eventCntMap.values()) {
            activeCnt += cnt;
            max = Math.max(max, activeCnt);
        }
        return max;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
