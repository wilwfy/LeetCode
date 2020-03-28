/*
 * My solution with Stack
 * Similar to these problem:
 *   - 503. Next Greater Element II
 *   - 1019. Next Greater Node In Linked List
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if (T.length == 0) return new int[]{};
        
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>(); // stack of index
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && (T[stack.peek()] < T[i])) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;
    }
}


/*
 * Other's solution with Next array
 * 
 * for each day from end to start, record the next day of temperature t for all t in [30, 100]
 * for each day from n-1 to 0; for all temperature higher than temp[i], find the earliest
 *
 * Time Complexity: O(NW), where N is the length of T and W is the number of allowed values for T[i]. Since W =71 (100 - 30 + 1), we can
 *                  consider this complexity O(N).
 * Space Complexity: O(N+W), the size of the answer and the next array.
 */
class Solution {
    public int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] waits = new int[n];
        int[] next = new int[101]; // next day with with certain temperature.
        for (int i = n - 1; i >= 0; i--) {
            int earliest = Integer.MAX_VALUE;
            for (int t = temps[i] + 1; t <= 100; t++) {
                if (next[t] != 0) earliest = Math.min(earliest, next[t]);
            }
            if (earliest != Integer.MAX_VALUE) waits[i] = earliest - i;
            next[temps[i]] = i;
        }
        return waits;
    }
}
