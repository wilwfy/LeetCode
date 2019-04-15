/*
 * Solution
 * Trie, initiate time O(nl), input time O(k log k)
 * n: total sentences count
 * l: max sentence length
 * k: indicating the options available for the hot sentences
 * 题目很长，但不太难，用Trie即可解决，不过需要注意：
 * 
 * 在input中，需要边读边写，但注意一定是先读再写，否则会查出刚插进去的sentence
 * 遇到'#'时，要返回empty list
 * 由于要返回Trie中存储的sentences，一种做法是给Trie添加一个成员变量str用来存储root到curr路径上形成的str，另外一种做法是在查询时将str作为参数传入child。本题目采用的是后面的思路。
 * 在dfs trie时，一定要现将root添加到结果集，再遍历children！否则会漏掉栈底的root。这点很重要，Trie的查询都要这么写。
 * 在getKHot时，可以用PriorityQueue来做，也可以用List来做然后排序
 * ·
 * 作者：Nancyberry
 * 链接：https://www.jianshu.com/p/6a78f73a57be
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
 
 class AutocompleteSystem {
    private Trie root;
    private Trie curr;
    private String str; // store currently visiting str
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        
        for (int i = 0; i < sentences.length; ++i) {
            insert(root, sentences[i], times[i]);
        }
        
        this.curr = root;
        this.str = "";
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            insert(root, str, 1);
            curr = root;
            str = "";
            return Collections.EMPTY_LIST;  // return empty as designed
        }
        
        int i = getIndex(c);
        if (curr.children[i] == null) {
            curr.children[i] = new Trie();
        }

        str += c;
        curr = curr.children[i];
        return getKHot(curr, str, 3);
    }
    
    private void insert(Trie root, String s, int plusTimes) {
        for (int i = 0; i < s.length(); ++i) {
            int j = getIndex(s.charAt(i));
            if (root.children[j] == null) {
                root.children[j] = new Trie();
            }

            root = root.children[j];
        }

        root.times += plusTimes; // accumulate in case duplicate sentences
    }
    
    private List<String> getKHot(Trie root, String s, int k) {
        List<Pair> list = new ArrayList<>();
        dfs(root, s, list);
        Collections.sort(list, (a, b) 
                         -> (b.times != a.times 
                             ? b.times - a.times : a.str.compareTo(b.str)));
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < Math.min(k, list.size()); ++i) {
            res.add(list.get(i).str);
        }
        
        return res;
    }
    
    private void dfs(Trie root, String s, List<Pair> list) {
        if (root.times > 0) {   // add root first
            list.add(new Pair(s, root.times));
        }
        
        for (char c = 'a'; c <= 'z'; ++c) {
            int i = getIndex(c);
            if (root.children[i] != null) {
                dfs(root.children[i], s + c, list);
            }
        }
        
        if (root.children[26] != null) {
            dfs(root.children[26], s + ' ', list);
        }
    }
    
    private int getIndex(char c) {
        return c == ' ' ? 26 : c - 'a';
    }
    
    class Pair {
        String str;
        int times;
        
        public Pair(String s, int t) {
            str = s;
            times = t;
        }
    }
    
    class Trie {
        Trie[] children;
        int times;
        
        public Trie() {
            children = new Trie[27];
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
