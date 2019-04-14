/*
 * Brute-force
 */
class Solution {
    public String longestWord(String[] words) {
        if (words.length == 0) return "";
        Set<String> set = new HashSet<>();
        String res = "";
        for (String str: words) {
            set.add(str);
        }
        for (String str: words) {
            boolean find = true;
            if ((str.length() > res.length()) || ((str.length() == res.length()) && (str.compareTo(res) < 0))) {
                for (int i = 1; i < str.length(); i++) {
                    if (!set.contains(str.substring(0,i))) {
                        find = false;
                        break;
                    }
                }
                if (find) res = str;
            }
        }
        return res;
    }
}


/*
 * Official's brute-force solution
 *
 * Time complexity : O(∑(wi ^ 2)), where wi is the length of words[i]. 
 *                   Checking whether all prefixes of words[i] are in the set is O(∑(wi ^ 2)).
 * Space complexity : O(∑(wi ^ 2)) to create the substrings.
 */
class Solution {
    public String longestWord(String[] words) {
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        Arrays.sort(words, (a, b) -> a.length() == b.length()
                    ? a.compareTo(b) : b.length() - a.length());
        for (String word: words) {
            boolean good = true;
            for (int k = 1; k < word.length(); ++k) {
                if (!wordset.contains(word.substring(0, k))) {
                    good = false;
                    break;
                }
            }
            if (good) return word;
        }

        return "";
    }
}


/*
 * Other's sorting solution
 */
class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }
}


/*
 * Official's solution by using Trie + Depth-First Search
 *
 * Time Complexity: O(∑wi), where wi is the length of words[i]. This is the complexity to build the trie and to search it.
 *
 * If we used a BFS instead of a DFS, and ordered the children in an array, we could drop the need to check whether the
 * candidate word at each node is better than the answer, by forcing that the last node visited will be the best answer.
 *
 * Space Complexity: O(∑wi), the space used by our trie.
 */
class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word: words) {
            trie.insert(word, ++index); //indexed by 1
        }
        trie.words = words;
        return trie.dfs();
    }
}
class Node {
    char c;
    HashMap<Character, Node> children = new HashMap();
    int end;
    public Node(char c){
        this.c = c;
    }
}

class Trie {
    Node root;
    String[] words;
    public Trie() {
        root = new Node('0');
    }

    public void insert(String word, int index) {
        Node cur = root;
        for (char c: word.toCharArray()) {
            cur.children.putIfAbsent(c, new Node(c));
            cur = cur.children.get(c);
        }
        cur.end = index;
    }

    public String dfs() {
        String ans = "";
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.empty()) {
            Node node = stack.pop();
            if (node.end > 0 || node == root) {
                if (node != root) {
                    String word = words[node.end - 1];
                    if (word.length() > ans.length() ||
                            word.length() == ans.length() && word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }
                for (Node nei: node.children.values()) {
                    stack.push(nei);
                }
            }
        }
        return ans;
    }
}
