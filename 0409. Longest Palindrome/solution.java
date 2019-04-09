class Solution {
    public int longestPalindrome(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        Map<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (char c: str) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int len = 0, center = 0;
        for (char c: map.keySet()) {
            int cnt = map.get(c);
            if ((cnt % 2) == 1) {
                center = 1;
                cnt--;
            }
            len += cnt;
        }
        return len + center;
    }
}


/*
 * Official's solution: Greedy
 *
 * For each letter, say it occurs v times. We know we have v // 2 * 2 letters
 * that can be partnered for sure. For example, if we have 'aaaaa', then we 
 * could have 'aaaa' partnered, which is 5 // 2 * 2 = 4 letters partnered.
 *
 * At the end, if there was any v % 2 == 1, then that letter could have been
 * a unique center. Otherwise, every letter was partnered. To perform this check,
 * we will check for v % 2 == 1 and ans % 2 == 0, the latter meaning we haven't
 * yet added a unique center to the answer.
 */
class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1)
                ans++;
        }
        return ans;
    }
}
