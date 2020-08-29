/**
 * Other's solution of Next Max Value
 *
 * Explanation
 * Find the index i of the next maximum number x with considering the condition permutation of [1,2,..., A.length]
 * Reverse i + 1 numbers, so that the x will be at A[0]
 * Reverse x numbers, so that x will be at A[x - 1].
 * Repeat this process N times.
 * 
 * Time Complexity: O(N^2).
 * Space Complexity: O(N) for result.
 */
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        // A is a permutation of the integers from 1 to A.length,
        // so the max value of A[i] is A.length
        for (int x = A.length; x > 0; x--) {
            int i = 0;
            while (A[i] != x) i++; // find x which is the max value;
            //System.out.println("i = " + i);
            
            // Swap the max value to A[0]
            reverse(A, i + 1);
            res.add(i + 1);
            // Then, swap the max value to A[x - 1], where x is the
            // last position for this max value in final sorted array
            reverse(A, x);
            res.add(x);
            // x is decreased in x--
        }
        return res;
    }
    
    private void reverse(int[] A, int end) {
        for (int i = 0, j = end - 1; i < j; i++, j--) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
}
