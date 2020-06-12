/**
 * My solution of Two Pointers
 *
 * Time: O(n+m). n is the length of s, m is the length of t.
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
 * Time: O(n+m). n is the length of s, m is the length of t.
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


/**
 * Other's Binary search solution for follow-up with detailed comments
 *
 * Java binary search using TreeSet got TLE.
 * So I think the Map and TreeSet could be simplified by Array and binarySearch. Since we scan T from beginning to the end (index itself
 * is in increasing order), List will be sufficient. Then we can use binarySearch to replace with TreeSet ability which is a little
 * overkill for this problem.
 *
 * Time: O(m + nlogm)???  n is the length of s, m is the length of t.
 * Space: O(m)
 */
class Solution {
    // Follow-up: O(N) time for pre-processing, O(Mlog?) for each S.
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    //  i=2 ('c'): prev=? (return false)
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }
        
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false; // Note: char of S does NOT exist in T causing NPE
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j < 0) j = -j - 1;
            if (j == idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
}
/**
 * Binary search:
 * record the indexes for each character in t, if s[i] matches t[j], then s[i+1] should match a character in t with index bigger than j.
 * This can be reduced to find the first element larger than a value in an sorted array (find upper bound), which can be achieved using
 * binary search.
 *
 * Trie:
 * For example, if s1 has been matched, s1[last char] matches t[j]. Now, s2 comes, if s1 is a prefix of s2, i.e., s1 == s2.substr[0, i-1],
 * we can start match s2 from s2[i], right?
 * So, the idea is to create a Trie for all string that have been matched so far. At a node, we record the position in t which matches this
 * char represented by the node. Now, for an incoming string s, we first search the longest prefix in the Trie, find the matching position
 * of the last prefix-char in t, say j. Then, we can start matching the first non-prefix-char of s from j+1.
 * Now, if we have done the preprocessing as stated in the binary search approach, we can be even faster.
 */
