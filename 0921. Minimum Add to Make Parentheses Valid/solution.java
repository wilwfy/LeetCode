class Solution {
    public int minAddToMakeValid(String S) {
        if (S.length() <= 1) return S.length();
        List<Character> resList = new ArrayList<>();
        int resSize = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            
            if ((c == ')') && (resList.size() > 0)) {
                int last = resSize - 1;
                if (resList.get(last) == '(') {
                    resList.remove(last);
                    resSize--;
                    continue;
                }
            }
            resList.add(c);
            resSize++;
        }
        return resSize;
    }
}


/*
 * Other's Solution
 *
 * Intuition:
 * To make a string valid,
 * we can add some ( on the left,
 * and add some ) on the right.
 * We need to find the number of each.
 * 
 * Explanation:
 * left records the number of ( we need to add on the left of S.
 * right records the number of ) we need to add on the left of S,
 * which equals to the number of current opened parentheses.
 * 
 * Loop char c in the string S:
 * if (c == '('), we increment right,
 * if (c == ')'), we decrement right.
 * When right is already 0, we increment left
 * Return left + right in the end
 * 
 * Time Complexity:
 * Time O(N)
 * Space O(1)
 */
class Solution {
    public int minAddToMakeValid(String S) {
        int left = 0, right = 0;
        for (int i = 0; i < S.length; ++i) {
            if (S.charAt(i) == '(') {
                right++;
            } else if (right > 0) {
                right--;
            } else {
                left++;
            }
        }
        return left + right;
    }
}
