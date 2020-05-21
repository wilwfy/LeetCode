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
 * Other's solution with DoubleLinkedList
 */
public class LFUCache {
    class Node {
        int key, val, cnt;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            cnt = 1;
        }
    }
    
    class DLList {
        Node head, tail;
        int size;
        DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }
        
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            else return null;
        }
    }
    
    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, DLList> countMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        update(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        }
        else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLList lastList = countMap.get(min);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            size++;
            min = 1;
            DLList newList = countMap.getOrDefault(node.cnt, new DLList());
            newList.add(node);
            countMap.put(node.cnt, newList);
        }
    }
    
    private void update(Node node) {
        DLList oldList = countMap.get(node.cnt);
        oldList.remove(node);
        if (node.cnt == min && oldList.size == 0) min++; 
        node.cnt++;
        DLList newList = countMap.getOrDefault(node.cnt, new DLList());
        newList.add(node);
        countMap.put(node.cnt, newList);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
