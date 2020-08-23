/**
 * My solution of Stack with Pair
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        for (Character ch: s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().getKey() == ch) {
                int cnt = stack.pop().getValue();
                if (cnt != k - 1)
                    stack.push(new Pair<Character, Integer>(ch, cnt + 1));
            } else {
                stack.push(new Pair<Character, Integer>(ch, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Pair<Character, Integer> p :stack)
            sb.append(Character.toString(p.getKey()).repeat(p.getValue()));
        return sb.toString();
    }
}


/**
 * Other's solution of Stack
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder(); // use StringBuilder as Stack
        for(char c : s.toCharArray()) {
            sb.append(c);
            int last = sb.length()-1;
            count[last] = 1 + (last > 0 && sb.charAt(last) == sb.charAt(last-1) ? count[last-1] : 0);
            if(count[last] >= k) sb.delete(sb.length()-k, sb.length());
        }
        return sb.toString();
    }
}


/**
 * Other's solution of Two Pointers
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public String removeDuplicates(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
    }
}
