/**
 * Official solution based on HashMap, Pair and Binary Search
 *
 * Time Complexity: O(1) for each set operation, and O(logN) for each get operation, where N is the number of entries in the TimeMap.
 * Space Complexity: O(N).
 */
import javafx.util.Pair;
class TimeMap {

    Map<String, List<Pair<Integer, String>>> map;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<Pair<Integer, String>>());
        
        map.get(key).add(new Pair<Integer, String>(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        
        List<Pair<Integer, String>> list = map.get(key);
        // If key is not present in list, the it returns "(-(insertion point) - 1)". 
        // The insertion point is defined as the point at which the key 
        // would be inserted into the list.
        int idx = Collections.binarySearch(list, new Pair<Integer, String>(timestamp, "{"),
            (a, b) -> Integer.compare(a.getKey(), b.getKey()));
        

        if (idx >= 0)
            // the timestamp is found in the list
            return list.get(idx).getValue();
        else if (idx == -1)
            // the timestamp is not found and smaller than the first element of list,
            // so insertion point is 0,
            // so idx = -(0) - 1 and there's no previous element for this case
            return "";
        else
            // the timestamp is not found but greater than the first element of list
            // so idx = -(insertion point) - 1,
            // so insertion point = -idx - 1,
            // so previous element index = -idx - 2
            return list.get(-idx-2).getValue();
    }
}


/**
 * Official solution based on HashMap, TreeMap
 *
 * In Java, we can use TreeMap.floorKey(timestamp) to find the largest timestamp smaller than the given timestamp.
 *
 * Time Complexity: O(1) for each set operation, and O(logN) for each get operation, where N is the number of entries in the TimeMap.
 * Space Complexity: O(N).
 */
class TimeMap {

    Map<String, TreeMap<Integer, String>> map;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key))
            map.put(key, new TreeMap());
        
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        
        TreeMap<Integer, String> treeMap = map.get(key);

        Integer treeKey = treeMap.floorKey(timestamp);
        
        return treeKey == null ? "" : treeMap.get(treeKey);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
