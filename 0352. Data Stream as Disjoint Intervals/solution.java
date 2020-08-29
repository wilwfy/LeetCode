/**
 * Other's solution of TreeMap
 *
 * Use TreeMap to easily find the lower and higher keys, the key is the start of the interval.
 * Merge the lower and higher intervals when necessary.
 * 
 * The time complexity for adding is O(logN) since lowerKey(), higherKey(), put() and remove() are all O(logN).
 * It would be O(N) if you use an ArrayList and remove an interval from it.
 */
class SummaryRanges {
    TreeMap<Integer, Integer> treeMap;
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        treeMap = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (treeMap.containsKey(val)) return;
        Integer lo = treeMap.lowerKey(val);
        Integer hi = treeMap.higherKey(val);
        if (lo != null && hi != null && treeMap.get(lo) + 1 == val && val + 1 == hi) {
            treeMap.put(lo, treeMap.get(hi)); // merge the two intervals
            treeMap.remove(hi);
        } else if (lo != null && treeMap.get(lo) + 1 >= val) {
            treeMap.put(lo, Math.max(treeMap.get(lo), val));
        } else if (hi != null && val + 1 == hi) {
            treeMap.put(val, treeMap.get(hi));
            treeMap.remove(hi);
        } else {
            treeMap.put(val, val);
        }
    }
    
    public int[][] getIntervals() {
        int[][] res = new int[treeMap.size()][2];
        int i = 0;
        for (int key: treeMap.keySet()) {
            res[i] = new int[]{key, treeMap.get(key)};
            i++;
        }
        return res;
    }
    
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
