/**
 * Other's solution with using ASCII values
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        // ASCII Value:
        // A - 65, Z - 90;
        // a - 97, z - 122
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
}


/**
 * Official solution with using Regex
 *
 * Time: Basically O(n), but depends on implementation.
 * Space: O(1). We only need constant spaces to store our pattern.
 */
class Solution {
    public boolean detectCapitalUse(String word) {
        // the '.' should be one character in either Upper case or Lower case
        return word.matches("[A-Z]*|.[a-z]*");
    }
}
