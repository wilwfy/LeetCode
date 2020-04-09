/*
 * My solution with Stack
 *
 * Time: O(n + m).  n is the length of string S, m is the length of string T
 * Space: O(n + m)
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        for (char s: S.toCharArray()) {
            if (s == '#') {
                if (!st1.isEmpty()) st1.pop();
            } else
                st1.push(s);
        }
        for (char t: T.toCharArray()) {
            if (t == '#') {
                if (!st2.isEmpty()) st2.pop();
            } else
                st2.push(t);
        }
        while (!st1.isEmpty() && !st2.isEmpty()) {
            //System.out.println(st1.size() + ", " + st2.size());
            if (st1.pop() != st2.pop()) return false;
        }
        if (!st1.isEmpty() || !st2.isEmpty())
            return false;
        else
            return true;
    }
}


/*
 * Official solution with Stack
 *
 * Time Complexity: O(M+N), where M, N are the lengths of S and T respectively.
 * Space Complexity: O(M+N).
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }
}


/*
 * Official solution with Two Pointer
 *
 * Intuition:
 * When writing a character, it may or may not be part of the final string depending on how many backspace keystrokes occur in
 * the future.
 * If instead we iterate through the string in reverse, then we will know how many backspace characters we have seen, and therefore
 * whether the result includes our character.
 *
 * Algorithm:
 * Iterate through the string in reverse. If we see a backspace character, the next non-backspace character is skipped. If a character
 * isn't skipped, it is part of the final answer.
 *
 * Time Complexity: O(M+N), where M, N are the lengths of S and T respectively.
 * Space Complexity: O(1).
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }
}


/*
 * Other's solution with Two Pointer
 *
 * Time Complexity: O(M+N), where M, N are the lengths of S and T respectively.
 * Space Complexity: O(1).
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--; j--;
            } else
                return i == -1 && j == -1;
        }
    }
}
