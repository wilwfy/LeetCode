/**
 * Other's solution
 *
 * Hints
 * what is the relationship between input and output
 * input + output = 111....11 in binary format
 * Is there any corner case?
 * 0 is a corner case expecting 1, output > input
 * Intuition
 * Let's find the first number X that X = 1111....1 > N
 * And also, it has to be noticed that,
 * N = 0 is a corner case expecting1 as result.
 * 
 * 
 * Solution 1:
 * N + bitwiseComplement(N) = 11....11 = X
 * Then bitwiseComplement(N) = X - N
 *
 * Complexity
 * O(logN) Time
 * O(1) Space
 */
class Solution {
    public int bitwiseComplement(int N) {
        int X = 1;
        while (N > X) X = X * 2 + 1;
        return X - N;
    }
}


/**
 * Other's solution
 *
 * Solution 2:
 * N ^ bitwiseComplement(N) = 11....11 = X
 * bitwiseComplement(N) = N ^ X
 */
class Solution {
    public int bitwiseComplement(int N) {
        int X = 1;
        while (N > X) X = X * 2 + 1;
        return N ^ X;
    }
}
