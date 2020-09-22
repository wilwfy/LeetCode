/**
 * Other's solution of Divide and Conquer
 *
 * Intuition:
 * Try to divide and conquer,
 * so we have left part, right part.
 * 
 * One way is to divide into [1, N / 2] and [N / 2 + 1, N].
 * But it will cause problems when we merge them.
 * 
 * Another way is to divide into odds part and evens part.
 * So there is no k with A[k] * 2 = odd + even
 * 
 * I brute force all permutations when N = 5:
 * 20 beautiful array found,
 * only 4 don't fit odd + even pattern:
 * [2, 1, 4, 5, 3]
 * [3, 1, 2, 5, 4]
 * [3, 5, 4, 1, 2]
 * [4, 5, 2, 1, 3]
 * 
 * 
 * Beautiful Array Properties
 * Saying that an array is beautiful,
 * there is no i < k < j,
 * such that A[k] * 2 = A[i] + A[j]
 * 
 * Apply these 3 following changes a beautiful array,
 * we can get a new beautiful array
 * 
 * 
 * 1. Deletion
 * Easy to prove.
 * 
 * 2. Addition
 * If we have A[k] * 2 != A[i] + A[j],
 * (A[k] + x) * 2 = A[k] * 2 + 2x != A[i] + A[j] + 2x = (A[i] + x) + (A[j] + x)
 * 
 * E.g: [1,3,2] + 1 = [2,4,3].
 * 
 * 3. Multiplication
 * If we have A[k] * 2 != A[i] + A[j],
 * for any x != 0,
 * (A[k] * x) * 2 = A[k] * 2 * x != (A[i] + A[j]) * x = (A[i] * x) + (A[j] * x)
 * 
 * E.g: [1,3,2] * 2 = [2,6,4]
 * 
 * 
 * Explanation
 * With the observations above, we can easily construct any beautiful array.
 * Assume we have a beautiful array A with length N
 * 
 * A1 = A * 2 - 1 is beautiful with only odds from 1 to N * 2 -1
 * A2 = A * 2 is beautiful with only even from 2 to N * 2
 * B = A1 + A2 beautiful array with length N * 2
 * 
 * E.g:
 * 
 * A = [2, 1, 4, 5, 3]
 * A1 = [3, 1, 7, 9, 5]
 * A2 = [4, 2, 8, 10, 6]
 * B = A1 + A2 = [3, 1, 7, 9, 5, 4, 2, 8, 10, 6]
 * 
 * Time Complexity:
 * I have iteration version here O(N)
 * Naive recursion is O(NlogN)
 * Recursion with one call or with cache is O(N)
 */
class Solution {
    public int[] beautifulArray(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < N) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : res) if (i * 2 - 1 <= N) tmp.add(i * 2 - 1);
            for (int i : res) if (i * 2 <= N) tmp.add(i * 2);
            res = tmp;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}


/**
 * Official solution of Divide and Conquer
 *
 * Intuition
 * 
 * This answer is quite unintuitive.
 * 
 * First, notice that the condition is equivalent to saying that A has no arithmetic subsequence.
 * We'll use the term "arithmetic-free" interchangeably with "beautiful".
 * 
 * One way is to guess that we should divide and conquer. One reason for this is that the condition
 * is linear, so if the condition is satisfied by variables taking on values (1, 2, ..., n), it is
 * satisfied by those variables taking on values (a + b, a + 2*b, a + 3*b, ..., a + (n-1)*b) instead.
 * 
 * If we perform a divide and conquer, then we have two parts left and right, such that each part is
 * arithmetic-free, and we only want that a triple from both parts is not arithmetic. Looking at the conditions:
 * 
 * -- 2*A[k] = A[i] + A[j]
 * -- (i < k < j), i from left, j from right
 * we can guess that because the left hand side 2*A[k] is even, we can choose left to have all odd
 * elements, and right to have all even elements.
 * 
 * Another way we could arrive at this is to try to place a number in the middle, like 5. We will
 * have 4 and 6 say, to the left of 5, and 7 to the right of 6, etc. We see that in general, odd
 * numbers move towards one direction and even numbers towards another direction.
 * 
 * One final way we could arrive at this is to inspect possible answers arrived at by brute force.
 * On experimentation, we see that many answers have all the odd elements to one side, and all the
 * even elements to the other side, with only minor variation.
 * 
 * Algorithm
 * 
 * Looking at the elements 1, 2, ..., N, there are (N+1) / 2 odd numbers and N / 2 even numbers.
 * 
 * We solve for elements 1, 2, ..., (N+1) / 2 and map these numbers onto 1, 3, 5, .... Similarly,
 * we solve for elements 1, 2, ..., N/2 and map these numbers onto 2, 4, 6, ....
 * 
 * We can compose these solutions by concatenating them, since an arithmetic sequence never starts
 * and ends with elements of different parity.
 * 
 * We memoize the result to arrive at the answer quicker.
 *
 * Time Complexity: O(NlogN). The function f is called only O(logN) times, and each time does O(N) work.
 * Space Complexity: O(NlogN).
 */
class Solution {
    Map<Integer, int[]> memo;
    
    public int[] beautifulArray(int N) {
        memo = new HashMap();
        return f(N);
    }

    public int[] f(int N) {
        if (memo.containsKey(N))
            return memo.get(N);

        int[] ans = new int[N];
        if (N == 1) {
            ans[0] = 1;
        } else {
            int t = 0;
            for (int x: f((N+1)/2))  // odds
                ans[t++] = 2*x - 1;
            for (int x: f(N/2))  // evens
                ans[t++] = 2*x;
        }
        memo.put(N, ans);
        return ans;
    }
}
