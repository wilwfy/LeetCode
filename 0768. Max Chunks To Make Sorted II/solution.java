/**
 * Other's solution with arrays of Left Max and Right Min
 *
 * Algorithm
 * Iterate through the array, each time all elements to the left are smaller (or equal) to all elements
 * to the right, there is a new chunck.
 * This algorithm can be used to solve ver1 too.
 * 
 * Time complexity: O(n). Use a variable to store the left max and one array to store the right min.
 * Space complexity: O(n).
 */
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] minOfRight = new int[n];
        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }
        
        int res = 0;
        int maxOfLeft = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            maxOfLeft = Math.max(maxOfLeft, arr[i]);
            if (maxOfLeft <= minOfRight[i + 1])
                res++;
        }
        return res + 1;
    }
}


/**
 * Other's solution with Sorting and Prefix Sum
 * 
 * Time complexity: O(nlogn). O(nlogn) for sorting, O(n) for check.
 * Space complexity: O(n).
 */
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int res = 0, sum1 = 0, sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
            sum2 += sorted[i];
            if (sum1 == sum2) res += 1;
        }
        return res;
    }
}
