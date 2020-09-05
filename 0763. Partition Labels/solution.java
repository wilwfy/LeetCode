/**
 * Official solution of Greedy with Two Pointers
 *
 * Intuition
 * 
 * Let's try to repeatedly choose the smallest left-justified partition. Consider the first label,
 * say it's 'a'. The first partition must include it, and also the last occurrence of 'a'. However,
 * between those two occurrences of 'a', there could be other labels that make the minimum size of
 * this partition bigger. For example, in "abccaddbeffe", the minimum first partition is "abccaddb".
 * This gives us the idea for the algorithm: For each letter encountered, process the last occurrence
 * of that letter, extending the current partition [anchor, j] appropriately.
 * 
 * Algorithm
 * 
 * We need an array last[char] -> index of S where char occurs last. Then, let anchor and j be the start
 * and end of the current partition. If we are at a label that occurs last at some index after j, we'll
 * extend the partition j = last[c]. If we are at the end of the partition (i == j) then we'll append a
 * partition size to our answer, and set the start of our new partition to i+1.
 *
 * Time Complexity: O(N), where N is the length of S.
 * Space Complexity: O(1) to keep data structure last of not more than 26 characters.
 */
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++)
            last[S.charAt(i) - 'a'] = i;
        
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            // Move the right pointer to the farest one of the last character of
            // the character that the left pointer ever visited
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) { // left pointer reach the right pointer, it means all the visited characters locate within the substring whose index range is anchor ~ j.
                ans.add(i - anchor + 1); // get the size of substring
                anchor = i + 1;
            }
        }
        return ans;
    }
}


/**
 * Other's solution of Greedy with Two Pointers
 *
 * Explanation
 * traverse the string record the last index of each char.
 * using pointer to record end of the current sub string.
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }
}
