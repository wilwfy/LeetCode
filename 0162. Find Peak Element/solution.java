/**
 * My solution of Binary Search
 *
 * Time: O(logN)
 * Space: O(1)
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int res = 0;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            else if (nums[mid] < nums[mid + 1])
                start = mid;
            else if (nums[mid] > nums[mid + 1])
                end = mid;
        }
        if (nums[start] > nums[end])
            return start;
        else
            return end;
    }
}


/**
 * Official solution of Linear Scan
 *
 * Time: O(N)
 * Space: O(1)
 */
public class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}


/**
 * Official solution of Recursive Binary Search
 *
 * Time complexity : O(log(n)). We reduce the search space in half at every step. Thus, the total search
 *                   space will be consumed in log(n) steps. Here, n refers to the size of nums array.
 * Space complexity : O(log(n)). We reduce the search space in half at every step. Thus, the total search
 *                    space will be consumed in log(n) steps. Thus, the depth of recursion tree will go
 *                    upto log(n).
 */
public class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}


/**
 * Official solution of Iterative Binary Search
 *
 * Time: O(logN)
 * Space: O(1)
 */
public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
