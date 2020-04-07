/*
 * My solution with HashMap
 *
 * Performance: 8 ms
 * Time : O(n * mlogm). n is the length of input array. m is the length of the string element of the input array.
 * Space : O(n * m). 
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<List<String>>(){};
        
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String chString = String.valueOf(ch);
            if (map.containsKey(chString)) {
                List<String> tmp = map.get(chString);
                tmp.add(str);
                map.put(chString, tmp);
            } else
                map.put(chString, new ArrayList<String>(List.of(str)));
        }
        
        List<List<String>> res = new ArrayList<>();
        for (String key: map.keySet()) {
            List<String> group = map.get(key);
            res.add(group);
        }
        return res;
    }
}


/*
 * Official solution of Categorize by Sorted String
 *
 * Time Complexity: O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs. The outer loop
 *                  has complexity O(N) as we iterate through each string. Then, we sort each string in O(KlogK) time.
 * Space Complexity: O(NK), the total information content stored in ans. 
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}


/*
 * Official solution of Categorize by Count
 *
 * The hashable representation of our count will be a string delimited with '#' characters.
 * For example, abbccc will be #1#2#3#0#0#0...#0
 *
 * Time Complexity: O(NK), where N is the length of strs, and K is the maximum length of a string in strs. Counting each string
 *                  is linear in the size of the string, and we count every string.
 * Space Complexity: O(NK), the total information content stored in ans. 
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
