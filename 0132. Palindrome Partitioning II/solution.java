/**
 * Other's solution of Dynamic Programming
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
class Solution {
    public int minCut(String s) {
        int N = s.length();
        if (N <= 1) return 0;
        // Initialize the 'cut' array: For a string with n characters s[0, n-1], it needs at most n-1 cut. Therefore,
        // the 'cut' array is initialized as cut[i] = i-1
        int[] dpMinCut = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            dpMinCut[i] = i - 1;
        }

        for (int center = 0; center < N; center++) {
            // odd length palindrome
            for (int radius = 0; center - radius >= 0 && center + radius < N && s.charAt(center - radius) == s.charAt(center + radius); radius++)
                // This palindrome can then be represented as s[i-j, i+j]. If this string is indeed a palindrome, then one possible value
                // of cut[i+j] is cut[i-j] + 1, where cut[i-j] corresponds to s[0, i-j-1] and 1 correspond to the palindrome s[i-j, i+j];
                dpMinCut[center + radius + 1] = Math.min(dpMinCut[center + radius + 1], dpMinCut[center - radius] + 1);
            
            // even length palindrome, palindrome center is between [center, center+1]
            for (int radius = 1; center - radius + 1 >= 0 && center + radius < N && s.charAt(center - radius + 1) == s.charAt(center + radius); radius++)
                dpMinCut[center + radius + 1] = Math.min(dpMinCut[center + radius + 1], dpMinCut[center - radius + 1] + 1);
        }
        
        return dpMinCut[N];
    }
}
