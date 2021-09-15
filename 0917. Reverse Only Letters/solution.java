/**
 * Official solution of Stack of Letters
 *
 * Intuition and Algorithm
 * Collect the letters of S separately into a stack, so that popping the stack reverses the letters. (Alternatively,
 * we could have collected the letters into an array and reversed the array.)
 * 
 * Then, when writing the characters of S, any time we need a letter, we use the one we have prepared instead.
 * 
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(N).
 */
class Solution {
    public String reverseOnlyLetters(String s) {
        Stack<Character> letters = new Stack();
        for (char c: s.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: s.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }
}


/**
 * Official solution of Reverse Pointer
 *
 * Intuition
 * 
 * Write the characters of S one by one. When we encounter a letter, we want to write the next letter that
 * occurs if we iterated through the string backwards.
 * 
 * So we do just that: keep track of a pointer j that iterates through the string backwards. When we need to
 * write a letter, we use it.
 * 
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(N).
 */
class Solution {
    public String reverseOnlyLetters(String s) {
        StringBuilder ans = new StringBuilder();
        int j = s.length() - 1;
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isLetter(s.charAt(i))) {
                while (!Character.isLetter(s.charAt(j)))
                    j--;
                ans.append(s.charAt(j--));
            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}
