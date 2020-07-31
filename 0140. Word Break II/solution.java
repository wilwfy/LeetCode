/**
 * Other's solution based on memorized DFS with HashMap
 *
 * Using DFS directly will lead to TLE, so just use HashMap to save the previous results to prune duplicated branches
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s,wordDict,new HashMap<String, List<String>>());
    }
    
    // backtrack returns an array including all substrings derived from s.
    public List<String> backtrack(String s, List<String> wordDict, Map<String,List<String>> mem){
        if(mem.containsKey(s)) return mem.get(s);
        List<String> result = new ArrayList<String>();
        for(String word: wordDict)
            if(s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.length()==0) result.add(word);
                else for(String sub: backtrack(next, wordDict, mem)) result.add(word+" "+sub);
            }
        mem.put(s, result);
        return result;
    }
}


/**
 * Other's solution based on memorized DFS with HashMap and reduced recursive calls
 *
 * There are several ways to improve the naive dfs method:
 *   (1) memo using hashmap (like the one above)
 *   (2) DP
 *   (3) preprocess the string using word break I DP array to determine whether to go on or not
 *   (4) precompute the max length of all words in the dictionary to reduce the number of recursive calls.
 * These are all good approaches when not all combinations are valid, but won't change the time complexity O(2^n) in the worse case scenario where all combinations
 * of the string are correct (e,g, s=aaa, dic=[a, aa, aaa]). Some might argue that they reduce the number of recursive/iterative calls to n^2 using memo or DP just
 * like word break I. However, the time complexity of each recursive call in this approach is not linear anymore. Imagine the length of sublist is 2^(n-1). Optimization
 * only happens when the return value is a integer or boolean. This is why we don't use DP/memo to solve subsets/permutation problem because all combinations are valid.
 * In addition, the code below combines (1) and (4) and beats 99% as the solution above suffers the problem that the dictionary size might be too large. 
 */
class Solution {
    // precompute the max and min lengths of all words in the dictionary
    // to reduce the number of recursive calls.
    int maxLen = 0;
    int minLen = Integer.MAX_VALUE;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> hs = new HashSet<>();
        for(String w: wordDict) {
            hs.add(w);
            if (w.length() > maxLen) maxLen = w.length(); //get the maxLen of words
            if (w.length() < minLen) minLen = w.length(); //get the minLen of words
        }
        Map<Integer, List<String>> map = new HashMap<>();
        return helper(hs, s, 0, map);      
    }
    
    public List<String> helper(Set<String> hs, String s, int start, Map<Integer, List<String>> map) {
        if (map.containsKey(start)) return map.get(start);
        List<String> list = new ArrayList<>(); 
        if (start == s.length())  list.add("");
        //reduce the # of iterations using maxLen
        for (int end = start+minLen-1; end<start+maxLen && end<s.length(); end++) {
            if (hs.contains(s.substring(start, end+1))) {
                List<String> nexts = helper(hs, s, end+1, map);
                for(String next:nexts) {
                    if (next == "")//reaches the end
                        list.add(s.substring(start, end+1)+next);
                    else
                        list.add(s.substring(start, end+1) + " " + next);
                }
            }
        }
        map.put(start, list); 
        return list;
    }
}
