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

