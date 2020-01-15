class Solution {
    public void rotate(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return;
        int n = nums.length;
        int shift = (n - k >= 0) ? k : k % n;
        if (shift == 0) return;
        
        int[] tmp = new int[n];
        for (int i = 0; i < shift; i++) {
            tmp[i] = nums[n-shift+i];
        }
        for (int i = shift; i < n; i++) {
            tmp[i] = nums[i-shift];
        }
        
        System.arraycopy(tmp, 0, nums, 0, n);
        return;
    }
}


/*
 * Official solution by using extra array
 *
 * Time complexity : O(n). One pass is used to put the numbers in the new array. 
 *                  And another pass to copy the new array to the original one.
 * Space complexity : O(n). Another array of the same size is used.
 */
public class Solution {
    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }
}


/*
 * Official solution by using Cyclic Replacements
 *
 * Time complexity : O(n). Only one pass is used.
 * Space complexity : O(1). Constant extra space is used.
 */
public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}


/*
 * Official solution by using Cyclic Replacements
 *
 * Let n=7 and k=3.
 * Original List                   : 1 2 3 4 5 6 7
 * After reversing all numbers     : 7 6 5 4 3 2 1
 * After reversing first k numbers : 5 6 7 4 3 2 1
 * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
 *
 * Time complexity : O(n). n elements are reversed a total of three times.
 * Space complexity : O(1). No extra space is used.
 */
public class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
