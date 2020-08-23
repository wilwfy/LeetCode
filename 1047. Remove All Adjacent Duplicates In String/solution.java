/**
 * My solution of Stack
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char ch: S.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch)
                stack.pop();
            else
                stack.push(ch);
        }
        String res = "";
        while (!stack.isEmpty())
            res = stack.pop() + res;
        return res;
    }
}


/**
 * Other's solution of Stack without reverse
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for(char s : S.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == s)
                stack.pop();
            else
                stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        for(char s : stack) sb.append(s);
        return sb.toString();
    }
}


/**
 * Another's solution of Stack without reverse
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            int size = sb.length();
            if (size > 0 && sb.charAt(size - 1) == c) {
                sb.deleteCharAt(size - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}


/**
 * Other's solution of Two Pointers without reverse
 *
 * Algorithm
 *
 * i refers to the index to set next character in the output string.
 * j refers to the index of current iteration in the input string.
 * 
 * Iterate characters of S one by one by increasing j.
 * 
 * If S[j] is same as the current last character S[i - 1],
 * we remove duplicates by doing i -= 2.
 * 
 * If S[j] is different as the current last character S[i - 1],
 * we set S[i] = S[j] and increment i++.
 *
 * Time: O(n)
 * Space: O(n)
 */
 class Solution {
    public String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }
}
