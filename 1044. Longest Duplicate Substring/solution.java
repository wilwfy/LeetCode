/**
 * Other's solution of Binary Search
 *
 * Intuition
 * Suffix array is typical solution for this problem.
 * The fastest way is to copy a template form the Internet.
 * The code will be quite long.
 * Here I want to share a binary search solution.
 * 
 * 
 * Explanation
 * Binary search the length of longest duplicate substring and call the help function test(L).
 * test(L) slide a window of length L,
 * rolling hash the string in this window,
 * record the seen string in a hashset,
 * and try to find duplicated string.
 * 
 * I give it a big mod for rolling hash and it should be enough for this problem.
 * Actually there could be hash collision.
 * One solution is to have two different mod for hash.
 * Or we can use a hashmap to record the index of string.
 * 
 * 
 * Complexity
 * Time: Overall O(NlogN). Binary Search in range 1 and N, so it's O(logN). Rolling hash O(N).
 * Space: O(N)
 */
class Solution {
    public String longestDupSubstring(String S) {
        // edge case
        if (S == null) return null;
        // binary search the max length
        int min = 0;
        int max = S.length() - 1;
        int mid;
        while (min + 1 < max) {
            mid = (min + max) / 2;
            if (searchForLength(S, mid) != null) {
                min = mid;
            } else {
                max = mid - 1;
            }
        }
        String str = searchForLength(S, max);
        if (str != null)
            return str;
        else
            return searchForLength(S, min);
    }
    
    private String searchForLength(String str, int len) {
        // rolling hash method
        if (len == 0)
            return "";
        else if (len >= str.length())
            return null;
        
        Map<Long, List<Integer>> map = new HashMap<>(); // hashcode -> list of all starting idx with identical hash
        long PRIME = (1 << 31) - 1; // prime number
        long BASE = 256;
        long hash = 0;
        for (int i = 0; i < len; ++i) {
            hash = (hash * BASE + str.charAt(i)) % PRIME;
        }
        long multiplier = 1;
        for (int i = 1; i < len; i++) {
            multiplier = (multiplier * BASE) % PRIME;
        }
        // first substring
        List<Integer> equalHashIdx = new ArrayList<Integer>();
        equalHashIdx.add(0);
        map.put(hash, equalHashIdx);
        // other substrings
        int from = 0, to = len;
        while (to < str.length()) {
            // add hash with PRIME before the subtraction to avoid negative value
            hash = ((hash + PRIME - multiplier * str.charAt(from) % PRIME) * BASE + str.charAt(to)) % PRIME;
            from++;
            to++;
            equalHashIdx = map.get(hash);
            if (equalHashIdx == null) {
                equalHashIdx = new ArrayList<Integer>();
                map.put(hash, equalHashIdx);
            } else {
                for (int i0: equalHashIdx) {
                    if (str.substring(from, to).equals(str.substring(i0, i0 + len)))
                        return str.substring(i0, i0 + len);
                }
            }
            equalHashIdx.add(from);
        }
        return null;
    }
}
