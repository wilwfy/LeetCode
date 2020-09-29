/**
 * Official solution of Sort
 *
 * Intuition and Algorithm
 * 
 * For all elements like A[i] = v, let's write the indices i in sorted order of their values v.
 * For example with A[0] = 7, A[1] = 2, A[2] = 5, A[3] = 4, we can write the order of indices
 * i=1, i=3, i=2, i=0.
 * 
 * Then, whenever we write an index idx, we know there was a ramp of width idx - min(indexes_previously_written)
 * (if this quantity is positive). We can keep track of the minimum of all indexes previously written as prevMinIdx.
 *
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(N), depending on the implementation of the sorting function.
 */
class Solution {
    public int maxWidthRamp(int[] A) {
        if (A == null || A.length == 0) return 0;
        Integer[] idxArr = new Integer[A.length];
        for (int i = 0; i < idxArr.length; i++)
            idxArr[i] = i;
        
        Arrays.sort(idxArr, (a, b) -> ((Integer)A[a]).compareTo(A[b]));
        
        int res = 0, prevMinIdx = A.length;
        for (int idx: idxArr) {
            res = Math.max(res, idx - prevMinIdx);
            prevMinIdx = Math.min(prevMinIdx, idx);
        }
        return res;
    }
}
