class Trie {
    
    class TrieNode {
        TrieNode[] child;
        boolean end;
        
        public TrieNode() {
            child = new TrieNode[26];
            end = false;
        }
    }
    
    TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (Character c: word.toCharArray()) {
            int i = c - 'a';
            if (cur.child[i] == null)
                cur.child[i] = new TrieNode();
            cur = cur.child[i];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (Character c: word.toCharArray()) {
            int i = c - 'a';
            if (cur.child[i] == null) return false;
            cur = cur.child[i];
        }
        return cur.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (Character c: prefix.toCharArray()) {
            int i = c - 'a';
            if (cur.child[i] == null) return false;
            cur = cur.child[i];
        }
        return true;        
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
