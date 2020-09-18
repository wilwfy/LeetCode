/**
 * Official solution with Sort by Count
 *
 * Algorithm
 * 
 * Find the count of each character, and use it to sort the string by count.
 * 
 * If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task
 * is impossible.
 * 
 * Otherwise, interleave the characters in the order described above.
 *
 * Time Complexity: O(A(N+logA)), where N is the length of S, and A is the size of the alphabet. In Java,
 *                  our implementation is O(N+AlogA). If A is fixed, this complexity is O(N).
 * Space Complexity: O(N). In Java, our implementation is O(N+A).
 */
class Solution {
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }
}
