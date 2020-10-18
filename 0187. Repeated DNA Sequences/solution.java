/**
 * Other's solution of One Pass with HashSet
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String str = s.substring(i, i + 10);
            if (!seen.add(str)) { // add() returns true if this set did not already contain the specified element
                repeated.add(str);
            }
        }
        return new ArrayList(repeated);
    }
}
