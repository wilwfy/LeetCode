/**
 * My solution of Three Pass
 *
 * Time: O(n)
 * Space: O(1). The nums array has constant length 10.
 */
class Solution {
    public String getHint(String secret, String guess) {
        int[] nums = new int[10]; // index 0 ~ 9
        for (char c: secret.toCharArray())
            nums[c - '0']++;
        int bull = 0, cow = 0;
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c == secret.charAt(i)) {
                bull++;
                nums[c - '0']--;
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c == secret.charAt(i)) {
                continue;
            } else if (nums[c - '0'] > 0) {
                cow++;
                nums[c - '0']--;
            }
        }
        return String.valueOf(bull) + "A" + String.valueOf(cow) + "B";
    }
}


/**
 * Other's solution of One Pass
 *
 * Time: O(n)
 * Space: O(1). The numbers array has constant length 10.
 */
class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0'] < 0) cows++;
                if (numbers[guess.charAt(i)-'0'] > 0) cows++;
                numbers[secret.charAt(i)-'0']++;
                numbers[guess.charAt(i)-'0']--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
