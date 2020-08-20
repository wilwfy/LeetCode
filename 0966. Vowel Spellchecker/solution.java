/**
 * Official solution of HashMap
 *
 * Intuition and Algorithm
 * We analyze the 3 cases that the algorithm needs to consider:
 * - when the query is an exact match,
 * - when the query is a match up to capitalization,
 * - and when the query is a match up to vowel errors.
 * In all 3 cases, we can use a hash table to query the answer.
 *
 * Time Complexity: O(C), where C is the total content of wordlist and queries.
 * Space Complexity: O(C).
 */
class Solution {
    Set<String> words_perfect;
    Map<String, String> words_cap;
    Map<String, String> words_vow;

    public String[] spellchecker(String[] wordlist, String[] queries) {
        words_perfect = new HashSet();
        words_cap = new HashMap();
        words_vow = new HashMap();

        for (String word: wordlist) {
            words_perfect.add(word);

            String wordlow = word.toLowerCase();
            words_cap.putIfAbsent(wordlow, word); // use putIfAbsent() to record the first match

            String wordlowDV = devowel(wordlow);
            words_vow.putIfAbsent(wordlowDV, word); // use putIfAbsent() to record the first match
        }

        String[] ans = new String[queries.length];
        int t = 0;
        for (String query: queries)
            ans[t++] = solve(query);
        return ans;
    }

    public String solve(String query) {
        if (words_perfect.contains(query))
            return query;

        String queryL = query.toLowerCase();
        if (words_cap.containsKey(queryL))
            return words_cap.get(queryL);

        String queryLV = devowel(queryL);
        if (words_vow.containsKey(queryLV))
            return words_vow.get(queryLV);

        return "";
    }

    public String devowel(String word) {
        StringBuilder ans = new StringBuilder();
        for (char c: word.toCharArray())
            ans.append(isVowel(c) ? '*' : c);
        return ans.toString();
    }

    public boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}


/**
 * Official solution of HashMap
 *
 * Intuition and Algorithm
 *
 * For each word in the wordlist,
 * get its the lower pattern and devowel pattern,
 * 
 * For each lower pattern, record the first such match to hashmap cap.
 * For each vowel pattern, record the first such match to hashmap vowel.
 * 
 * For each query,
 * check if it's in the words set,
 * check if there is a match in cap,
 * check if there is a match in vowel,
 * otherwise return "".
 */
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> cap = new HashMap<>();
        Map<String, String> vowel = new HashMap<>();
        for (String w: wordlist) {
            String wLower = w.toLowerCase();
            String wDevowel = wLower.replaceAll("[aeiou]", "#");
            cap.putIfAbsent(wLower, w); // use putIfAbsent() record the first match
            vowel.putIfAbsent(wDevowel, w); // use putIfAbsent() record the first match
        }
        for (int i = 0; i < queries.length; i++) {
            if (words.contains(queries[i])) continue;
            String qLower = queries[i].toLowerCase();
            String qDevowel = qLower.replaceAll("[aeiou]", "#");
            if (cap.containsKey(qLower))
                queries[i] = cap.get(qLower);
            else if (vowel.containsKey(qDevowel))
                queries[i] = vowel.get(qDevowel);
            else
                queries[i] = "";
        }
        return queries;
    }
}
