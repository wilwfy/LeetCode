/**
 * Other's solution of DFS with Sorting
 *
 * According to https://en.wikipedia.org/wiki/Partition_problem, the partition problem (or number partitioning)
 * is the task of deciding whether a given multiset S of positive integers can be partitioned into two subsets
 * S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. The partition problem
 * is NP-complete.
 * 
 * When I trying to think how to apply dynamic programming solution of above problem to this one (difference is
 * divid S into 4 subsets), I took another look at the constraints of the problem:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 * 
 * Sounds like the input will not be very large... Then why not just do DFS? In fact, DFS solution passed judges.
 *
 * Sorting the input array DESC will make the DFS process run much faster. Reason behind this is we always try to
 * put the next matchstick in the first subset. If there is no solution, trying a longer matchstick first will get
 * to negative conclusion earlier.
 */
class Solution {
    public boolean makesquare(int[] matchsticks) {
    	if (matchsticks == null || matchsticks.length < 4)
    		return false;
    	int sum = 0;
    	for (int matchstick : matchsticks)
    		sum += matchstick;
    	if (sum % 4 != 0)
    		return false;
    
    	Arrays.sort(matchsticks);
    
    	return dfs(matchsticks, new int[4], matchsticks.length - 1, sum / 4);
    }
    
    private boolean dfs(int[] matchsticks, int[] sums, int index, int target) {
    	if (index == -1) {
    		if (sums[0] == target && sums[1] == target && sums[2] == target) {
    			return true;
    		}
    		return false;
    	}
        
    	for (int i = 0; i < 4; i++) {
    		if (sums[i] + matchsticks[index] > target || (i > 0 && sums[i] == sums[i - 1]))
    			continue;
    		sums[i] += matchsticks[index];
    		if (dfs(matchsticks, sums, index - 1, target))
    			return true;
    		sums[i] -= matchsticks[index];
    	}
    	return false;
    }
}


/**
 * Official DP solution
 *
 * In any dynamic programming problem, what's important is that our problem must be breakable into smaller
 * subproblems and also, these subproblems show some sort of overlap which we can save upon by caching or
 * memoization.
 * 
 * Suppose we have 3,3,4,4,5,5 as our matchsticks that have been used already to construct some of the sides
 * of our square (Note: not all the sides may be completely constructed at all times.)
 * 
 * If the square side is 88, then there are many possibilities for how the sides can be constructed using the
 * matchsticks above. We can have
 * 
 *   (4, 4), (3, 5), (3, 5) -----------> 3 sides fully constructed.
 *   (3, 4), (3, 5), (4), (5) ---------> 0 sides completely constructed.
 *   (3, 3), (4, 4), (5), (5) ---------> 1 side completely constructed.
 * 
 * As we can see above, there are multiple ways to use the same set of matchsticks and land up in completely
 * different recursion states.
 * 
 * This means that if we just keep track of what all matchsticks have been used and which all are remaining,
 * it won't properly define the state of recursion we are in or what subproblem we are solving.
 * 
 * A single set of used matchsticks can represent multiple different unrelated subproblems and that is just
 * not right.
 * 
 * We also need to keep track of number of sides of the square that have been completely formed till now.
 * 
 * Also, an important thing to note in the example we just considered was that if the matchsticks being used
 * are [3,3,4,4,5,5] and the side of the square is 8, then we will always consider that arrangement that forms
 * the most number of complete sides over that arrangement that leads to incomplete sides. Hence, the optimal
 * arrangement here is (4, 4), (3, 5), (3, 5) with 3 complete sides of the square.
 * 
 * Let us take a look at the following recursion tree to see if in-fact we can get overlapping subproblems.
 * 
 * ![473_Matchsticks-In-Square-Diag-2.png](https://github.com/wilwfy/LeetCode/blob/master/0473.%20Matchsticks%20to%20Square/473_Matchsticks-In-Square-Diag-2.png)
 * 
 * Note: Not all subproblems have been shown in this figure. The thing we wanted to point out was overlapping
 * subproblems.
 * 
 * We know that the overall sum of these matchsticks can be split equally into 4 halves. The only thing we don't
 * know is if 4 equal halves can be carved out of the given set of matchsticks. For that also we need to keep track
 * of the number of sides completely formed at any point in time. If we end up forming 4 equal sides successfully
 * then naturally we would have used up all of the matchsticks each being used exactly once and we would have formed
 * a square.
 * 
 * 
 * 
 * Time Complexity : O(N × 2^N). At max 2^N unique bit masks are possible and during every recursive call, we iterate
 *                   our original matchsticks array to sum up the values of matchsticks used to update the sides_formed
 *                   variable.
 * 
 * Space Complexity : O(N + 2^N) because N is the stack space taken up by recursion and 4 × 2^N = O(2^N) is the max
 *                    possible size of our cache for memoization.
 *                    The size of the cache is defined by the two variables sides_formed and mask. The number of different
 *                    values that sides_formed can take = 4 and number of unique values of mask = 2^N.
 */
class Solution {

    // The memoization cache to be used during recursion.
    public HashMap<Pair<Integer, Integer>, Boolean> memo;

    // Array containing our matchsticks.
    public int[] nums;

    // Possible side of our square depending on the total sum of all the matchsticks. 
    public int possibleSquareSide;

    // Default constructor to initialise our memo cache.
    public Solution() {
        this.memo = new HashMap<Pair<Integer, Integer>, Boolean>();
    }

    // Main DP function.
    public boolean recurse(Integer mask, Integer sidesDone) {
        int total = 0;
        int L = this.nums.length;

        // The memo key for this recursion
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

        // Find out the sum of matchsticks used till now.
        for(int i = L - 1; i >= 0; i--) {
            if ((mask&(1 << i)) == 0) {
                total += this.nums[L - 1 - i];
            }
        }

        // If the sum if divisible by our square's side, then we increment our number of complete sides formed variable.
        if (total > 0 && total % this.possibleSquareSide == 0) {
            sidesDone++;
        }

        // Base case.
        if (sidesDone == 3) {
            return true;
        }


        // Return precomputed results
        if (this.memo.containsKey(memoKey)) {
            return this.memo.get(memoKey);
        }

        boolean ans = false;
        int c = total / this.possibleSquareSide;

        // Remaining vlength in the current partially formed side.
        int rem = this.possibleSquareSide * (c + 1) - total;

        // Try out all remaining options (that are valid)
        for(int i = L - 1; i >= 0; i--) {
            if (this.nums[L - 1 - i] <= rem && (mask&(1 << i)) > 0) {
                if (this.recurse(mask ^ (1 << i), sidesDone)) {
                    ans = true;
                    break;
                }
            }
        }

        // Cache the computed results.
        this.memo.put(memoKey, ans);
        return ans;
    }

    public boolean makesquare(int[] nums) {

        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for(int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        int possibleSquareSide =  perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }

        this.nums = nums;
        this.possibleSquareSide = possibleSquareSide;
        return this.recurse((1 << L) - 1, 0);
    }
}
