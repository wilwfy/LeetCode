/**
 * My solution with split() and StringBuilder
 *
 * Time: O(N). N is the number of words.
 * Space: O(n). n is the length of the string s.
 */
class Solution {
    public String reverseWords(String s) {
        // String[] words = s.split("\\s+");
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            //System.out.println("index = " + i + ", " + words[i]);
            if (words[i].equals("")) continue;
            if (res.length() != 0) res.append(" ");
            res.append(words[i]);
        }
        return res.toString();
    }
}
