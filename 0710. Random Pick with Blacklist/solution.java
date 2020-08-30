/**
 * Other's solution of HashMap and re-mapping
 *
 * Explanation:
 * Suppose N=10, blacklist=[3, 5, 8, 9], re-map 3 and 5 to 7 and 6.
 */
class Solution {
    // N: [0, N)
    // B: blacklist
    // B1: < N
    // M: N - B1
    Map<Integer, Integer> map;
    Random rand;
    int M;
    
    public Solution(int N, int[] blacklist) {
        map = new HashMap<>();
        rand = new Random();
        
        for (int b: blacklist)
            map.put(b, -1); // initialize the re-mapping
        
        M = N - map.size();
        for (int b: blacklist) {
            if (b < M) {
                while (map.containsKey(N - 1))
                    N--; // find a value N-1 which is not in blacklist and bigger than M
                map.put(b, N - 1); // re-map: b -> N-1
                N--;
            }
        }
    }
    
    public int pick() {
        int num = rand.nextInt(M);
        if (map.containsKey(num))
            return map.get(num);
        return num;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */
