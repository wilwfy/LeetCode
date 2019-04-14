/*
 * Brute-force
 */
class Solution {
    public String longestWord(String[] words) {
        if (words.length == 0) return "";
        Set<String> set = new HashSet<>();
        String res = "";
        for (String str: words) {
            set.add(str);
        }
        for (String str: words) {
            boolean find = true;
            if ((str.length() > res.length()) || ((str.length() == res.length()) && (str.compareTo(res) < 0))) {
                for (int i = 1; i < str.length(); i++) {
                    if (!set.contains(str.substring(0,i))) {
                        find = false;
                        break;
                    }
                }
                if (find) res = str;
            }
        }
        return res;
    }
}


/*
 * Official's brute-force solution
 *
 * Time complexity : O(∑(wi ^ 2)), where wi is the length of words[i]. 
 *                   Checking whether all prefixes of words[i] are in the set is O(∑(wi ^ 2)).
 * Space complexity : O(∑(wi ^ 2)) to create the substrings.
 */
class Solution {
    public String longestWord(String[] words) {
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        Arrays.sort(words, (a, b) -> a.length() == b.length()
                    ? a.compareTo(b) : b.length() - a.length());
        for (String word: words) {
            boolean good = true;
            for (int k = 1; k < word.length(); ++k) {
                if (!wordset.contains(word.substring(0, k))) {
                    good = false;
                    break;
                }
            }
            if (good) return word;
        }

        return "";
    }
}
