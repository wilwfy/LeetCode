/**
 * Other's solution based on Split by Character
 *
 * Intuition
 * Let's think about how a character can be found as a unique character.
 *
 * Think about string "XAXAXXAX" and focus on making the second "A" a unique character.
 * We can take "XA(XAXX)AX" and between "()" is our substring.
 * We can see here, to make the second "A" counted as a uniq character, we need to:
 * 
 * insert "(" somewhere between the first and second A
 * insert ")" somewhere between the second and third A
 * For step 1 we have "A(XA" and "AX(A", 2 possibility.
 * For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.
 * 
 * So there are in total 2 * 3 = 6 ways to make the second A a unique character in a substring.
 * In other words, there are only 6 substring, in which this A contribute 1 point as unique string.
 * 
 * Instead of counting all unique characters and struggling with all possible substrings,
 * we can count for every char in S, how many ways to be found as a unique char.
 * We count and sum, and it will be out answer.
 * 
 * 
 * Explanation
 * index[26][2] record last two occurrence index for every upper characters.
 * Initialise all values in index to -1.
 * Loop on string S, for every character c, update its last two occurrence index to index[c].
 * Count when loop. For example, if "A" appears twice at index 3, 6, 9 seperately, we need to count:
 * For the first "A": (6-3) * (3-(-1))"
 * For the second "A": (9-6) * (6-3)"
 * For the third "A": (N-9) * (9-6)"
 * 
 * Complexity
 * One pass, time complexity O(N).
 * Space complexity O(1).
 */
class Solution {
    public int uniqueLetterString(String s) {
        int[][] charIdx = new int[26][2];
        for (int i = 0; i < 26; i++) Arrays.fill(charIdx[i], -1);
        int cnt = 0, N = s.length(), mod = (int)1e9 + 7;
        for (int i = 0; i < N; i++) {
            int c = s.charAt(i) - 'A';
            cnt = (cnt + (i - charIdx[c][1]) * (charIdx[c][1] - charIdx[c][0]) % mod) % mod;
            charIdx[c] = new int[]{charIdx[c][1], i};
        }
        for (int c = 0; c < 26; c++)
            cnt = (cnt + (N - charIdx[c][1]) * (charIdx[c][1] - charIdx[c][0]) % mod) % mod;
        return cnt;        
    }
}


/**
 * Official solution based on Split by Character
 *
 * Intuition
 * 
 * We only need to answer the following question (26 times, one for each character): how many substrings have
 * exactly one \text{"A"}"A"?
 * 
 * Algorithm
 * Consider how many substrings have a specific \text{"A"}"A". For example, let's say S only has three "A"'s,
 * at 'S[10] = S[14] = S[20] = "A"; and we want to know the number of substrings that contain S[14]. The answer
 * is that there are 4 choices for the left boundary of the substring (11, 12, 13, 14), and 6 choices for the
 * right boundary (14, 15, 16, 17, 18, 19). So in total, there are 24 substrings that have S[14] as their unique "A".
 * 
 * Continuing our example, if we wanted to count the number of substrings that have S[10], this would be 10 * 4
 * - note that when there is no more "A" characters to the left of S[10], we have to count up to the left edge of
 * the string.
 * 
 * We can add up all these possibilities to get our final answer.
 *
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(N). We could reduce this to O(A) if we do not store all the indices, but compute the answer on the fly.
 */
class Solution {
    public int uniqueLetterString(String S) {
        Map<Character, List<Integer>> index = new HashMap();
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            index.computeIfAbsent(c, x-> new ArrayList<Integer>()).add(i);
        }

        long ans = 0;
        for (List<Integer> A: index.values()) {
            for (int i = 0; i < A.size(); ++i) {
                long prev = i > 0 ? A.get(i-1) : -1;
                long next = i < A.size() - 1 ? A.get(i+1) : S.length();
                ans += (A.get(i) - prev) * (next - A.get(i));
            }
        }

        return (int) ans % 1_000_000_007;
    }
}
