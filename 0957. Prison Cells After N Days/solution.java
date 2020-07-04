/**
 * My solution of Brute Force -- Time Limit Exceeded
 *
 * Time: O(N*len)
 * Space: O(1)
 */
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                cells[0] = 0;
                cells[cells.length-1] = 0;
            }
            int former = cells[0];
            for (int j = 1; j < cells.length - 1; j++) {
                if (former == cells[j+1]) {
                    former = cells[j];
                    cells[j] = 1;
                } else {
                    former = cells[j];
                    cells[j] = 0;                    
                }
            }
        }
        return cells;
    }
}


/**
 * Other's solution of HashMap and Modulus
 *
 * cells.length = 8, and cells[0] and cells[7] will become 0.
 * In fact, cells have only 2 ^ 6 = 64 different states.
 * And there will be a loop if N > 64.
 * 
 * We record all seen states.
 * Be careful,
 * we need transform array to string as the key, otherwise it use the reference.
 * 
 * Since you only have 6 bits that are changing (first and last bit changes to 0 and stays 0). Total max possible distinct states are 2 ^ 6 = 64.
 * 
 * Let's take an example. Assume you are asked the state after 10 ^ 9 days.
 * You store the state in the map the first time you see a new state. Then when you see the same state again, you know that you have passed (lastSeen - currVal) state in
 * between. So now you know your states repeat every (lastSeen - currVal) times. So finally you can mod the current N with that value to not repeat the same steps. Below
 * is an example for 10^9 days.
 * [0,1,0,1,1,0,0,1]
 * 1000000000
 * 
 * N -> N % (last_seen - curr_val) ==> result
 * 999999985 -> 999999985 % (999999999 - 999999985) ==> 5
 * 4 -> 4 % (999999998 - 4) ==> 4
 * 3 -> 3 % (999999997 - 3) ==> 3
 * 2 -> 2 % (999999996 - 2) ==> 2
 * 1 -> 1 % (999999995 - 1) ==> 1
 * 0 -> 0 % (999999994 - 0) ==> 0
 *
 * Time: O(N/loop_size)
 * Space: O(loop_size)
 */
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[cells.length]; // cells2[0] and cells2[7] are 0
            seen.put(Arrays.toString(cells), N--);
            for (int i = 1; i < cells.length - 1; i++)
                cells2[i] = (cells[i-1] == cells[i+1]) ? 1 : 0;

            cells = cells2; // cells[0] and cells[7] are updated to 0
            String k = Arrays.toString(cells);
            if (seen.containsKey(k))
                N %= seen.get(k) - N; // N -> N % (last_seen - curr_val) ==> result
        }
        return cells;
    }
}
