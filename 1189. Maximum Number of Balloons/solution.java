/**
 * Official solution of Counting Characters
 *
 * Time complexity: O(N). Here N is equal to the length of text.
 *                  We iterate over all the characters of string text which requires N operations.
 * 
 * Space complexity: O(1)
 *                   All we need is the 5 variables to store the frequency of characters. Hence the space complexity is constant.
 */
class Solution {
    public int maxNumberOfBalloons(String text) {
        int bCount = 0, aCount = 0, lCount = 0, oCount = 0, nCount = 0;

        // Count the frequencey of all the five characters
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'b') {
                bCount++;
            } else if (text.charAt(i) == 'a') {
                aCount++;
            } else if (text.charAt(i) == 'l') {
                lCount++;
            } else if (text.charAt(i) == 'o') {
                oCount++;
            } else if (text.charAt(i) == 'n') {
                nCount++;
            }
        }
	    
        // Find the potential of each character.
        // Except for 'l' and 'o' the potential is equal to the frequency.
        lCount = lCount / 2;
        oCount = oCount / 2;
        
        // Find the bottleneck.
        return Math.min(bCount, Math.min(aCount, Math.min(lCount, Math.min(oCount, nCount))));
    }
}


/**
 * Official generalized solution using an Array
 *
 * Intuition
 * 
 * As a follow-up exercise, let's consider how we can approach this problem if the word is not guaranteed
 * to be balloonballoon. Suppose we are given an arbitrary string pattern instead of balloonballoon then
 * we can use the same counting characters approach, except we must do so in a more generalized way. Close
 * observation of the previous approach reveals that the potential of each character is equal to the number
 * of instances in text divided by the number of instances in pattern (balloonballoon in the previous approach).
 * So we just need to find the frequency of all the letters in the strings text and pattern. Then the minimum
 * potential of a character will be the answer.
 * 
 * Algorithm
 * 
 *   1. Store the frequency of all the characters in text in freqInText and the frequency of all the characters in
 *      pattern in freqInPattern.
 *   2. Find the potential of all the lowercase English letters. The potential will be equal to its frequency in text
 *      divided by its frequency in pattern.
 *   3. Return the minimum potential of a character.
 *
 * Time complexity: O(N + M). Here N equals the length of text, M equals the length of pattern, and K equals the maximum possible number
 *                  of distinct letters in pattern.
 *                  We traverse over text having length N and over the string pattern of length M to find the frequency
 *                  of each character in each string. Lastly, we traverse over the frequency arrays of length K to find
 *                  the bottleneck character. Since this problem only uses lowercase English letters, K is fixed at 26.
 *                  Hence, we can consider the space complexity to be O(N + M).
 * 
 * Space complexity: O(1)
 *                   The integer arrays used to store the frequency of characters in text and pattern each require O(K)
 *                   space. Since this problem only uses lowercase English letters, K is fixed at 26. Hence, we can
 *                   consider the space complexity to be constant.
 */
class Solution {
    private int findMaxNumberofPattern(String text, String pattern) {
        int n = text.length(), m = pattern.length(), answer = Integer.MAX_VALUE;
        int freqInText[] = new int[26];
        int freqInPattern[] = new int[26];
        
        // Frequency of characters in text.
        for (int i = 0; i < n; i++) {
            freqInText[text.charAt(i) - 'a']++;
        }
        // Frequency of characters in pattern.
        for (int i = 0; i < m; i++) {
            freqInPattern[pattern.charAt(i) - 'a']++;
        }
        
        // Compare the maximum string that can be produced
        // considering one character at a time.
        for (int i = 0; i < 26; i++) {
            // Do not divide by zero.
            if (freqInPattern[i] > 0) {
                answer = Math.min(answer, freqInText[i] / freqInPattern[i]);
            }
        }
        
        return answer;
    }
    
    public int maxNumberOfBalloons(String text) {
        // Any string made up of lowercase English letters.
        String pattern = "balloon";
        return findMaxNumberofPattern(text, pattern);
    }
}
