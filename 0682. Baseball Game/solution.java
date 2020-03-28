/*
 * My solution of Stack
 */
class Solution {
    public int calPoints(String[] ops) {
        if (ops.length == 0) return 0;
        
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ops.length; i++) {
            int score = 0;
            switch (ops[i]) {
                case "+":
                    int tmp = stack.pop();
                    score = tmp + stack.peek();
                    stack.push(tmp);
                    break;
                case "D":
                    score = stack.peek() * 2;
                    break;
                case "C":
                    score = -1 * stack.pop();
                    break;
                default:
                    score = Integer.valueOf(ops[i]);
            }
            sum += score;
            if (!ops[i].equals("C"))
                stack.push(score);
        }
        return sum;
    }
}


/*
 * Official solution of Stack
 */
class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack();

        for(String op : ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newtop = top + stack.peek();
                stack.push(top);
                stack.push(newtop);
            } else if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else {
                stack.push(Integer.valueOf(op));
            }
        }

        int ans = 0;
        for(int score : stack) ans += score;
        return ans;
    }
}
