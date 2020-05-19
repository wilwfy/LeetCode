/**
 * My solution with Stack
 */
class StockSpanner {
    Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && (stack.peek()[0] <= price)) {
            //System.out.println(stack.peek()[0] + "--" + stack.peek()[1]);
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }
}


/**
 * Official solution with Stack
 *
 * Time Complexity: O(Q), where Q is the number of calls to StockSpanner.next. In total, there are Q pushes to the stack, and at
 *                  most Q pops.
 * Space Complexity: O(Q).
 */
class StockSpanner {
    Stack<Integer> prices, weights;

    public StockSpanner() {
        prices = new Stack();
        weights = new Stack();
    }

    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }

        prices.push(price);
        weights.push(w);
        return w;
    }
}


/**
 * Other's solution with Stack
 *
 *  You can refer to the same problem 739. Daily Temperatures.
 * 
 * Push every pair of <price, result> to a stack.
 * Pop lower price from the stack and accumulate the count.
 * 
 * One price will be pushed once and popped once.
 * So 2 * N times stack operations and N times calls.
 * I'll say time complexity is amortized O(1)
 */
class StockSpanner {
    Stack<int[]> stack;
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price)
            res += stack.pop()[1];
        stack.push(new int[]{price, res});
        return res;
    }
}


/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
