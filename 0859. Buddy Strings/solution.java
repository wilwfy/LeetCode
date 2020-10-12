/**
 * My solution of One Pass with HashMap
 *
 * Time: O(N)
 * Space: O(1). Constant space for array letterCnt and hashmap map (whose size will not exceed 2)
 */
class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.length() < 2) return false;
        int[] letterCnt = new int[26];
        int maxCnt = 0;
        int diffCnt = 0;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char c1 = A.charAt(i);
            letterCnt[c1 - 'a']++;
            maxCnt = Math.max(maxCnt, letterCnt[c1 - 'a']);
            
            char c2 = B.charAt(i);
            if (c1 != c2) {
                diffCnt++;
                if (diffCnt > 2) return false;
                if (!map.isEmpty()) {
                    // make sure A and B are equal after the swapping in case like:
                    // A -- "abcaa", B -- "abcbb"
                    if (!map.containsKey(c2) || map.get(c2) != c1)
                        return false;
                }
                map.put(c1, c2);
            }
        }
        if (diffCnt == 2) return true;
        if (diffCnt == 1) return false;
        // If A equals to B, then check if there is a letter whose count is no less than 2.
        return (maxCnt >= 2) ? true : false;
    }
}
