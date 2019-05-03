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


/*
 * Official solution
 *
 * Time complexity : O(n)O(n) because we simply traverse the given string one character at a time and
 * push and pop operations on a stack take O(1) time.
 * Space complexity : O(n) as we push all opening brackets onto the stack and in the worst case, we will
 * end up pushing all the brackets onto the stack. e.g. ((((((((((.
 */
class Solution {

  // Hash table that takes care of the mappings.
  private HashMap<Character, Character> mappings;

  // Initialize hash map with mappings. This simply makes the code easier to read.
  public Solution() {
    this.mappings = new HashMap<Character, Character>();
    this.mappings.put(')', '(');
    this.mappings.put('}', '{');
    this.mappings.put(']', '[');
  }

  public boolean isValid(String s) {

    // Initialize a stack to be used in the algorithm.
    Stack<Character> stack = new Stack<Character>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // If the current character is a closing bracket.
      if (this.mappings.containsKey(c)) {

        // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
        char topElement = stack.empty() ? '#' : stack.pop();

        // If the mapping for this bracket doesn't match the stack's top element, return false.
        if (topElement != this.mappings.get(c)) {
          return false;
        }
      } else {
        // If it was an opening bracket, push to the stack.
        stack.push(c);
      }
    }

    // If the stack still contains elements, then it is an invalid expression.
    return stack.isEmpty();
  }
}
