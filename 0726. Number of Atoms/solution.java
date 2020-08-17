/**
 * Other's solution with Stack and Map
 *
 * Time Complexity: O(N^2).
 * Space Complexity: O(N).
 */
class Solution {
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        int i = 0, N = formula.length();
        while (i < N) {
            char c = formula.charAt(i);
            i++;
            if (c == '(') {
                stack.push(map);
                map = new HashMap<>();
            } else if (c == ')') {
                int cnt = 0;
                while (i < N && Character.isDigit(formula.charAt(i)))
                    cnt = cnt * 10 + formula.charAt(i++) - '0'; // Notice: the index i is increased
                if (cnt == 0) cnt = 1;
                
                if (!stack.isEmpty()) {
                    Map<String, Integer> tmpMap = map; // get the atoms within current parenthesis
                    map = stack.pop(); // get the atoms outside of current parenthesis
                    for (String key: tmpMap.keySet())
                        map.put(key, map.getOrDefault(key, 0) + tmpMap.get(key) * cnt); // merge the count number of atoms
                }
            } else {
                int start = i - 1;
                while (i < N && Character.isLowerCase(formula.charAt(i)))
                    i++;
                String atom = formula.substring(start, i);
                int cnt = 0;
                while (i < N && Character.isDigit(formula.charAt(i)))
                    cnt = cnt * 10 + formula.charAt(i++) - '0'; // Notice: the index i is increased
                if (cnt == 0) cnt = 1;
                map.put(atom, map.getOrDefault(atom, 0) + cnt);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        List<String> atomList = new ArrayList<>(map.keySet());
        Collections.sort(atomList);
        for (String atom: atomList) {
            sb.append(atom);
            if (map.get(atom) > 1) sb.append(map.get(atom));
        }
        return sb.toString();
    }
}
