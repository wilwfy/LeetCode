/**
 * Other's DFS solution with Trie
 */
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        
        TrieNode root = new TrieNode();
        // construct Trie tree
        for (String word: words) {
            if (word.length() == 0) continue;
            addWord(word, root);
        }
        
        // test word is a concatenated word or not
        for (String word: words) {
            if (word.length() == 0) continue;
            if (testWord(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }
        
        return res;
    }
    
    // count means how many words during the search path
    public boolean testWord(char[] chars, int index, TrieNode root, int count) {
        TrieNode cur = root;
        int n = chars.length;
        for (int i = index; i < n; i++) {
            if (cur.sons[chars[i] - 'a'] == null) return false;
            if (cur.sons[chars[i] - 'a'].isEnd) {
                if (i == n - 1) // no next word, so test count to get result.
                    return count >= 1; // need be a word combined by 2 shorter words at least
                
                if (testWord(chars, i + 1, root, count + 1))
                    return true;
            }
            cur = cur.sons[chars[i] - 'a'];
        }
        return false;
    }
    
    public void addWord(String str, TrieNode root) {
        char[] chars = str.toCharArray();
        TrieNode cur = root;
        for (char c: chars) {
            if (cur.sons[c - 'a'] == null)
                cur.sons[c - 'a'] = new TrieNode();
            
            cur = cur.sons[c - 'a'];
        }
        cur.isEnd = true;
    }
    
    class TrieNode {
        TrieNode[] sons;
        boolean isEnd;
        public TrieNode() {
            sons = new TrieNode[26];
        }
    }
}


/**
 * Other's solution of DP
 *
 * Intuition
 * this problem is similiar with problem 139. Word Break
 * 
 * If you do know one optimized solution for above question is using DP, this problem is just one more step further.
 * We iterate through each word and see if it can be formed by using other words.
 * 
 * Of course it is also obvious that a word can only be formed by words shorter than it. So we can first sort the
 * input by length of each word, and only try to form one word by using words in front of it.
 *
 * Time: slower than the DFS solution with Trie
 */
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        
        Arrays.sort(words, (s1, s2) -> (s1.length() - s2.length()));
        
        for (String word: words) {
            if (canForm(word, preWords)) {
                res.add(word);
            }
            preWords.add(word);
        }
        
        return res;
    }
    
    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true; // the word defaultly contains the empty string ""
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}


/**
 * Other's DFS solution with HashSet
 */
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // we sort the words array by length of the words
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
        
        // we maintain a set of words already seen so that it is easy for us to chek the prefix and see if the word can be made
        Set <String> set = new HashSet<> ();
        List<String> res = new ArrayList<> ();
        
        // we check every word if it can be made by the other words if yes then we add to res
        // we add every word to set to check if it can be used to make other word
        for (String word : words) {
            if (canBeFormed(word, set)) {
                res.add(word);
            }
            // add every word in the prefix set
            set.add(word);
        }
        
        return res;
    }
    
    public boolean canBeFormed (String word, Set <String> set) {
        // if word is already present in the set then return
        // In case of being contained by the set, the word here is only possible to be a substring of a longer word
        if (set.contains(word)) return true;
        
        // else check each substring on the word,
        // starting from 0-1 if it is present then check if rest substring is present 
        // if both are present that means the word can be formed by using the prefixes in the string
        for (int i = 1; i <= word.length(); i++) {
            if (set.contains(word.substring(0, i))) {
                // then check if the set has rest of the substing
                if (canBeFormed(word.substring(i), set)) return true;
            }
        }
        return false;
    }
}
