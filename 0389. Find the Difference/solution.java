/**
 * My solution with Hash
 *
 * Time: O(N).
 * Space: O(1). Hash array with constant length.
 */
class Solution {
    public char findTheDifference(String s, String t) {
        int[] hash = new int[26];
        int i = 0;
        for (; i < s.length(); i++) {
            hash[t.charAt(i) - 'a']++;
            hash[s.charAt(i) - 'a']--;
        }
        hash[t.charAt(i) - 'a']++;
        for (i = 0; i < 26; i++) {
            if (hash[i] > 0) break;
        }
        return (char) ('a' + i);
    }
}


/**
 * Other's solution with Hash
 *
 * Time: O(N).
 * Space: O(1). Hash array with constant length.
 */
class Solution {
    public char findTheDifference(String s, String t) {
        int[] it = new int[26];
        
        for(char cs : s.toCharArray()) {
            it[cs - 'a']++;
        }
        
        for(char ct : t.toCharArray()) {
            it[ct - 'a']--;
            if(it[ct - 'a'] < 0) {
                return ct;
            }
        }
        
        return '0';
    }
}


/**
 * Other's solution with Bit Manipulation
 *
 * Time: O(N).
 * Space: O(1).
 */
class Solution {
    public char findTheDifference(String s, String t) {
    	int n = t.length();
    	char c = t.charAt(n - 1);
    	for (int i = 0; i < n - 1; ++i) {
    		c ^= s.charAt(i);
    		c ^= t.charAt(i);
    	}
    	return c;
    }
}


/**
 * Other's solution with Char Sum
 *
 * Time: O(N).
 * Space: O(1).
 */
class Solution {
    public char findTheDifference(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
              charCode -= (int)s.charAt(i);
              charCode += (int)t.charAt(i); 
        }
        return (char)charCode;
    }
}
