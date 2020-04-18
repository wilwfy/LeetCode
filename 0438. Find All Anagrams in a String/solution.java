/*
 * My solution of Brute Force with HashMap (Accepted but low performance)
 *
 * Time: 2078 ms
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<Integer>();

        Map<String, Integer> map = new HashMap<>();
        char[] c = p.toCharArray();
        Arrays.sort(c);
        map.put(String.valueOf(c), -1);
        
        List<Integer> res = new ArrayList<>();
        int N = p.length();
        for (int i = 0; i < s.length() - N + 1; i++) {
            String str = s.substring(i, i+N);
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            //System.out.println(ch);
            if (map.containsKey(String.valueOf(ch)))
                res.add(i);
        }
        return res;
    }
}


/*
 * Other's solution of O(n + m) with Hash
 *
 * Time: 6 ms. O(n + m). n is the length of string s,  m is the length of string p.
 * Space: O(1). Array with constant length.
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if ( (s == null) || (s.length() == 0) || (p == null) || (p.length() == 0) || (s.length() < p.length()) ) return res;

        //record each character in p to hash
        int[] hash = new int[256];  // character hash
        for (char c: p.toCharArray())
            hash[c]++;
        
        //move right everytime, if the character exists in p's hash, decrease the count
        //current hash value > 0 means the character is existing in p
        int left = 0, right = 0; // Two pointers for slide window
        int N = p.length(), diffSize = N; // window size
        char tmp;
        while (right < s.length()) {
            tmp = s.charAt(right);
            if (hash[tmp] > 0) {
                diffSize--;
            }
            hash[tmp]--;
            right++;
            
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (diffSize == 0)
                res.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == N) {
                tmp = s.charAt(left);
                if (hash[tmp] >= 0)// this char is in anagram
                    diffSize++;
                hash[tmp]++; // resume the count before moving the window
                left++;
            }
        }
        return res;
    }
}
