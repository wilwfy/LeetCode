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


/*
 * Other's solution
 */
class WordDictionary {
    private static final int R = 26;
    private Node root;
    private static class Node {
        boolean isEnd = false;
        Node[] next = new Node[R];
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root = insert(word, root, 0);
    }
    
    private Node insert(String word, Node x, int d) {
        if (x == null) x = new Node();
        if (d == word.length()) {
            x.isEnd = true; return x;
        }
        char c = word.charAt(d);
        x.next[c - 'a'] = insert(word, x.next[c - 'a'], d + 1);
        return x;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root, 0);
    }
    
    private boolean search(String pat, Node x, int d) {
        if (x == null) return false;
        if (d == pat.length()) return x.isEnd;
        char next = pat.charAt(d);
        boolean ans = false;
        for (char c = 0; c < R; c++)
            if (next == '.' || c == next - 'a')
                ans = ans || search(pat, x.next[c], d + 1);
        return ans;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
