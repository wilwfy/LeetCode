/**
 * Official solution of Recursion with Memoization
 * 
 * Algorithm
 * 
 * Store the results obtained for a particular call in a 2-d memoization array memomemo.
 * memo[i][j]memo[i][j] represents the length of the LIS possible using nums[i]nums[i] as
 * the previous element considered to be included/not included in the LIS, with nums[j]nums[j]
 * as the current element considered to be included/not included in the LIS. Here, numsnums
 * represents the given array.
 *
 * Time complexity : O(n^2). Size of recursion tree can go upto n^2.
 * Space complexity : O(n^2). memomemo array of n*n is used.
 */
public class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    
    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }
}


/**
 * Official solution of Dynamic Programming
 * 
 * Algorithm
 * 
 * This method relies on the fact that the longest increasing subsequence possible upto the i^{th} index
 * in a given array is independent of the elements coming later on in the array. Thus, if we know the length
 * of the LIS upto i^{th} index, we can figure out the length of the LIS possible by including the (i+1)^{th}
 * element based on the elements with indices j such that 0≤j≤(i+1).
 * 
 * We make use of a dp array to store the required data. dp[i] represents the length of the longest increasing
 * subsequence possible considering the array elements upto the i^{th} index only ,by necessarily including the
 * i^{th} element. In order to find out dp[i], we need to try to append the current element(nums[i]) in every
 * possible increasing subsequences upto the (i-1)^{th} index(including the (i-1)^{th} index), such that the new
 * sequence formed by adding the current element is also an increasing subsequence. Thus, we can easily determine
 * dp[i] using:
 * 
 * dp[i] = max(dp[j]) + 1, ∀ 0 ≤ j <i
 * 
 * At the end, the maximum out of all the dp[i]'s to determine the final result.
 * 
 * LIS_{length} = max(dp[i]), ∀ 0 ≤ i < n
 * 
 * Time complexity : O(n^2). Two loops of n are there.
 * Space complexity : O(n). dp array of size n is used.
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}


/**
 * Official solution of Dynamic Programming with Binary Search
 * 
 * Algorithm
 * 
 * In this approach, we scan the array from left to right. We also make use of a dp array initialized
 * with all 0's. This dp array is meant to store the increasing subsequence formed by including the
 * currently encountered element. While traversing the nums array, we keep on filling the dp array with
 * the elements encountered so far. For the element corresponding to the j^{th} index (nums[j]), we determine
 * its correct position in the dp array(say i^{th} index) by making use of Binary Search(which can be used
 * since the dp array is storing increasing subsequence) and also insert it at the correct position. An
 * important point to be noted is that for Binary Search, we consider only that portion of the dp array in
 * which we have made the updates by inserting some elements at their correct positions(which remains always
 * sorted). Thus, only the elements upto the i^{th} index in the dp array can determine the position of the
 * current element in it. Since, the element enters its correct position(i) in an ascending order in the dp
 * array, the subsequence formed so far in it is surely an increasing subsequence. Whenever this position
 * index i becomes equal to the length of the LIS formed so far(len), it means, we need to update the len
 * as len = len + 1.
 * 
 * Note: dp array does not result in longest increasing subsequence, but length of dp array will give you
 * length of LIS.
 * 
 * Consider the example:
 * 
 * input: [0, 8, 4, 12, 2]
 * 
 * dp: [0]
 * 
 * dp: [0, 8]
 * 
 * dp: [0, 4]
 * 
 * dp: [0, 4, 12]
 * 
 * dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dp array results in length
 * of Longest Increasing Subsequence.
 *
 * Note: Arrays.binarySearch() method returns index of the search key, if it is contained in the array,
 * else it returns (-(insertion point) - 1). The insertion point is the point at which the key would be
 * inserted into the array: the index of the first element greater than the key, or a.length if all
 * elements in the array are less than the specified key.
 * 
 * Time complexity : O(nlogn). Binary search takes logn time and it is called n times.
 * Space complexity : O(n). dp array of size n is used.
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}


/**
 * Other's solution of Patience sorting
 *
 *   https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
 * Algorithm
 *
 * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
 * For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
 * 
 * len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
 * len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
 * len = 3   :      [4, 5, 6]            => tails[2] = 6
 * We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in
 * tails array to find the one needs update.
 * 
 * Each time we only do one of the two:
 * 
 * (1) if x is larger than all tails, append it, increase the size by 1
 * (2) if tails[i-1] < x <= tails[i], update tails[i]
 * Doing so will maintain the tails invariant. The the final answer is just the size.
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
}

/* Another solution of Patience sorting */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile;
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
            }
        }
        return piles.size();
    }
}
