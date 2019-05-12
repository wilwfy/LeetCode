Medium

class Solution {
    Map<String, String> keys = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    
    List<String> res = new ArrayList<String>();
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return res;
        helper(digits.length(), digits, "", res);
        return res;
    }
    
    public void helper(int max, String digits, String str, List<String> result) {
        if (max == 0) {
            result.add(str);
            return;
        } else {
            String d = digits.substring(0, 1);
            String key = keys.get(d);
            for (int i = 0; i < key.length(); i++) {
                helper(max-1, digits.substring(1), str+key.substring(i, i+1), result);
            }
        }
    }
}

