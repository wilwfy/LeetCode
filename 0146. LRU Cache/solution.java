/**
 * My solution with HashMap and ArrayList
 *
 */
class LRUCache {
    int listSize;
    List<Integer> usedList;
    Map<Integer, Integer> map;
    public LRUCache(int capacity) {
        listSize = capacity;
        usedList = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            usedList.remove(new Integer(key));
            usedList.add(key);
            return map.get(key);
        } else
            return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            usedList.remove(new Integer(key));
            usedList.add(key);
            return;
        }
            
        if (listSize > 0) {
            listSize--;
        } else {
            map.remove(usedList.get(0));
            usedList.remove(0);
        }
        usedList.add(key);
        map.put(key, value);
    }
}


/**
 * Other's solution with LinkedHashMap
 *
 * This is the laziest implementation using Java's LinkedHashMap.
 * In the real interview, however, it is definitely not what interviewer expected.
 *
 * In the constructor, the third boolean parameter specifies the ordering mode. If we set it to true, it will be in access order.
 * (https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean-)
 *
 * By overriding removeEldestEntry in this way, we do not need to take care of it ourselves. It will automatically remove the least
 * recent one when the size of map exceeds the specified capacity.
 * (https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-)
 */
import java.util.LinkedHashMap;
public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void set(int key, int value) {
        map.put(key, value);
    }
}


/**
 * Other's solution with HashMap and Double Linked List
 *
 * The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list. One interesting
 * property about double linked list is that the node can remove itself without other reference. In addition, it takes constant time
 * to add and remove nodes from the head or tail.
 *
 * One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary,
 * so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good
 * for the performance.
 */
class LRUCache {
    
    final Node head = new Node(0, 0);
    final Node tail = new Node(0, 0);
    final Map<Integer, Node> map;
    final int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int res = -1;
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            insertToHead(n);
            res = n.value;
        }
        return res;   
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            n.value = value;
            insertToHead(n);
        } else {
          
            if(map.size() == capacity){
                map.remove(tail.prev.key); 
                remove(tail.prev);
            } 
            
            Node n = new Node(key, value);
            insertToHead(n);
            map.put(key, n);
        }  
    }
    
    private void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    private void insertToHead(Node n){
        Node headNext = head.next;
        head.next = n;
        headNext.prev = n;
        n.prev = head;
        n.next = headNext;
    }
    
    class Node{
        Node prev, next;
        int key, value;
        Node(int k, int v){
            key = k;
            value = v;
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
