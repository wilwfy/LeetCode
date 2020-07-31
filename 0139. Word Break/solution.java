/**
 * Other's solution of DP
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
                        if (s.substring(i-str.length(), i).equals(str)) {
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
                if (f[j] && wordDict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
}
