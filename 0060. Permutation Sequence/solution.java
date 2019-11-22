/**
 * My solution of Backtracking
 * But runtime and memory usage are not good
 */
class Solution {
    public String getPermutation(int n, int k) {
        List<StringBuilder> resList = new ArrayList<>();
        backtrack(n, k, new boolean[n], resList, new StringBuilder());
        return resList.get(k-1).toString();
    }
    
    public void backtrack(int n, int k, boolean[] used, List<StringBuilder> list, StringBuilder tmpList) {
        if (list.size() < k) {
            if (tmpList.length() == n) {
                list.add(new StringBuilder(tmpList.toString()));
            } else {
                for (int i = 1; i <= n; i++) {
                    if (used[i-1]) continue;
                    
                    used[i-1] = true;
                    tmpList.append(String.valueOf(i));
                    backtrack(n, k, used, list, tmpList);
                    
                    tmpList.deleteCharAt(tmpList.length()-1);
                    used[i-1] =  false;
                }
            }
        }
    }
}
