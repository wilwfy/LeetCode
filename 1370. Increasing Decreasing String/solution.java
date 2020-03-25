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


/*
 * Other's soltion with TreeMap and TreeSet
 *
 * Time: O(nlogn). TreeMap operation.
 * Space: O(n). where n = s.length().
 */
class Solution {
    public String sortString(String s) {
        StringBuilder ans = new StringBuilder();
        TreeMap<Character, Integer> tm = new TreeMap<>();
        for (char c : s.toCharArray()) {
            tm.put(c, 1 + tm.getOrDefault(c, 0));
        }
        boolean asc = true;
        while (!tm.isEmpty()) {
            for (char c : asc ? new TreeSet<>(tm.keySet()) : new TreeSet(tm.descendingKeySet())) {
                ans.append(c);
                tm.put(c, tm.get(c) - 1);
                tm.remove(c, 0);
            }
            asc = !asc; // same as asc ^= true;
        }
        return ans.toString();
    }
}


/*
 * Other's soltion with array int[26] to store 'a' ~ 'z'
 *
 * Time & space: O(n), where n = s.length().
 */
class Solution {
    public String sortString(String s) {
        StringBuilder ans = new StringBuilder();
        int[] count = new int[26];
        for (char c : s.toCharArray())
            ++count[c - 'a'];
        while (ans.length() < s.length()) {
            add(count, ans, true);
            add(count, ans, false);
        }
        return ans.toString();
    }
    private void add(int[] cnt, StringBuilder ans, boolean asc) {
        for (int i = 0; i < 26; ++i) {
            int j = asc ? i : 25 - i;
            if (cnt[j]-- > 0)
                ans.append((char)(j + 'a'));
        }
    }
}


/*
 * Other's more general soltion with array int[256] to store more chars
 *
 * Time & space: O(n), where n = s.length().
 */
class Solution {
    public String sortString(String s) {
        char[] arr = s.toCharArray();
        int[] map = new int[256];
        StringBuilder sb = new StringBuilder();
        
        for (char c : arr){
            map[c]++;
        }
        
        int count = 0;
        while (count < arr.length){
            for (int i = 0; i < 256; i++){
                if (map[i] > 0){
                    sb.append((char)(i));
                    map[i]--;
                    count++;
                }
            }
            
            for (int i = 255; i >= 0; i--){
                if (map[i] > 0){
                    sb.append((char)(i));
                    map[i]--;
                    count++;
                }
            }
        }
        
        return sb.toString();      
    }
}
