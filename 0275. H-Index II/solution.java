/**
 * Other's solution of Binary Search
 *
 * The idea is to search for the first index from the sorted array so that :
 * citations[index] >= length(citations) - index.
 * And return (length - index) as the result.
 *
 * Time: O(logn)
 * Space: O(1)
 */
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (citations[mid] == n - mid)
                return n - mid;
            else if (citations[mid] > n - mid)
                // (citations[mid] > n - mid), mid qualified as a hIndex,
		        // but we have to continue to search for a higher one.
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return n - lo;
        
    }
}
