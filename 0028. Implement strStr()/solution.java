/**
 * My solution of Two Pointers
 *
 * Time: O(N1/N2 * N2^2) = O(N1 * N2).
 * Space: O(1).
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;
        int N1 = haystack.length(), N2 = needle.length();
        for (int i = 0; i <= N1 - N2; i++) {
            int p1 = i, p2 = 0;
            if (haystack.charAt(p1) != needle.charAt(p2)) continue;
            while(p1 < N1 && p2 < N2 && haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            }
            if (p2 == N2) return i;
        }
        return -1;
    }
}


/**
 * Other's solution of Two Pointers
 *
 * Time: O(N1 * N2).
 * Space: O(1).
 */
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}


/**
 * Other's solution of Two Pointers
 *
 * Time: O(N1 * N2).
 * Space: O(1).
 */
class Solution {
    public int strStr(String haystack, String needle) {
        // empty needle appears everywhere, first appears at 0 index
        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0)
            return -1;
        
        for (int i = 0; i < haystack.length(); i++) {
            // no enough places for needle after i
            if (i + needle.length() > haystack.length()) break;
            
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j))
                    break;
                if (j == needle.length()-1)
                    return i;
            }
        }
        
        return -1;
    }
}
