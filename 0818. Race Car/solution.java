/**
 * Other's solution of BFS
 *
 * the BFS solution is straightforward: we can keep track of all the possible positions of the racecar after n
 * instructions (n = 0, 1, 2, 3, 4, ...) and return the smallest n such that the target position is reached.
 * Naive BFS will run at O(2^n) since for each position we have two choices: either accelerate or reverse. Further
 * observations reveal that there may be overlapping among intermediate states so we need to memorize visited
 * states (each state is characterized by two integers: car position and car speed). However, the total number of
 * unique states still blows up for large target positions (because the position and speed can grow unbounded), so
 * we need further pruning of the search space.
 * 
 * Tim: O(target * log(target)) in the worst case
 * Space: O(target * log(target)) in the worst case.
 *
 * The reasoning is as follows: in the worst case, all positions in the range [-target, target] will be visited
 * and for each position there can be as many as 2 * log(target) different speeds.
 */
class Solution {
    public int racecar(int target) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offerLast(new int[] {0, 1}); // starts from position 0 with speed 1
        
        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + 1);
        
        for (int level = 0; !queue.isEmpty(); level++) {
            for(int k = queue.size(); k > 0; k--) {
                int[] cur = queue.pollFirst();  // cur[0] is position; cur[1] is speed
                
                if (cur[0] == target) {
                    return level;
                }
                
                int[] nxt = new int[] {cur[0] + cur[1], cur[1] << 1};  // accelerate instruction
                String key = (nxt[0] + " " + nxt[1]);
                
                if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
                    queue.offerLast(nxt);
                    visited.add(key);
                }
                
                nxt = new int[] {cur[0], cur[1] > 0 ? -1 : 1};  // reverse instruction
                key = (nxt[0] + " " + nxt[1]);
                
                if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
                    queue.offerLast(nxt);
                    visited.add(key);
                }
            }
        }
        
        return -1;
    }
}


/**
 * Other's solution of DP
 *
 * Example
 * For the input 5, we can reach with only 7 steps: AARARAA. Because we can step back.
 * 
 * Explanation
 * Let's say n is the length of target in binary and we have 2 ^ (n - 1) <= target < 2 ^ n
 * We have 2 strategies here:
 * 
 * 1. Go pass our target , stop and turn back
 * We take n instructions of A.
 * 1 + 2 + 4 + ... + 2 ^ (n-1) = 2 ^ n - 1
 * Then we turn back by one R instruction.
 * In the end, we get closer by n + 1 instructions.
 * 
 * 2. Go as far as possible before pass target, stop and turn back
 * We take n - 1 instruction of A and one R.
 * Then we take m instructions of A, where m < n
 * 
 * More explanation:
 * Consider two general cases for number i with bit_length n.
 * 
 * i == 2^n - 1, this case, n is the best way
 * 2^(n-1) - 1 < i < 2^n - 1, there are two ways to arrive at i:
 * -- Use n A to arrive at 2^n - 1 first, then use R to change the direction, by here there are n+1 operations
 *    (n A and one R), then the remaining is same as dp[2^n-1-i]
 * -- Use n-1 A to arrive at 2^(n-1)-1 first, then R to change the direction, use m A to go backward, then use
 *    R to change the direction again to move forward, by here there are n-1+2+m=n+m+1 operations (n-1 A, two R, m A) ,
 *    current position is 2^(n-1)-1-(2^m-1)=2^(n-1)-2^m, the remaining operations is same as:
 *    dp[i-(2^(n-1)-1)+(2^m-1)]=dp[i-2^(n-1)+2^m)].
 * 
 * Why dp in this way?
 * 
 * I first think the dp way should be:
 * dp[i] = min(n+1+dp[2**n-1-i], n-1+2+dp[i-2**(n-1)+1])
 * 
 * But it's wrong, look at the (n-1) A case, we do A for (n-1) times, then do two R, then the situation is the
 * same as dp[i-2**(n-1)+1]. This can be larger than the actual min operations since, there may be redundant R
 * operations, we can combine RR operation with the remaining (2**(n-1)-1) to i path. So we use m to go backward
 * between the two R operations and count the remaining (2^(n-1)-2^m) to i path to include the combining situation.
 * 
 * For example:
 * 
 * target = 5
 * 
 * The right answer should be AARARAA, positions: 0, 1, 3, 3, 2, 2, 3, 5
 * But if we use the above formula, the answer is AA (0->3) RR (make speed at 1 again) AARA (3->5)
 * 
 * We can move the last A to the middle of RR, so that we save an operation. That's where the combine can happen.
 * So we do dp by adding m A between the RR and add the # operations for remaining distance.
 * 
 * 
 * Complexity
 * Time: O(TlogT)
 * Space: O(T)
 */
class Solution {
    int[] dp = new int[10001]; // because target <= 10000.
    public int racecar(int target) {
        if (dp[target] > 0) return dp[target];
        int n = (int)(Math.log(target) / Math.log(2)) + 1;
        if (1 << n == target + 1) {
            dp[target] = n;
        } else {
            dp[target] = racecar((1 << n) - 1 - target) + n + 1;
            for (int m = 0; m < n - 1; m++) {
                dp[target] = Math.min(dp[target], racecar(target - (1 << (n - 1)) + (1 << m)) + n + m + 1);
            }
        }
        return dp[target];
    }
}
