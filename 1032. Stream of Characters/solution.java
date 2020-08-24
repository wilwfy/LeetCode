/**
 * Other's solution of Trie with reverse order
 *
 * Time complexity: O(W * Q). W = max(words.length),the maximum length of all words.
 *                         N = words.size, the number of words.
 *                         Q, the number of calls of function query.
 */
class StreamChecker {
    // Store the words in the trie with reverse order, and check the query string from the end:
    // do checking from newest letter -> oldest letter
    class TrieNode {
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }
    
    TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();
    
    public StreamChecker(String[] words) {
        createTrie(words);
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
            char c = sb.charAt(i);
            node = node.next[c - 'a'];
            if (node != null && node.isWord)
                return true;
        }
        return false;
    }
    
    private void createTrie(String[] words) {
        for (String s: words) {
            TrieNode node = root;
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (node.next[c - 'a'] == null)
                    node.next[c - 'a'] = new TrieNode();
                
                node = node.next[c - 'a'];
            }
            node.isWord = true;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
