/*
 * My solution with higher Space Complexity
 */
class Solution {
    public int largestPerimeter(int[] A) {
        if (A.length == 3) {
            if (isTriangle(A))
                return A[0] + A[1] + A[2];
            else
                return 0;
        } else {
            Arrays.sort(A);
            //System.out.println(Arrays.toString(A));
            for (int i = A.length - 3; i >= 0; i--) {
                int[] a = Arrays.copyOfRange(A, i, i+3);
                if (isTriangle(a))
                    return a[0] + a[1] + a[2];
            }
            return 0;
        }

    }
    
    public boolean isTriangle(int[] T) {
        if (T.length != 3) return false;
        return (T[0] + T[1] > T[2]) && (T[0] + T[2] > T[1]) && (T[1] + T[2] > T[0]);
    }
}


/*
 * Official solution with Sort
 *
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(1).
 */
class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i)
            if (A[i] + A[i+1] > A[i+2])
                return A[i] + A[i+1] + A[i+2];
        return 0;
    }
}
