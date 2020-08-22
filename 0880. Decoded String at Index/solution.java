/**
 * Official solution of Work Backwords
 *
 * Intuition
 * 
 * If we have a decoded string like appleappleappleappleappleapple and an index like K = 24, the answer is
 * the same if K = 4.
 * 
 * In general, when a decoded string is equal to some word with size length repeated some number of times
 * (such as apple with size = 5 repeated 6 times), the answer is the same for the index K as it is for the
 * index K % size.
 * 
 * We can use this insight by working backwards, keeping track of the size of the decoded string. Whenever
 * the decoded string would equal some word repeated d times, we can reduce K to K % (word.length).
 * 
 * Algorithm
 * 
 * First, find the length of the decoded string. After, we'll work backwards, keeping track of size: the
 * length of the decoded string after parsing symbols S[0], S[1], ..., S[i].
 * 
 * If we see a digit S[i], it means the size of the decoded string after parsing S[0], S[1], ..., S[i-1]
 * will be size / Integer(S[i]). Otherwise, it will be size - 1.
 *
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(1).
 */
class Solution {
    public String decodeAtIndex(String S, int K) {
        long size = 0;
        int N = S.length();

        // Find size = length of decoded string
        for (int i = 0; i < N; ++i) {
            char c = S.charAt(i);
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }

        for (int i = N-1; i >= 0; --i) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c))
                size /= c - '0';
            else
                size--;
        }

        throw null;
    }
}


/**
 * Other's solution of Work Backwords
 *
 * Intuition and Algorithm
 * We decode the string and N keeps the length of decoded string, until N >= K.
 * Then we go back from the decoding position.
 * If it's S[i] = d is a digit, then N = N / d before repeat and K = K % N is what we want.
 * If it's S[i] = c is a character, we return c if K == 0 or K == N
 *
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(1).
 */
class Solution {
    public String decodeAtIndex(String S, int K) {
        long N = 0;
        int i;
        for (i = 0; N < K; ++i)
            N = Character.isDigit(S.charAt(i)) ? N * (S.charAt(i) - '0') : N + 1;
        while (i-- > 0) {
            if (Character.isDigit(S.charAt(i))) {
                N /= S.charAt(i) - '0';
                K %= N;
            } else {
                if (K % N == 0) // K == N or K == 0
                    return S.substring(i, i+1);
                N--;
            }
        }
        return "wrong";
    }
}
