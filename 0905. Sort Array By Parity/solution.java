/**
 * My solution of in-place swap with Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int left = 0, right = A.length - 1;
        while (left < right) {
            while ((A[left] % 2 != 0) && left < right) {
                int tmp = A[right];
                A[right] = A[left];
                A[left] = tmp;
                right--;
            }
            left++;
        }
        return A;
    }
}


/**
 * Other's solution of in-place swap with Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        for (int i = 0, j = 0; j < A.length; j++)
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i++] = A[j];
                A[j] = tmp;;
            }
        return A;
    }
}


/**
 * Official solution of Sort
 *
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(N) for the sort, depending on the built-in implementation of sort.
 */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t)
            B[t] = A[t];

        Arrays.sort(B, (a, b) -> Integer.compare(a%2, b%2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;

        /* Alternative:
        return Arrays.stream(A)
                     .boxed()
                     .sorted((a, b) -> Integer.compare(a%2, b%2))
                     .mapToInt(i -> i)
                     .toArray();
        */
    }
}


/**
 * Official solution of Two Pass
 *
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(N), the space used by the answer.
 */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int[] ans = new int[A.length];
        int t = 0;

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 0)
                ans[t++] = A[i];

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 1)
                ans[t++] = A[i];

        return ans;
    }
}


/**
 * Official solution of In-Place
 *
 * Time Complexity: O(N), where N is the length of A. Each step of the while loop makes j-i decrease by at least one. (Note that while quicksort is O(NlogN) normally,
 *                  this is O(N) because we only need one pass to sort the elements.)
 * Space Complexity: O(1) in additional space complexity.
 */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
    }
}
