/**
 * My solution with HashMap
 *
 * Time: O(m + n). m is the length of String magazine, n is the length of String ransomNote
 * Space: O(m).
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null) return true;
        if ((magazine == null) || (ransomNote.length() > magazine.length()))
            return false;
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c: magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (char c: ransomNote.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0)
                return false;
            else
                map.put(c, map.get(c)-1);
        }
        return true;
    }
}


/**
 * Other's solution with Array
 */
public boolean canConstruct(String ransomNote, String magazine) {
    int[] table = new int[128];
    for (char c : magazine.toCharArray())   table[c]++;
    for (char c : ransomNote.toCharArray())
        if (--table[c] < 0) return false;
    return true;
}


/**
 * Other's solution with Array
 */
public boolean canConstruct2(String ransomNote, String magazine) {
    int[] table = new int[26];
    for (char c : magazine.toCharArray())   table[c - 'a']++;
    for (char c : ransomNote.toCharArray())
        if (--table[c - 'a'] < 0) return false;
    return true;
}
