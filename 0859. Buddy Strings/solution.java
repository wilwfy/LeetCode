/**
 * My solution of One Pass with HashMap
 *
 * Time: O(N).
 * Space: O(1). Constant space for array letterCnt and hashmap map (whose size will not exceed 2)
 */
class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.length() < 2) return false;
        int[] letterCnt = new int[26];
        int maxCnt = 0;
        int diffCnt = 0;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char c1 = A.charAt(i);
            letterCnt[c1 - 'a']++;
            maxCnt = Math.max(maxCnt, letterCnt[c1 - 'a']);
            
            char c2 = B.charAt(i);
            if (c1 != c2) {
                diffCnt++;
                if (diffCnt > 2) return false;
                if (!map.isEmpty()) {
                    // make sure A and B are equal after the swapping in case like:
                    // A -- "abcaa", B -- "abcbb"
                    if (!map.containsKey(c2) || map.get(c2) != c1)
                        return false;
                }
                map.put(c1, c2);
            }
        }
        if (diffCnt == 2) return true;
        if (diffCnt == 1) return false;
        // If A equals to B, then check if there is a letter whose count is no less than 2.
        return (maxCnt >= 2) ? true : false;
    }
}


/**
 * Official solution of Enumerate Cases with Two Pointers
 *
 * Time: O(N).
 * Space: O(1).
 */
class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c: count)
                if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first));
        }
    }
}


/**
 * Other's solution of One Pass with List
 *
 * Explanation
 *
 * If A.length() != B.length(): no possible swap
 * 
 * If A == B, we need swap two same characters. Check is duplicated char in A.
 * 
 * In other cases, we find index for A[i] != B[i]. There should be only 2 diffs and it's our one swap.
 *
 * Time: O(N).
 * Space: O(1).
 */
class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            Set<Character> s = new HashSet<Character>();
            for (char c : A.toCharArray()) s.add(c);
            return s.size() < A.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) if (A.charAt(i) != B.charAt(i)) dif.add(i);
        return dif.size() == 2 && A.charAt(dif.get(0)) == B.charAt(dif.get(1)) && A.charAt(dif.get(1)) == B.charAt(dif.get(0));
    }
}
