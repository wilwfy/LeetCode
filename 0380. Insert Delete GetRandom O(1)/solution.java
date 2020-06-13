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
 * Other's solution for the follow-up: allowing duplications.
 *
 * For example, after insert(1), insert(1), insert(2), getRandom() should have 2/3 chance return 1 and 1/3 chance return 2.
 * Then, remove(1), 1 and 2 should have an equal chance of being selected by getRandom().
 * 
 * The idea is to add a set to the hashMap to remember all the locations of a duplicated number.
 */
public class RandomizedSet {
    ArrayList<Integer> nums;
    HashMap<Integer, Set<Integer>> locs;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) locs.put( val, new HashSet<Integer>() ); 
        locs.get(val).add(nums.size());        
        nums.add(val);
        return ! contain ;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) return false;
        int loc = locs.get(val).iterator().next();
            locs.get(val).remove(loc);
        if (loc < nums.size() - 1 ) {
            int lastone = nums.get(nums.size() - 1 );
            nums.set( loc , lastone );
            locs.get(lastone).remove(nums.size() - 1);
            locs.get(lastone).add(loc);
        }
        nums.remove(nums.size() - 1);
        if (locs.get(val).isEmpty()) locs.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get( rand.nextInt(nums.size()) );
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
