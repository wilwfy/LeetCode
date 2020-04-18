/**
 * Official solution of Sliding Window with HashSet
 *
 * Time complexity : O(2n) = O(n). In the worst case each character will be visited twice by i and j.
 * Space complexity : O(min(m, n)). We need O(k) space for the sliding window, where k is the size of the Set. The size of the Set
 *                    is upper bounded by the size of the string n and the size of the charset/alphabet m.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int N = s.length();
        int longestLen = 0, left = 0, right = 0;
        while ((left < N) && (right < N)) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                right++;
                longestLen = Math.max(longestLen, right - left);
            } else {
                c = s.charAt(left);
                set.remove(c);
                left++;
            }
        }
        return longestLen;
    }
}


/**
 * Official solution of Sliding Window with HashMap
 *
 * If s[j] have a duplicate in the range [i, j) with index j', we don't need to increase i little by little. We can skip all the
 * elements in the range [i, j'] and let i to be j' + 1 directly.
 *
 * Time complexity : O(n). Index j will iterate n times.
 * Space complexity (HashMap) : O(min(m, n)). Same as the previous approach.
 * Space complexity (Table): O(m). m is the size of the charset.
 *                           Commonly used tables are: int[26] for Letters 'a' - 'z' or 'A' - 'Z'
 *                                                     int[128] for ASCII
 *                                                     int[256] for Extended ASCII
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int N = s.length();
        int longestLen = 0;
        // try to extend the range [i, j]
        for (int i = 0, j = 0; j < N; j++) { // j is the fast pointer
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                // the value of map.get(c) + 1 could be less than current i
                // for example, if s = "tmmzuxt",
                // when j goes to s.length-1, the i = 2 where the second 'm' locates,
                // but map.get('t') + 1 = 1,
                // Actually we need put i to the rightmost position of any duplicated chars
                i = Math.max(i, map.get(c) + 1);
            }
            map.put(c, j);
            longestLen = Math.max(longestLen, j - i + 1);
        }
        return longestLen;
    }
}
