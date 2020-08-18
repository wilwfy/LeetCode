/**
 * Other's solution with Stack
 */
class Solution {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curSb = new StringBuilder();
        int cnt = 0;
        for (char ch: s.toCharArray()) {
            if (Character.isDigit(ch)) {
                cnt = cnt * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(cnt);
                strStack.push(curSb);
                curSb = new StringBuilder();
                cnt = 0;
            } else if (ch == ']') {
                StringBuilder tmpSb = curSb;
                curSb = strStack.pop();
                for (cnt = intStack.pop(); cnt > 0; cnt--)
                    curSb.append(tmpSb);
            } else
                curSb.append(ch);
        }
        return curSb.toString();
    }
}


/**
 * Other's solution of Recursion with Deque
 */
class Solution {
    public String decodeString(String s) {
        Deque<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        return helper(queue);
    }
    
    public String helper(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = helper(queue);
                for (int i = 0; i < num; i++) {
                    sb.append(sub);    
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
