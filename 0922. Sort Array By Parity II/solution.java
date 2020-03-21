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

