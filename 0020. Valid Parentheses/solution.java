class Solution {
    public boolean isValid(String s) {
        if ((s == null) || (s.length() == 0)) return true;
        if (s.length() % 2 != 0) return false;
        Stack<Character> sta = new Stack<>();
        for (char c: s.toCharArray()) {
            if ((c == '(') || (c == '[') || (c == '{'))
                sta.push(c);
            if ((c == ')') || (c == ']') || (c == '}')) {
                if (sta.isEmpty()) return false;
                char p = sta.pop();
                if ( ((p == '(') && (c == ')')) ||
                     ((p == '[') && (c == ']')) ||
                     ((p == '{') && (c == '}')) )
                    continue;
                else
                    return false;
            }
        }
        return sta.isEmpty();
    }
}
