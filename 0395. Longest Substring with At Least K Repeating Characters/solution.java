/**
 * Other's solution of Two Pointers with Map based on Array
 *
 * Intuition
 * For each numUniqueTarget (from 1 to 26), apply two pointer technique to find the longest substring
 * with at least K repeating characters and the number of unique characters in substring is numUniqueTarget
 *
 */
class Solution {
    public int longestSubstring(String s, int k) {
        int longestCnt = 0;
        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
            longestCnt = Math.max(longestCnt, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));
        
        return longestCnt;
    }
    
    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        int numUnique = 0; // counter 1
        int numNoLessThanK = 0; // counter 2
        int start = 0, end = 0;
        int max = 0;
        
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map[c1] == 0) numUnique++; // increase map[c1] after this statement
            map[c1]++;
            if (map[c1] == k) numNoLessThanK++; // increase end after this statement
            end++;
            
            while (numUnique > numUniqueTarget) {
                char c2 = s.charAt(start);
                if (map[c2] == k) numNoLessThanK--; // decrease map[c2] after this statement
                map[c2]--;
                if (map[c2] == 0) numUnique--; // increase start after this statement
                start++;
            }
            
            // if we found a string where the number of unique chars equals our target
            // and all those chars are repeated at least K times then update max
            if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                max = Math.max(max, end - start);
        }
        return max;
    }
}
