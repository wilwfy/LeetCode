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
