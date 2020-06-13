/**
 * Other's solution
 */
class RandomizedSet {
    ArrayList<Integer> numsList;
    HashMap<Integer, Integer> locsMap; // <num_value, index_in_numsList>
    java.util.Random rand = new java.util.Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        numsList = new ArrayList<>();
        locsMap = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (locsMap.containsKey(val))
            return false;
        
        locsMap.put(val, numsList.size());
        numsList.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!locsMap.containsKey(val))
            return false;
        
        int index = locsMap.get(val); // get num_value's index in index_in_numsList
        if (index < numsList.size() - 1) {  // not the last one, then replace this val with the last value
            int lastVal = numsList.get(numsList.size() - 1);
            numsList.set(index, lastVal);
            locsMap.put(lastVal, index);
        }
        // remove the last one in the ArrayList, so it is O(1) not O(n)
        numsList.remove(numsList.size() - 1);
        
        locsMap.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return numsList.get(rand.nextInt(numsList.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
