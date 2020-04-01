/*
 * My solution of Two Pass with HashMap
 *
 * Time & Space: O(n)
 */
class Solution {
    public int firstUniqChar(String s) {
        if (s == null) return -1;
        if (s.length() == 1) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        for (char c: s.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, -1);
            else
                map.put(c, i);
            i++;
        }
        
        int minIndex = Integer.MAX_VALUE;
        for (char c: map.keySet()) {
            if (map.get(c) != -1)
                minIndex = Math.min(minIndex, map.get(c));
        }
        return minIndex < Integer.MAX_VALUE ? minIndex : -1;
    }
}
