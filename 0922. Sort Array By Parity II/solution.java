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
