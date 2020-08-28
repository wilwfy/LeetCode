/**
 * Other's solution with TreeMap and cellingKey()
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[]{};
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }
        
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i][1]);
            res[i] = (key != null) ? map.get(key) : -1;
        }
        return res;
    }
}


/**
 * Other's solution with TreeMap and ceilingEntry()
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        // corner case
        if(intervals == null || intervals.length == 0) return new int[]{};
        
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            treeMap.put(intervals[i][0], i);
        }
        for(int i = 0; i < intervals.length; i++){
            Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(intervals[i][1]);
            res[i] = (entry == null) ? -1 : entry.getValue();
        }
        return res;
    }
}
