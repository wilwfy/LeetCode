/**
 * Other's solution with HashMap and Queue
 */
class FirstUnique {
    Queue<Integer> queue;
    HashMap<Integer, Integer> map;
    
    public FirstUnique(int[] nums) {
        queue = new LinkedList<Integer>();
        map = new HashMap<>();
        for(int item : nums)
            this.add(item); // adding elements to the queue
    }
    
    public int showFirstUnique() {
        if(queue.isEmpty())  return -1; //if the queue is empty
        while(!queue.isEmpty()){
            if(map.get(queue.peek()) > 1)
                queue.remove(); // if the count of the first element is greater than 1 then remove from queue
            else
                return queue.peek(); // else return the element
        }
        return -1; // if every element in the queue is repeated
    }
    
    public void add(int key) {
        if(map.containsKey(key))
            map.put(key, map.get(key) + 1); // if the element exists increment the count in map alone
        else{
            queue.add(key); //if the element is not present then add it to the queue
            map.put(key, 1); // put this element in the map and the count is 1
        }
    }

}
