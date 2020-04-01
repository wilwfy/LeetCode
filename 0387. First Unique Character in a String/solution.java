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


/*
 * Official solution of Two Pass with HashMap
 *
 * Time & Space: O(n)
 */
class Solution {
    public int firstUniqChar(String s) {
        if (s == null) return -1;
        if (s.length() == 1) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        // find the index
        for (char c: s.toCharArray()) {
            if (map.get(c) == 1)
                return s.indexOf(c);
        }
        return -1;
    }
}


/*
 * Other's solution of Two Pass with Array
 *
 * Time & Space: O(n)
 */
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
