/*
 * My solution based on visited set.
 *
 * Time Complexity: O(n).
 * Space Complexity: O(n).
 */
class Solution {
    public int arrayNesting(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int maxCount = 0;
        Set<Integer> visitedSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visitedSet.contains(nums[i]))
                continue;
            else
                visitedSet.add(nums[i]);
            
            Set<Integer> idxSet = new HashSet<>();
            int cnt = 0;
            int idx = i;
            
            while (!idxSet.contains(idx)) {
                cnt++;
                idxSet.add(idx);
                idx = nums[idx];
                visitedSet.add(idx);
            }
            if (cnt > maxCount) maxCount = cnt;
        }
        return maxCount;
    }
}


/*
 * Official solution of Brute Force. [Time Limit Exceeded]
 *
 * Time complexity : O(n^2). In worst case, for example - [1,2,3,4,5,0], loop body will be executed n^2 times.
 * Space complexity : O(1). Constant space is used.
 */
public class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i], count = 0;
            do {
                start = nums[start];
                count++;
            }
            while (start != nums[i]);
            res = Math.max(res, count);

        }
        return res;
    }
}


/*
 * Official solution of Using Visited Array
 *
 * Time complexity : O(n). Every element of the numsnums array will be considered atmost once.
 * Space complexity : O(n). visited array of size nn is used.
 */
public class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int start = nums[i], count = 0;
                do {
                    start = nums[start];
                    count++;
                    visited[start] = true;
                }
                while (start != nums[i]);
                res = Math.max(res, count);
            }
        }
        return res;
    }
}


/*
 * Official solution without Using Extra Space
 *
 * Time complexity : O(n). Every element of the numsnums array will be considered atmost once.
 * Space complexity : O(1). Constant Space is used.
 */
public class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
