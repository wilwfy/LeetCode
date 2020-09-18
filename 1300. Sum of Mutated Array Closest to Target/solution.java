/**
 * Other's solution of Sort
 *
 * Explanation
 * Binary search is O(NlogMax(A)).
 * In order to ruduce the difficulty, it constrains A[i] < 10 ^ 5.
 * 
 * In this solution,
 * we sort the input and compared A[i] with target one by one.
 * 
 * Sort the array A in decreasing order.
 * We try to make all values in A to be the min(A) (the last element)
 * If target >= min(A) * n, we doesn't hit our target yet.
 * We should continue to try a value bigger.
 * So we pop the min(A) value.
 * Consider that it won't be affected anymore,
 * we can remove it from target by target -= A.pop()
 * We continue doing step 2-4, until the next number is too big for target.
 * We split the the target evenly, depending on the number of element left in A
 * At this point, @bobalice help explain the round part:
 * if A is empty means its impossible to reach target so we just return maximum element.
 * If A is not empty, intuitively the answer should be the nearest integer to target / len(A).
 * 
 * Since we need to return the minimum such integer if there is a tie,
 * if target / len(A) has 0.5 we should round down,
 * 
 * 
 * Complexity
 * Time: O(NlogN)
 * Space: O(1)
 */
class Solution {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        
        int i = 0, n = arr.length;
        while (i < n && target > arr[i] * (n - i)) {
            target -= arr[i];
            i++;
        }
        
        if (i == n) return arr[n - 1];
        int res = target / (n - i);
        if ( (target - res * (n - i)) > ((res + 1) * (n - i) - target) )
            res++;
        return res;
    }
}
