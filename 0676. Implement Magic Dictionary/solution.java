/**
 * Other's solution with Trie
 */
class MagicDictionary {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isEnd = false;
    }
    
    TrieNode root;
    boolean modified;
    
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
        modified = false;
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        TrieNode p = new TrieNode();
        for (String word: dict) {
            p = root;
            for (char c: word.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.isEnd = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return search(word, 0, false, root);
    }
    
    public boolean search(String word, int idx, boolean modified, TrieNode cur) {
        if (cur == null) return false;
        if (idx == word.length()) return modified && cur.isEnd;
        
        int j = word.charAt(idx) - 'a';
        for (int i = 0; i < 26; i++) {
            if (modified && i != j) continue; // If already modified once, then skip the further
                                              // search on later mismatched characters
            if (search(word, idx+1, modified || i!=j, cur.next[i])) return true;
        }
        return false;
    }
}


/**
 * Other's solution with HashMap
 */
class MagicDictionary {
    Map<Integer, Set<String>> map;
    
    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            int len = s.length();
            if (!map.containsKey(len)) {
                map.put(len, new HashSet<>());
            }
            map.get(len).add(s);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        int len = word.length();
        if (!map.containsKey(len)) {
            return false;
        }
        for (String s : map.get(len)) {
            int count = 0;
            for (int i = 0; i < len; i++) {
                if (word.charAt(i) != s.charAt(i)) {
                    count++;
                }
            }
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
