/**
 * Other's solution of DP and Two Pass
 *
 * Time Comlexity: O(n^3). n is the length of s.
 *                First DP: [length of s][size of dict][avg length of words in dict]
 *                Second DP: [length of s]^3
 *                BTW, for this kind of problem, which time complexity is [length of s][size of dict][avg length of words in dict]. We can usually remove [size of dict] by
 *                using Tire, remove [avg length of words in dict] by KMP, and what's more, remove both [size of dict] and [avg length of words in dict] by AC-automata.
 * Space Complexity: O(n). n is the length of s.
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        // f[i] stands for whether subarray(0, i), s[0] ~ s[i-1], can be segmented into words from the dictionary.
        // So f[0] means whether subarray(0, 0) (which is an empty string) can be segmented, and
        // of course the answer is yes.
        f[0] = true;
        
        // First DP
        for (int i = 1; i <= s.length(); i++) {
            for (String str: wordDict) {
                if (str.length() <= i) {
                    if (f[i - str.length()]) {
                        if (s.substring(i-str.length(), i).equals(str)) { // Time complexity for substring() is O(1)
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        //Second DP
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++) {
                if (f[j] && wordDict.contains(s.substring(j, i))) { // Time complexity for List.contains() is O(n)
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
}


/**
 * Other's solution of DP with HashSet and One Pass
 *
 * Time Comlexity: O(n^2). n is the length of s.
 * Space Complexity: O(n + m). n is the length of s. m is the length of wordDict
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);
	    
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && set.contains(s.substring(j, i)); // Time complexity for substring() is O(1)
                if(dp[i]) break;
            }
        }
        return dp[s.length()];
    }
}


/**
 * Other's solution of DP with Trie
 *
 * Time Comlexity: O(n^2). n is the length of s.
 * Space Complexity: O(n + 128*Max_word_length)). n is the length of s. Max_word_length is the length of the longest word in wordDict
 */
public class Solution {
    public class TrieNode {
        boolean isWord;
        TrieNode[] c;
        
        public TrieNode(){
            isWord = false;
            c = new TrieNode[128];
        }
    }
    
    public void addWord(TrieNode t, String w) {
        for (int i = 0; i < w.length(); i++) {
            int j = (int)w.charAt(i); 
            if (t.c[j] == null) t.c[j] = new TrieNode();
            t = t.c[j];
        }
        t.isWord = true;
    }
    
    public boolean wordBreak(String s, Set<String> wordDict) {
        TrieNode t = new TrieNode(), cur;
        for (String i : wordDict)
            addWord(t, i);
        
        char[] str = s.toCharArray();
        int len = str.length;
        boolean[] f = new boolean[len + 1];
        f[len] = true;
        
        for (int i = len - 1; i >= 0; i--) {
            //System.out.println(str[i]);
            cur = t;
            for (int j = i; cur != null && j < len ; j++) {
                cur = cur.c[(int)str[j]];
                if (cur != null && cur.isWord && f[j + 1]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }
}
