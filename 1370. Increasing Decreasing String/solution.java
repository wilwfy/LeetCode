/*
 * My soltion per the official hints on Leetcode
 */
class Solution {
    public String sortString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: s.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0)+1);
        
        //List<Character> res = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        while (!map.isEmpty()) {
            for (char key = 'a'; key <= 'z'; key++) {
                if (map.get(key) != null) {
                    res.append(key);
                    if (map.get(key)-1 == 0)
                        map.remove(key);
                    else
                        map.put(key, map.get(key)-1);
                }
            }
            for (char key = 'z'; key >= 'a'; key--) {
                if (map.get(key) != null) {
                    res.append(key);
                    if (map.get(key)-1 == 0)
                        map.remove(key);
                    else
                        map.put(key, map.get(key)-1);
                }
            }
        }
        return res.toString();
    }
}
