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
