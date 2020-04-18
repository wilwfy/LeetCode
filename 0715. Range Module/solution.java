/*
 * Official solution of Maintain Sorted Disjoint Intervals
 *
 * Intuition
 *   Because left, right < 10^9, we need to deal with the coordinates abstractly. Let's maintain some sorted structure of disjoint
 *   intervals. These intervals will be closed (eg. we don't store [[1, 2], [2, 3]]; we would store [[1, 3]] instead.)
 *
 * We will maintain the structure as a TreeSet ranges = new TreeSet<Interval>();. We introduce a new Comparable class Interval to
 * represent our half-open intervals. They compare by right-most coordinate as later we will see that it simplifies our work. Also
 * note that this ordering is consistent with equals, which is important when dealing with Sets.
 *
 * Time Complexity: Let K be the number of elements in ranges. addRange and removeRange operations have O(K) complexity.
 *                  queryRange has O(logK) complexity. Because addRange, removeRange adds at most 1 interval at a time,
 *                  you can bound these further. For example, if there are A addRange, R removeRange, and Q queryRange number
 *                  of operations respectively, we can express our complexity as O((A+R)^2 * Qlog(A+R)).
 * Space Complexity: O(A+R), the space used by ranges.
 */
class RangeModule {
    private TreeSet<Interval> ranges;
    public RangeModule() {
        ranges = new TreeSet();
    }
    
    public void addRange(int left, int right) {
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left - 1)).iterator();
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            left = Math.min(left, iv.left);
            right = Math.max(right, iv.right);
            itr.remove();
        }
        ranges.add(new Interval(left, right));
    }
    
    public boolean queryRange(int left, int right) {
        Interval iv = ranges.higher(new Interval(0, left));
        return (iv != null && iv.left <= left && right <= iv.right);
    }
    
    public void removeRange(int left, int right) {
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left)).iterator();
        ArrayList<Interval> todo = new ArrayList();
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            if (iv.left < left) todo.add(new Interval(iv.left, left));
            if (right < iv.right) todo.add(new Interval(right, iv.right));
            itr.remove();
        }
        for (Interval iv: todo) ranges.add(iv);
    }
}

class Interval implements Comparable<Interval>{
    int left;
    int right;
    
    public Interval(int left, int right){
        this.left = left;
        this.right = right;
    }
    
    public int compareTo(Interval that){
        if (this.right == that.right) return this.left - that.left;
        return this.right - that.right;
    }
}


/**
 * Other's solution with TreeMap
 */
class RangeModule {
    TreeMap<Integer, Integer> intervals = new TreeMap<>();
    
    public void addRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);
        if(start != null && intervals.get(start) >= left){
            left = start;
        }
        if(end != null && intervals.get(end) > right){
            right = intervals.get(end);
        }
        intervals.put(left, right);
       
        intervals.subMap(left, false, right, true).clear();
    }
    
    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        if(start == null) return false;
        return intervals.get(start) >= right;
    }
    
    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if(end != null && intervals.get(end) > right){
            intervals.put(right, intervals.get(end));
        }
        if(start != null && intervals.get(start) > left){
            intervals.put(start, left);
        }
        intervals.subMap(left, true, right, false).clear();
    }
}


/**
 * Other's solution with TreeMap
 */
class RangeModule {
    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public void addRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        if (start == null) start = map.ceilingKey(left);
        while (start != null && start <= right) {
            int end = map.get(start);
            if (end >= left) {
                map.remove(start);
                if (start < left) left = start;
                if (end > right) right = end;
            }
            start = map.ceilingKey(end);
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer floor = map.floorKey(left);
        return floor != null && map.get(floor) >= right;
    }

    public void removeRange(int left, int right) {
        if (left >= right) return;
        Integer start = map.floorKey(left);
        if (start == null) start = map.ceilingKey(left);
        while (start != null && start < right) {
            int end = map.get(start);
            if (end >= left) {
                map.remove(start);
                if (start < left) map.put(start, left);
                if (end > right) map.put(right, end);
            }
            start = map.ceilingKey(end);
        }
    }
}
/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
