/**
 * My solution with split()
 */
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split("\\s"); // s.split(" ");
        if (words.length == 0)
            return 0;
        else
            return words[words.length - 1].length();
    }
}


/**
 * Other's solution with Pointer
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0, i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ')
            i--;
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }
        return len;
    }
}
