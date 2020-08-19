/**
 * Other's Iteration solution
 *
 * Explanation
 * We initial the current result with all 1-digit numbers,
 * like cur = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9].
 * 
 * Each turn, for each x in cur,
 * we get its last digit y = x % 10.
 * If y + K < 10, we add x * 10 + y + K to the new list.
 * If y - K >= 10, we add x * 10 + y - K to the new list.
 * 
 * We repeat this step N - 1 times and return the final result.
 * 
 * 
 * Complexity
 * If K >= 5, time and Space O(N)
 * If K <= 4, time and space O(2^n)
 */
class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> curNumList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 2; i <= N; i++) {
            List<Integer> tmpList = new ArrayList<>();
            for (int x: curNumList) {
                int y = x % 10;
                if (x > 0 && y + K < 10) // Need x > 0 to eliminate leading 0
                    tmpList.add(x * 10 + y + K);
                if (x > 0 && K > 0 && y - K >= 0) // Need K > 0 to eliminate duplicated values between y + K and y - K when K = 0
                    tmpList.add(x * 10 + y - K);
            }
            curNumList = tmpList;
        }
        return curNumList.stream().mapToInt(j -> j).toArray();
    }
}


/**
 * Official solution of DFS
 *
 * Algorithm
 * 
 * Intuitively we could implement the DFS algorithm with recursion. Here we define a recursive function DFS(N, num)
 * (in Python) whose goal is to come up the combinations for the remaining N digits, starting from the current num.
 * Note that, the signature of the function is slightly different in our Java implementation. Yet, the semantics of
 * the function remains the same.
 * 
 * DFS example
 * 
 * For instance, in the previous examples, where N=3 and K=2, and there is a moment we would invoke DFS(1, 13) which
 * is to add another digit to the existing number 13 so that the final number meets the requirements. If the DFS
 * function works properly, we should have the numbers of 135 and 131 as results after the invocation.
 * 
 * We could implement the recursive function in the following steps:
 * 
 * As a base case, when N=0 i.e. no more remaining digits to complete, we could return the current num as the result.
 * 
 * Otherwise, there are still some remaining digits to be added to the current number, e.g. 13. There are two
 * potential cases to explore, based on the last digit of the current number which we denote as tail_digit.
 * 
 * Adding the difference K to the last digit, i.e. tail_digit + K.
 * 
 * Deducting the difference K from the last digit, i.e. tail_digit - K.
 * 
 * If the result of either above case falls into the valid digit range (i.e. [0, 9][0,9]), we then continue the
 * exploration by invoking the function itself.
 * 
 * Once we implement the DFS(N, num) function, we then simply call this function over the scope of [1, 9][1,9], i.e.
 * the valid digits for the highest position.
 * 
 * Note: If we are asked to return numbers of a single digit (i.e. N=1), then regardless of K, all digits are valid,
 * including zero. We treat this as a special case in the code, since in our implementation of DFS function, we will
 * never return zero as the result.
 *
 * Complexity Analysis
 * 
 * Let N be the number of digits for a valid combination, and K be the difference between digits.
 * 
 * First of all, let us estimate the number of potential solutions. For the highest digit, we could have 9 potential
 * candidates. Starting from the second highest position, we could have at most 2 candidates for each position.
 * Therefore, at most, we could have 9 * 2^{N-1} solutions, for N > 1.
 * 
 * Time Complexity: O(N * 2^{N}).
 *                  For each final combination, we would invoke the recursive function N times. The operations
 *                  within each invocation takes a constant time O(1).
 *                  Therefore, for a total 9 * 2^{N-1} number of potential combinations, a loose upper-bound on
 *                  the time complexity of the algorithm would be O(N * 9 * 2^{N-1}) = O(N * 2^{N}), since different
 *                  combinations could share some efforts during the construction.
 *                  Note that, when K = 0, at each position, there is only one possible candidate, e.g. 333. In total,
 *                  we would have 9 numbers in the result set, and each number is of N digits. The time complexity
 *                  would then be reduced down to O(N).
 * 
 * Space Complexity: O(2^{N}).
 *                   Since we adopt a recursive solution, we would have some additional memory consumption on the
 *                   function call stack. The maximum number of consecutive calls on the recursion function is N.
 *                   Hence, the space complexity for the call stack is O(N).
 *                   We use a list to keep all the solutions, which could amount to 9 * 2^{N-1} number of elements.
 *                   To sum up, the overall space complexity of the algorithm is O(N) + O(9 * 2^{N-1}) = O(2^{N}).
 */
class Solution {

    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1)
            return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> results = new ArrayList<Integer>();
        for (int num = 1; num < 10; ++num)
            this.DFS(N - 1, num, K, results);

        // convert the ArrayList to int[]
        return results.stream().mapToInt(i->i).toArray();
    }

    protected void DFS(int N, int num, int K, List<Integer> results) {
        if (N == 0) {
            results.add(num);
            return;
        }
        List<Integer> nextDigits = new ArrayList<>();

        Integer tailDigit = num % 10;
        nextDigits.add(tailDigit + K);
        if (K != 0)
            nextDigits.add(tailDigit - K);
        for (Integer nextDigit : nextDigits) {
            if (0 <= nextDigit && nextDigit < 10) {
                Integer newNum = num * 10 + nextDigit;
                this.DFS(N - 1, newNum, K, results);
            }
        }
    }
}


/**
 * Official solution of BFS
 *
 * Algorithm
 * 
 * Here are a few steps to implement the BFS algorithm for this problem.
 * 
 * We could implement the algorithm with nested two-levels loops, where the outer loop iterates through levels and
 * the inner loop handles the elements within each level.
 * 
 * We could use a list data structure to keep the numbers for a single level, i.e. here we name the variable as queue.
 * 
 * For each number in the queue, we could apply the same logics as in the DFS approach, except the last step, rather
 * than making a recursive call for the next number we simply append the number to the queue for the next level.
 *
 * Complexity Analysis
 * 
 * Let N be the number of digits for a valid combination, and K be the difference between digits.
 * 
 * Time Complexity: O(N * 2^{N}).
 *                  Similar with the DFS approach, it takes O(N) to build each solution.
 *                  Therefore, for a total 9 * 2^{N-1} number of potential combinations as we estimated before,
 *                  the time complexity of the algorithm would be O(N * 9 * 2^{N-1}) = O(N * 2^{N}).
 * 
 * Space Complexity: O(2^{N}).
 *                   We use two queues to maintain the intermediate solutions, which contain no more than two levels
 *                   of elements. The number of elements at the level of ii is up to 9 * 2^{i-1}.
 *                   To sum up, the space complexity of the algorithm would be O(9 * 2^{N-1} + 9 * 2^{N-2}) = O(2^N).
 */
class Solution {

    public int[] numsSameConsecDiff(int N, int K) {

        if (N == 1)
            return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for(int level = 1; level < N; ++ level) {
            ArrayList<Integer> nextQueue = new ArrayList<>();
            // iterate through each number within the level
            for (Integer num : queue) {
                Integer tailDigit = num % 10;

                ArrayList<Integer> nextDigits = new ArrayList<>();
                nextDigits.add(tailDigit + K);
                if (K != 0)
                    nextDigits.add(tailDigit - K);
                for (Integer nextDigit : nextDigits) {
                    if (0 <= nextDigit && nextDigit < 10) {
                        Integer newNum = num * 10 + nextDigit;
                        nextQueue.add(newNum);
                    }
                }
            }
            // prepare for the next level
            queue = nextQueue;
        }

        return queue.stream().mapToInt(i->i).toArray();
    }
}
