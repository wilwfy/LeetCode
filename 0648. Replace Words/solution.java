/**
 * Official solution with HashSet
 *
 * Algorithm
 * Store all the roots in a Set structure. Then for each word, look at successive prefixes of that word. If you find a prefix that
 * is a root, replace the word with that prefix. Otherwise, the prefix will just be the word itself, and we should add that to the
 * final sentence answer.
 *
 * Time Complexity: O(∑w_i^2) where w_i is the length of the i-th word. We might check every prefix, the i-th of which is O(w_i^2) work.
 * Space Complexity: O(N) where N is the length of our sentence; the space used by rootset.
 */
class Solution {
    public String replaceWords(List<String> roots, String sentence) {
        Set<String> rootset = new HashSet();
        for (String root: roots) rootset.add(root);

        StringBuilder ans = new StringBuilder();
        for (String word: sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootset.contains(prefix)) break;
            }
            if (ans.length() > 0) ans.append(" ");
            ans.append(prefix);
        }
        return ans.toString();
    }
}


/**
 * My solution with Trie
 */
class Solution {
    class TrieNode {
        TrieNode[] next;
        String word;
        
        public TrieNode() {
            next = new TrieNode[26];
            word = null;
        }
    }
    
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        buildDictionary(root, dict);
        
        StringBuilder res = new StringBuilder();
        String[] words = sentence.split("\\s+");
        for (String wd: words) {
            String queryResult = queryWord(root, wd);
            if (queryResult.equals(""))
                res.append(wd);
            else
                res.append(queryResult);
            res.append(" ");
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
    
    public String queryWord(TrieNode root, String str) {
        TrieNode cur = root;
        for (char c: str.toCharArray()) {
            int i = c - 'a';
            if (cur.next[i] == null) return "";
            cur = cur.next[i];
            if (cur.word != null) return cur.word;
        }
        return "";
    }
    
    public void buildDictionary(TrieNode root, List<String> dict) {
        TrieNode cur = new TrieNode();
        for (String w: dict.stream().toArray(String[]::new)) {
            cur = root;
            for (char c: w.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) cur.next[i] = new TrieNode();
                cur = cur.next[i];
            }
            cur.word = w;
        }
        //return root;
    }
}


/**
 * Official solution with Trie
 *
 * Time Complexity: O(N) where N is the length of the sentence. Every query of a word is in linear time.
 * Space Complexity: O(N), the size of our trie.
 */
class Solution {
    public String replaceWords(List<String> roots, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root: roots) {
            TrieNode cur = trie;
            for (char letter: root.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word: sentence.split("\\s+")) {
            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = trie;
            for (char letter: word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    TrieNode() {
        children = new TrieNode[26];
    }
}



