/**
 * Official solution of Brute Force
 *
 * Intuition and Algorithm
 * 
 * Let's try to find the smallest left-most chunk. If the first k elements are [0, 1, ..., k-1],
 * then it can be broken into a chunk, and we have a smaller instance of the same problem.
 * 
 * We can check whether k+1 elements chosen from [0, 1, ..., n-1] are [0, 1, ..., k] by checking
 * whether the maximum of that choice is k.
 *
 * More explanation:
 * The basic idea is to use max[] array to keep track of the max value until the current position,
 * and compare it to the sorted array (indexes from 0 to arr.length - 1). If the max[i] equals the
 * element at index i in the sorted array, then the final count++. Because the numbers range from 0
 * to arr.length - 1. So there is no need to sort the arr, we can simply use the index for comparison.
 * 
 * For example,
 * 
 * original: 0, 2, 1, 4, 3, 5, 7, 6
 * max:      0, 2, 2, 4, 4, 5, 7, 7
 * sorted:   0, 1, 2, 3, 4, 5, 6, 7
 * index:    0, 1, 2, 3, 4, 5, 6, 7
 * The chunks are: 0 | 2, 1 | 4, 3 | 5 | 7, 6
 *
 * Time Complexity: O(N), where N is the length of arr
 * Space Complexity: O(1).
 */
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
        }
        return ans;
    }
}
