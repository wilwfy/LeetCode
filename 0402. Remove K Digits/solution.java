/**
 * Other's solution of Stack and Greedy
 *
 * Time & Space: O(n)
 */
class Solution {
    public String removeKdigits(String num, int k) {
        if ((num == null) || (num.length() <= k)) return "0";
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            //whenever meet a digit which is less than the previous digits, discard the previous ones
            while ((k > 0) && !stack.isEmpty() && (stack.peek() > num.charAt(i))) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        
        // in case of "1111" or "1234" (string is already in an ascending order)
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        
        // Remove the leading zeros before reversing the string of result,
        // because deleting element at tail has better performance than deleting them at head.
        // Here using sb.length() > 1 not sb.length() > 0 to make sure that there is at least
        // one "0" left to be returned for a string of all zeros like "0000000", otherwise a 
        // string of "" will be returned to result in wrong answer.
        while ( (sb.length() > 1) && (sb.charAt(sb.length()-1) == '0') )
            sb.deleteCharAt(sb.length()-1);
        
        return sb.reverse().toString();
    }
}


/**
 * Other's solution of Stack and Greedy
 *
 * Time & Space: O(n)
 */
public class Solution {
    public String removeKdigits(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }
}
