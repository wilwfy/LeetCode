/**
 * My solution with Two Pointers
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            while (!isAlphanumeric(chars[i]) && i < j) i++;
            while (!isAlphanumeric(chars[j]) && j > i) j--;
            chars[i] = (chars[i] >= 97) ? (char)(chars[i] - 32) : chars[i];
            chars[j] = (chars[j] >= 97) ? (char)(chars[j] - 32) : chars[j];
            if (chars[i] - chars[j] != 0)
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    public boolean isAlphanumeric(char c) {
        if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122))
            return true;
        else
            return false;
    }
}
