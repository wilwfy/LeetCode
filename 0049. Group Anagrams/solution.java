/*
 * My solution with HashMap
 *
 * Performance: 8 ms
 * Time : O(n * mlogm). n is the length of input array. m is the length of the string element of the input array.
 * Space : O(n). 
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
