/*
 * Other's solution with Two Stacks
 *
 * The basic idea is to track the index of the left bracket and star position.
 * Push all the indices of the star and left bracket to their stack respectively.
 * STEP 1
 * Once a right bracket comes, pop left bracket stack first if it is not empty. If the left bracket stack is empty, pop the star stack
 * if it is not empty. A false return can be made provided that both stacks are empty.
 *
 * STEP 2
 * Now attention is paid to the remaining stuff in these two stacks. Note that the left bracket CANNOT appear after the star as there
 * is NO way to balance the bracket. In other words, whenever there is a left bracket index appears after the Last star, a false
 * statement can be made. Otherwise, pop out each from the left bracket and star stack.
 *
 * STEP 3
 * A correct sequence should have an empty left bracket stack. You don't need to take care of the star stack.
 *
 * Final Remarks:
 * Greedy algorithm is used here. We always want to use left brackets to balance the right one first as the * symbol is a wild card.
 * There is probably an O(1) space complexity but I think this is worth mentioning.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> leftChar = new Stack<>();
        Stack<Integer> starChar = new Stack<>();
        for (int i = 0; i <s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                leftChar.push(i);
            else if (c == '*')
                starChar.push(i);
            else {
                if (!leftChar.isEmpty())
                    leftChar.pop();
                else if (!starChar.isEmpty())
                    starChar.pop();
                else
                    return false; // ')' occurs before '(' or '*'
            }
        }
        while (!leftChar.isEmpty() && !starChar.isEmpty()) {
            if (leftChar.pop() > starChar.pop())
                return false; // '(' is on right side of '*' without ')' any more
        }
        return leftChar.isEmpty();
    }
}
