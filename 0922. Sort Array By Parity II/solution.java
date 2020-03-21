/*
 * My first solution
 */
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        //int evenIdx = 0, oddIdx = 1;
        Queue<Integer> evenIdx = new LinkedList<>();
        Queue<Integer> oddIdx = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if ((i%2 == 0) && (A[i]%2 != 0))
                evenIdx.add(i);
            else if ((i%2 == 1) && (A[i]%2 != 1))
                oddIdx.add(i);
        }
        while (!evenIdx.isEmpty()) {
            int a = evenIdx.remove();
            int b = oddIdx.remove();
            int tmp= A[a];
            A[a] = A[b];
            A[b] = tmp;
        }
        return A;
    }
}


/*
 * Official solution of Two Pass
 * 
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(N).
 */
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int N = A.length;
        int[] ans = new int[N];

        int t = 0;
        for (int x: A) if (x % 2 == 0) {
            ans[t] = x;
            t += 2;
        }

        t = 1;
        for (int x: A) if (x % 2 == 1) {
            ans[t] = x;
            t += 2;
        }

        return ans;
    }
}


/*
 * Official solution of Read/Write Heads
 * 
 * Time Complexity: O(N), where N is the length of A.
 * Space Complexity: O(1).
 */
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2)
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        return A;
    }
}


/*
 * Other's faster solutions
 */
class Solution {
    // two pointer one pass inplace
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1, n = A.length;
        while (i < n && j < n) {
            while (i < n && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < n && A[j] % 2 == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                swap(A, i, j);
            }
        }
        return A;
    }
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int[] res = new int[A.length];
        int j = 0, k = 1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] % 2 == 0) {
                res[j] = A[i];
                j += 2;
            } else {
                res[k] = A[i];
                k += 2;
            }
        }
        return res;
    }
}
public int[] sortArrayByParityII(int[] A) {
    int[] res = new int[A.length];

    for (int i = 0, j = -2, k = -1; i < A.length; i++) {
        res[A[i] % 2 == 0 ? (j += 2) : (k += 2)] = A[i];
    }

    return res;
}
