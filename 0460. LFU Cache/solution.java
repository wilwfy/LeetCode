/**
 * Other's solution with LinkedHashSet
 */
class LFUCache {
    HashMap<Integer, Integer> vals;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int cap;
    int min = -1; // the least frequency so far
    
    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!vals.containsKey(key)) return -1;
        
        int count = counts.get(key);
        counts.put(key, count + 1);
        
        lists.get(count).remove(key); // remove the key from list of previous frequency
        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count+1))
            lists.put(count+1, new LinkedHashSet());
        lists.get(count+1).add(key); // put the key into list of new frequency
        
        return vals.get(key);
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return; // corner case of capacity
        
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key); // update the frequency of the key
            return;
        }
        
        // Now the key doesn't exist in vals
        if (vals.size() >= cap) {
            int evict = lists.get(min).iterator().next();
            // should evict the absolete data in all these 3 data structures,
            // otherwise the memory leakage would occur
            lists.get(min).remove(evict);
            vals.remove(evict);
            counts.remove(evict);
        }
        vals.put(key, value);
        counts.put(key, 1); // this is a new key
        min = 1; 
        lists.get(1).add(key); // add the new key into the linked set of frequency 1
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
