/*
 * Brute-force
 */
class Solution {
    public String longestWord(String[] words) {
        if (words.length == 0) return "";
        Set<String> set = new HashSet<>();
        String res = "";
        for (String str: words) {
            set.add(str);
        }
        for (String str: words) {
            boolean find = true;
            //System.out.print("\n");
            if ((str.length() > res.length()) || ((str.length() == res.length()) &&
                                                 (str.compareTo(res) < 0))) {
                for (int i = 1; i < str.length(); i++) {
                    //System.out.print(str.substring(0,i) + " ");
                    if (!set.contains(str.substring(0,i))) {
                        find = false;
                        break;
                    }
                }
                if (find) res = str;
            }
        }
        return res;
    }
}
