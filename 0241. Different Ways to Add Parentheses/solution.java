/**
 * Other's solution of Divide and Conquer
 */
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Res = diffWaysToCompute(part1);
                List<Integer> part2Res = diffWaysToCompute(part2);
                for (Integer p1: part1Res) {
                    for (Integer p2: part2Res) {
                        int r = 0;
                        switch (c) {
                            case '+': r = p1 + p2;
                                break;
                            case '-': r = p1 - p2;
                                break;
                            case '*': r = p1 * p2;
                                break;
                        }
                        res.add(r);
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        return res;
    }
}
