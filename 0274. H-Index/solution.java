/**
 * Other's solution of Count Sorting
 *
 * The idea is to see that the result can only range from 0 to the length of the array (because we can't have h-index greater than the
 * total papers published). So we create an array "arr" which acts like a HashMap (using pigeon hole principle) and loop backwards from
 * the highest element, then we find "tot" which is the total number of papers that has more than i citations, and we stop when tot>=i
 * (total number of papers with more than i citations >= i). We don't need to keep going because we are trying the biggest i possible,
 * we we stop and return the result.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length; // number of papers
        int[] citationCount = new int[n+1];
        for (int cnt: citations) {
            if (cnt > n)
                citationCount[n]++;
            else
                citationCount[cnt]++;
        }
        int total = 0;
        for (int i = n; i >= 0; i--) {
            total += citationCount[i];
            if (total >= i)
                return i;
        }
        return 0;
    }
}
