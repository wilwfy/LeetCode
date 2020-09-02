/**
 * Other's solution of Permutation and String Comparison
 *
 * Intuition
 *
 * The inner most loop at most iterates 4 * 4 * 4 = 64 times.
 * A[i], A[j], A[k], & A[l] are the 4 elements of A, where i, j, k & l are the permutation of 0, 1, 2, & 3.
 * Therefore, since i + j + k + l = 0 + 1 + 2 + 3 = 6, we have l = 6 - i - j - k.
 *
 * Time: O(1). Because the number of permutation is 4! = 4 * 3 * 2 * 1 = 24
 * Space: O(1).
 */
class Solution {
    public String largestTimeFromDigits(int[] A) {
        String ans = "";
        for (int i = 0; i < 4; i++) { // A.length = 4
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || i == k || j == k) continue;
                    String h = "" + A[i] + A[j]; // hour
                    String m = "" + A[k] + A[6 - i - j - k]; // minute
                    String t = h + ":" + m; // time
                    // hour < 24; minute < 60; update result.
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && t.compareTo(ans) > 0)
                        ans = t;
                }
            }
        }
        return ans;
    }
}


/**
 * Official solution of Permutation via Backtracking
 *
 * Intuition
 * 
 * As we discussed before, the hard part of the problem is not enumerating over the permutations,
 * but actually constructing the permutations itself.
 * 
 * In this approach, we present a solution to reinvent the wheel, i.e. generating permutations, which
 * itself is a fun problem to solve. For practice, one can implement the permutation algorithms on
 * these two problem: permutations and next permutation.
 * 
 * There are several classic algorithms to generate the permutations. For instance, B.R. Heap proposed
 * an algorithm (named Heap's algorithm) in 1963, which minimizes the movements of elements. It was
 * still considered as the most efficient algorithm later in 1977.
 * 
 * Here we present an algorithm, which might not be the most efficient one but arguably more intuitive.
 * 
 * It is based on the ideas of divide-and-conquer, swapping and backtracking.
 * - First of all, the algorithm follows the paradigm of divide and conquer. Given an array A[0:n], once
 *   we fix on the arrangements of the prefix subarray A[0:i], we then reduce the problem down to a subproblem,
 *   i.e. generating the permutations for the postfix subarray A[i:n].
 * - In order to fix on a prefix subarray, we apply the operation of swapping, where we swap the elements
 *   between a fixed position and an alternative position.
 *
 * Algorithm
 * 
 * Now we can put together all the ideas that we presented before, and implement the permutation algorithm.
 * 
 * Here we implement the permutation algorithm as the function permutate(array, start) which generates the
 * permutations for the postfix subarray of array[start:len(array)]. Once we implement the function, we
 * invocate it as permutate(array, 0) to generate all the permutations from the array.
 * 
 * As a preview, once implemented, the function will unfold itself as in the following example.
 * 
 * For instance, starting from the root node, first we try to fix on the first element in the final combination,
 * which we try to switch the element between the first position in the array and each of the positions in the array.
 * Since there are 3 possible candidates, we branch out in 3 directions from the root node.
 * 
 * The function can be implemented in recursion, due to its nature of divide-and-conquer and backtracking.
 * 
 * - The base case of the function would be start == len(array), where we've fixed all the prefixes and reached the
 *   end of the combination. In this case, we simply add the current array as one of the results of combination.
 * 
 * - When we still have some postfix that need to be permutated, i.e. start < len(array), we then apply backtracking
 *   to try out all possible permutations for the postfixes, i.e. permutate(array, start+1). More importantly, we
 *   need to swap the start element with each of the elements following the start index (including the start element).
 *   The goal is two-fold: 1). we generate different prefixes for the final combination; 2). we generate different
 *   lists of candidates in the postfixes, so that the permutations generated from the postfixes would vary as well.
 * 
 * - At the end of backtracking, we will swap the start element back to its original position, so that we can try
 *   out other alternatives.
 * 
 * - For each permutation, we apply the same logic as in the previous approach, i.e. check if the permutation is of
 *   valid time and update the maximum time.
 *
 * Time Complexity: O(1)
 *                  Since the length of the input array is fixed, it would take the same constant time
 *                  to generate its permutations, regardless the content of the array. Therefore, the
 *                  time complexity to generate the permutations would be O(1).
 *                  Therefore, the overall time complexity of the algorithm would be O(1).
 * Space Complexity: O(1)
 *                   In the algorithm, we keep the permutations for the input digits, which are in total 24,
 *                   i.e. a constant number regardless the input.
 *                   Although the recursion in the algorithm could incur additional memory consumption in the
 *                   function call stack, the maximal number of recursion is bounded by the size of the combination.
 *                   Hence, the space overhead for the recursion in this problem is constant.
 */
class Solution {
    private int max_time = -1;

    public String largestTimeFromDigits(int[] A) {
        this.max_time = -1;
        permutate(A, 0);
        if (this.max_time == -1)
            return "";
        else
            return String.format("%02d:%02d", max_time / 60, max_time % 60);
    }

    protected void permutate(int[] array, int start) {
        if (start == array.length) {
            this.build_time(array);
            return;
        }
        for (int i = start; i < array.length; ++i) {
            this.swap(array, i, start);
            this.permutate(array, start + 1);
            this.swap(array, i, start);
        }
    }

    protected void build_time(int[] perm) {
        int hour = perm[0] * 10 + perm[1];
        int minute = perm[2] * 10 + perm[3];
        if (hour < 24 && minute < 60)
            this.max_time = Math.max(this.max_time, hour * 60 + minute);
    }

    protected void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
