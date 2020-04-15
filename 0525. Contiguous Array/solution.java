/*
 * Official Brute Force solution
 *
 * Time complexity : O(n^2). We consider every possible subarray by traversing over the complete array for every start point possible.
 * Space complexity : O(1). Only two variables zeroeszeroes and onesones are required.
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }
}


/*
 * Official solution of Using Extra Array
 *
 * Algorithm:
 * In this approach, we make use of a countcount variable, which is used to store the relative number of ones and zeros encountered
 * so far while traversing the array. The countcount variable is incremented by one for every 1 encountered and the same is decremented
 * by one for every 0 encountered.
 *
 * We start traversing the array from the beginning. If at any moment, the countcount becomes zero, it implies that we've encountered
 * equal number of zeros and ones from the beginning till the current index of the array(i). Not only this, another point to be noted
 * is that if we encounter the same countcount twice while traversing the array, it means that the number of zeros and ones are equal
 * between the indices corresponding to the equal countcount values.
 *
 * If we keep a track of the indices corresponding to the same countcount values that lie farthest apart, we can determine the size of
 * the largest subarray with equal no. of zeros and ones easily.
 *
 * Now, the countcount values can range between -n to +n, with the extreme points corresponding to the complete array being filled with
 * all 0's and all 1's respectively. Thus, we make use of an array arr(of size 2n+1) to keep a track of the various countcount's
 * encountered so far. We make an entry containing the current element's index (i) in the arrarr for a new countcount encountered
 * everytime. Whenever, we come across the same countcount value later while traversing the array, we determine the length of the
 * subarray lying between the indices corresponding to the same countcount values.
 *
 * Time complexity : O(n). The complete array is traversed only once.
 * Space complexity : O(n). arr array of size 2n+1 is used.
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }
}


/*
 * Official solution of HashMap
 *
 * Algorithm:
 * This approach relies on the same premise as the previous approach. But, we need not use an array of size 2n+1, since it isn't
 * necessary that we'll encounter all the countcount values possible. Thus, we make use of a HashMap mapmap to store the entries
 * in the form of (index,count). We make an entry for a count in the mapmap whenever the countcount is encountered first, and later
 * on use the correspoding index to find the length of the largest subarray with equal no. of zeros and ones when the same count is
 * encountered again.
 *
 * Time complexity : O(n). The entire array is traversed only once.
 * Space complexity : O(n). Maximum size of the HashMap mapmap will be n, if all the elements are either 1 or 0.
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}
