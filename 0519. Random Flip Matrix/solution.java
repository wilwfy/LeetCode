/**
 * Other's solution with HashMap
 *
 * Here we are modeling the matrix as a 1d array with initial size of row*cols.
 * For each flip, randomly pick an index from 0 to size-1 and flip it.
 * int r = rand.nextInt(total--);
 * 
 * Then swap that flipped element with the tail element (index: size-1), store that mapping
 * info (key: origin index, value: index of the tail) into a Map and decrease the size.
 * map.put(r, map.getOrDefault(total, total));
 * 
 * Next time when we randomly pick a same index we can go to the map and find the actual element
 * stores in that index
 * int x = map.getOrDefault(r, r);
 */
class Solution {
    Map<Integer, Integer> map;
    int rows, cols, total;
    Random rand;

    public Solution(int n_rows, int n_cols) {
        map = new HashMap<>();
        rand = new Random();
        rows = n_rows;
        cols = n_cols;
        total = rows * cols;
    }
    
    public int[] flip() {
        // For each flip, randomly pick an index from 0 to size-1 and flip it.
        int i = rand.nextInt(total--);
        int x = map.getOrDefault(i, i); // Next time when we randomly pick a same index we can find the actual element stores for that index in map
        // Swap that flipped element with the tail element (index: size-1),
        // store that mapping info (key: origin index, value: index of the tail) into a Map and decrease the size.
        map.put(i, map.getOrDefault(total, total)); // the total slot might have been used before, by doing map.getOrDefault(total, total)
                                                    // it will be re-mapped to the unused slot that this total value points to.
        return new int[]{x / cols, x % cols};
    }
    
    public void reset() {
        map.clear();
        total = rows * cols;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
