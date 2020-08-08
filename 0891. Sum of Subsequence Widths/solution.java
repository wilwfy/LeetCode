/**
 * Other's Mathematical solution
 *
 * Explanation
 * The order in initial arrays doesn't matter,
 * my first intuition is to sort the array.
 * 
 * For each number A[i]:
 * 
 * There are i smaller numbers,
 * so there are 2 ^ i sequences in which A[i] is maximum.
 * we should do res += A[i] * (2 ^ i)
 * 
 * There are n - i - 1 bigger numbers,
 * so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.
 * we should do res -= A[i] * 2 ^ (n - i - 1)
 * 
 * Done.
 * 
 * Time Complexity: O(NlogN)
 * Space Complexity: O(1)
 * 
 * FAQ
 * Q. why do we plus mod before return?
 * A In Cpp and Java, mod on negative number will still get a negative number.
 */
class Solution {
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long sum = 0, seqNumber = 1, mod = (long)1e9 + 7;
        for (int i = 0, n = A.length; i < n; i++) {
            // (1) i:= 0 to n-1 sum Ai * 2^(n - 1 - i) = (A0 * 2^n-1) + (A1 * 2^n-2) + (A2 * 2^n-3) + ... (An-1 * 2^0)
            // (2) i:= 0 to n-1 sum An-i-1 * 2^i = (An-1 * 2^0) + (An-2 * 2^1) + ... (A1 * 2^n-2) + (A0 * 2^n-1)
            // Eq. (1) == (2) in reverse order.
            sum = (sum + A[i] * seqNumber - A[n - i - 1] * seqNumber) % mod;
            seqNumber = (seqNumber * 2) % mod;
        }
        return (int)((sum + mod) % mod); // let sum + mod to avoid negative value
   }
}
