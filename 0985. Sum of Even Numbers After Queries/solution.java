class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        if ((A == null) || (A.length == 0)) return A;
        int[] res = new int[queries.length];
        int sumEven = 0;
        for (int a: A) {
            if (a%2 == 0) sumEven += a;
        }
        for (int i = 0; i < queries.length; i++) {
            int aNew = A[queries[i][1]] + queries[i][0];
            //System.out.println("aNew = " + aNew + ", A[" + queries[i][1] + "] = " + A[queries[i][1]]);
            if ((aNew%2 == 0) && (A[queries[i][1]]%2 == 0)) {
                sumEven += aNew - A[queries[i][1]];
            } else if ((aNew%2 == 0) && (A[queries[i][1]]%2 != 0)) {
                sumEven += aNew;
            } else if ((aNew%2 != 0) && (A[queries[i][1]]%2 == 0)) {
                sumEven -= A[queries[i][1]];
            }
            res[i] = sumEven;
            A[queries[i][1]] = aNew;
        }
        return res;
    }
}
