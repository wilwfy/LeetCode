/*
 * My solution with Stack and Priority Queue
 */
class MinStack {
    Stack<Integer> stack;
    PriorityQueue<Integer> pq;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        pq = new PriorityQueue<>();
    }
    
    public void push(int x) {
        stack.push(x);
        pq.offer(x);
    }
    
    public void pop() {
        pq.remove(stack.pop());
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return pq.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
