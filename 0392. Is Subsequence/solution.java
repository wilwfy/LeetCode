/**
 * My solution of Two Pointers
 *
 * Time: O(n+m)
 * Space: O(1)
 */
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int i = 0, j = 0;
        while (j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if (i == s.length()) return true;
            }
            j++;
        }
        return false;
    }
}


/**
 * Other's solution of Two Pointers
 *
 * Time: O(n+m)
 * Space: O(1)
 */
class Solution {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
}
