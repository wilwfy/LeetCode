/**
 * My solution of One Pass with HashMap
 *
 * Time: O(n).
 * Space: O(1). At most there are 26 keys in the map.
 */
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        if (s == null || s.length() < 2) return -1;
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c))
                maxLen = Math.max(maxLen, i - map.get(c) - 1);
            else
                map.put(c, i);
        }
        return maxLen;
    }
}
