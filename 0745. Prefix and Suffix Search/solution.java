class WordFilter {
    class TrieNode {
        TrieNode[] children;
        int weight;
        public TrieNode() {
            children = new TrieNode[27];
            weight = -1;
        }
    }
    
    private TrieNode root;
    
    public WordFilter(String[] words) {
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            for (int j = words[i].length(); j>=0; j--) {
                TrieNode cur = root;
                StringBuilder newWord = new StringBuilder();
                if (j < words[i].length())
                    newWord.append(words[i].substring(j));
                newWord.append("#");
                newWord.append(words[i]);
                char[] arr = newWord.toString().toCharArray();
                for (char c: arr) {
                    int pos = (c == '#') ? 26 : (c - 'a');
                    if (cur.children[pos] == null)
                        cur.children[pos] = new TrieNode();
                    cur.weight = (i > cur.weight) ? i : cur.weight;
                    cur = cur.children[pos];
                }
                cur.weight = (i > cur.weight) ? i : cur.weight;
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        TrieNode cur = root;
        StringBuilder pattern = new StringBuilder();
        pattern.append(suffix);
        pattern.append("#");
        pattern.append(prefix);
        for (char c: pattern.toString().toCharArray()) {
            int pos = (c == '#') ? 26 : (c - 'a');
            if (cur.children[pos] == null)
                return -1;
            else
                cur = cur.children[pos];
        }
        return cur.weight;
    }
}


/*
 * Official solution: Trie of Suffix Wrapped Words
 *
 * Time Complexity: O(N*K^2 + QK) where N is the number of words, K is the maximum length of a word,
 *                  and Q is the number of queries.
 * Space Complexity: O(N*K^2), the size of the trie.
 */
class WordFilter {
    TrieNode trie;
    public WordFilter(String[] words) {
        trie = new TrieNode();
        for (int weight = 0; weight < words.length; ++weight) {
            String word = words[weight] + "{";
            for (int i = 0; i < word.length(); ++i) {
                TrieNode cur = trie;
                cur.weight = weight;
                for (int j = i; j < 2 * word.length() - 1; ++j) {
                    int k = word.charAt(j % word.length()) - 'a';
                    if (cur.children[k] == null)
                        cur.children[k] = new TrieNode();
                    cur = cur.children[k];
                    cur.weight = weight;
                }
            }
        }
    }
    public int f(String prefix, String suffix) {
        TrieNode cur = trie;
        for (char letter: (suffix + '{' + prefix).toCharArray()) {
            if (cur.children[letter - 'a'] == null) return -1;
            cur = cur.children[letter - 'a'];
        }
        return cur.weight;
    }
}

class TrieNode {
    TrieNode[] children;
    int weight;
    public TrieNode() {
        children = new TrieNode[27];
        weight = 0;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
