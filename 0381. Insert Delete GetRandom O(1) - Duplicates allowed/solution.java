/**
 * Official solution with ArrayList and HashMap
 *
 * Intuition
 * We must support three operations with duplicates:
 *   1. insert
 *   2. remove
 *   3. getRandom
 * To getRandom in O(1) and have it scale linearly with the number of copies of a value. The simplest solution
 * is to store all values in a list. Once all values are stored, all we have to do is pick a random index.
 * 
 * We don't care about the order of our elements, so insert can be done in O(1) using a dynamic array (ArrayList
 * in Java or list in Python).
 * 
 * The issue we run into is how to go about an O(1) remove. Generally we learn that removing an element from an
 * array takes a place in O(N), unless it is the last element in which case it is O(1).
 * 
 * The key here is that we don't care about order. For the purposes of this problem, if we want to remove the
 * element at the ith index, we can simply swap the ith element and the last element, and perform an O(1) pop
 * (technically we don't have to swap, we just have to copy the last element into index i because it's popped
 * anyway).
 * 
 * With this in mind, the most difficult part of the problem becomes finding the index of the element we have to
 * remove. All we have to do is have an accompanying data structure that maps the element values to their index.
 *
 *
 * Algorithm
 * We will keep a list to store all our elements. In order to make finding the index of elements we want to remove O(1),
 * we will use a HashMap or dictionary to map values to all indices that have those values. To make this work each value
 * will be mapped to a set of indices. The tricky part is properly updating the HashMap as we modify the list.
 * 
 * insert: Append the element to the list and add the index to HashMap[element].
 * remove: This is the tricky part. We find the index of the element using the HashMap. We use the trick discussed
 *         in the intuition to remove the element from the list in O(1). Since the last element in the list gets
 *         moved around, we have to update its value in the HashMap. We also have to get rid of the index of the
 *         element we removed from the HashMap.
 * getRandom: Sample a random element from the list.
 */
class RandomizedCollection {
    List<Integer> numList;
    Map<Integer, Set<Integer>> idxMap;
    java.util.Random rand = new java.util.Random();
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        numList = new ArrayList<>();
        idxMap = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = idxMap.containsKey(val);
        if (!contain) idxMap.put(val, new HashSet<Integer>());
        
        idxMap.get(val).add(numList.size()); // the last index for new list
        numList.add(val);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) return false;
        
        int removeIdx = idxMap.get(val).iterator().next();
        idxMap.get(val).remove(removeIdx);
        if (idxMap.get(val).isEmpty()) idxMap.remove(val);
        
        if (removeIdx < numList.size() - 1) { // not the last element of the numList
            int last = numList.get(numList.size()-1);
            numList.set(removeIdx, last);
            idxMap.get(last).add(removeIdx);
            idxMap.get(last).remove(numList.size()-1);
        }

        numList.remove(numList.size()-1);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return numList.get(rand.nextInt(numList.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
