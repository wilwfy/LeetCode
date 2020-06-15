/**
 * Other's solution of Greedy
 *
 * Intuition
 * Greedily paint nodes one by one.
 * Because there is no node that has more than 3 neighbors, always one possible color to choose.
 *
 * Time Complexity: O(N) with O(paths) = O(1.5N)
 * Space Complexity: O(N)
 */
class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        // Create a graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // ... via adjacency list
        for (int i = 0; i < N; i++) graph.put(i, new HashSet<>());
        // Add the edges
        for (int[] path: paths) {
            int x = path[0] - 1, y = path[1] - 1; //Due to 1-based indexing
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        // Here is our solution vector where res[i] represents color of garden i+1
        int[] res = new int[N];
        
        // Now run graph painting algorithm
        // For each garden
        for (int i = 0; i < N; i++) {
            int[] colors = new int[5]; // Use 5 instead of 4 so we can easily use 1-based indexing of the garden colors
            for (int nei: graph.get(i)) {
                //System.out.println("For garden " + i + " , nei = " + nei + ", res[nei] = " + res[nei]);
                colors[res[nei]] = 1;
            }
            // Now just use a color that has not been used yet
            for (int c = 4; c >= 1; c--) {
                if (colors[c] != 1) // colors[c] == 0 => the color has not been used yet,
                    res[i] = c; //so let's use that one
            }
        }
        return res;
    }
}
