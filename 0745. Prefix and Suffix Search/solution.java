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

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
