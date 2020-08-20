/**
 * Other's solution of One Pass with HashMap
 *
 * Explanation
 * cur records the count of "aeiou"
 * cur & 1 = the records of a % 2
 * cur & 2 = the records of e % 2
 * cur & 4 = the records of i % 2
 * cur & 8 = the records of o % 2
 * cur & 16 = the records of u % 2
 * seen note the index of first occurrence of cur
 * 
 * Note that we don't really need the exact count number,
 * we only need to know if it's odd or even.
 * 
 * If it's one of aeiou,
 * 'aeiou'.find(c) can find the index of vowel,
 * cur ^= 1 << 'aeiou'.find(c) will toggle the count of vowel.
 * 
 * But for no vowel characters,
 * 'aeiou'.find(c) will return -1,
 * that's reason that we do 1 << ('aeiou'.find(c) + 1) >> 1.
 * 
 * More detailed explanation:
 * Let me try to explain a bit more. Is the count of "aeiou" matters? No, indeed. Only the mod of count will
 * contribute to the result. Consider at index i, we have below counting mods where '+' means even and '-' means odd.
 * 
 * a e i o u
 * + - + - +
 * 
 * Then what we should do is just find another same mods pattern "+-+-+" with index j (j < i), then the sequence
 * between (j, i] is guaranteed to have all vowels' counting even (while other patterns are definitely not qualified).
 * To make the sequence longest, we should find the smallest j and that's why Map::putIfAbsent is used.
 * 
 * cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1 ) >> 1;
 * 
 * This concise code is just inversing a certain bit of cur:
 * 
 *                                        a       e       i       o       u       other
 * "aeiou".indexOf(s.charAt(i)) + 1       1       2       3       4       5       0
 * 1 << tmp                               2       4       8      16      32       1
 * (1 << tmp) >> 1                        1       2       4       8      16       0
 * So cur ^= something should be cur ^= 1, cur ^= 2, cur ^= 4, and so on.
 *
 * Complexity
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public int findTheLongestSubstring(String s) {
        int res = 0, cur = 0, n = s.length(); // cur records the count of "aeiou"
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1); // the default substring length which starts at index 0 is 1 = 0 - (-1)
        for (int i = 0; i < n; i++) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1) >> 1; // for no vowel characters, 'aeiou'.find(c) will return -1
            seen.putIfAbsent(cur, i); // use putIfAbsent() to record the smallest index where the same cur occurs
            res = Math.max(res, i - seen.get(cur));
        }
        return res;
    }
}
