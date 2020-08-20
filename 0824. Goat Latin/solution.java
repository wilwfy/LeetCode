/**
 * My solution
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String toGoatLatin(String S) {
        StringBuilder sb = new StringBuilder();
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        String[] words = S.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String aSuffix = "a";
            char ch = words[i].charAt(0);
            if (vowelSet.contains(ch))
                sb.append(words[i] + "ma" + aSuffix.repeat(i+1) + " ");
            else
                sb.append(words[i].substring(1) + ch + "ma" + aSuffix.repeat(i+1) + " ");
        }
        return sb.toString().substring(0, sb.length()-1);
    }
}


/**
 * Other's solution
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String toGoatLatin(String S) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        String res = "";
        int i = 0, j = 0;
        for (String w : S.split("\\s")) {
            res += ' ' + (vowels.contains(w.charAt(0)) ? w : w.substring(1) + w.charAt(0)) + "ma";
            for (j = 0, ++i; j < i; ++j) {
                res += "a";
            }
        };
        return res.substring(1);
    }
}
