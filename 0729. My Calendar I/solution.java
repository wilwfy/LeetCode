/**
 * My solution with TreeMap
 *
 * Time: O(nlogn). There are n pairs of (start, end) in the test, and take O(logn) to find the lower/higher key for each pair.
 * Space: O(n)
 */
class MyCalendar {
    TreeMap<Integer, Integer> eventMap;
    
    public MyCalendar() {
        eventMap = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        if (eventMap.containsKey(start)) return false;
        
        Integer lowerK = eventMap.lowerKey(start);
        Integer higherK = eventMap.higherKey(start);
        if (lowerK != null) {
            if (eventMap.get(lowerK) > start)
                return false;
        }
        if (higherK != null) {
            if (end > higherK) return false;
        }
        eventMap.put(start, end);
        return true;
    }
}


/**
 * Other's solution with TreeMap
 *
 * Time: O(nlogn). There are n pairs of (start, end) in the test, and take O(logn) to find the lower key for each pair.
 * Space: O(n)
 */
class MyCalendar {
    TreeMap<Integer, Integer> map;
    
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer low = map.lowerKey(end); // use 'end' not 'start' to find the lowerKey
        
        if(low == null || map.get(low) <= start) {
            map.put(start, end);
            return true;
        }
        return false;
    }
}


/**
 * Official solution of Brute Force
 *
 * Time Complexity: O(N^2), where N is the number of events booked. For each new event,
 *                  we process every previous event to decide whether the new event can
 *                  be booked. This leads to ∑k~N O(k)=O(N^2) complexity.
 * Space Complexity: O(N), the size of the calendar.
 */
public class MyCalendar {
    List<int[]> calendar;

    MyCalendar() {
        calendar = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1]) return false;
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}


/**
 * Official solution with TreeMap
 *
 * Time Complexity (Java): O(NlogN), where N is the number of events booked. For each new event, 
 *                         we search that the event is legal in O(logN) time, then insert it in O(1) time.
 * Space Complexity: O(N), the size of the data structures used.
 */
class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
