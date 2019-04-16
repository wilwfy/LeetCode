class WordDictionary {
    class TrieNode {
        TrieNode[] child;
        boolean isEnd;
        public TrieNode() {
            child = new TrieNode[26];
            isEnd = false;
        }
    }
    
    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new TrieNode();
            }
            cur = cur.child[c - 'a'];
        }
        cur.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word, root);
    }
    
    public boolean helper(String word, TrieNode cur) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                int empty = 1;
                boolean res = false;
                for (TrieNode node: cur.child) {
                    if (node != null) {
                        empty = 0;
                        cur = node;
                        if (helper(word.substring(i+1), cur)) return true;
                    }
                }
                if (empty == 1)
                    return false;
                else
                    return res;
            } else {
                if (cur.child[c - 'a'] == null)
                    return false;
                else
                    cur = cur.child[c - 'a'];
            }
        }
        return (cur.isEnd) ? true : false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
