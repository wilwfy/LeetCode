/**
 * Official solution by using HashMap of Stack
 *
 * Time Complexity: O(1) for both push and pop operations.
 * Space Complexity: O(N), where N is the number of elements in the FreqStack.
 */
class FreqStack {

    Map <Integer, Integer> freq; // number -> its frequency
    Map <Integer, Stack<Integer>> numGroup; // freq -> stack of the numbers with same frequency
    int maxFreq;
    
    public FreqStack() {
        freq = new HashMap<>();
        numGroup = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);

        if (f > maxFreq)
            maxFreq = f;
        
        numGroup.computeIfAbsent(f, z -> new Stack()).push(x);
    }
    
    public int pop() {
        int x = numGroup.get(maxFreq).pop();
        freq.put(x, freq.get(x) - 1);
        
        // Decrease maxFreq if no number has the current maximum frequency
        if (numGroup.get(maxFreq).size() == 0)
            maxFreq--;
        
        return x;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
