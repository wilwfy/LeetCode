/**
 * My solution of HashSet
 *
 * Time: O(J + S)
 * Space: O(J)
 */
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char c: J.toCharArray())
            set.add(c);
        int res = 0;
        for (char c: S.toCharArray()) {
            if (set.contains(c))
                res++;
        }
        return res;
    }
}


/**
 * Other's solution without HashSet
 *
 * But its time complexity is greater than the approach of HashSet, 
 * because the String.contains() take O(n) however HashSet.contains() just O(1)
 *
 * Time: O(J * S)
 * Space: O(1)
 */
class Solution {
    public static int numJewelsInStones(String j, String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (j.contains(c + "")) count++;
        }
        return count;
    }
}


/**
 * Other's solution with Array
 *
 * But the performance of Time and Space are not better than the approach of HashSet according to LeetCode running result
 *
 * Time: O(J + S)
 * Space: O(1). Array with constant length
 */
class Solution {
    public int numJewelsInStones(String J, String S) {
        int[] cnt = new int[128];
        for (char c : S.toCharArray())
            cnt[c]++;
        int ans = 0;
        for (char c : J.toCharArray())
            ans += cnt[c];
        return ans;
    }
}
