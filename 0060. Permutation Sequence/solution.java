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


/**
 * Other's solution of using pattern k = k - (index from pervious)
 * But runtime is O(n^2) because of ArrayList.remove() is O(n)
 *
 * https://leetcode.com/problems/permutation-sequence/discuss/22507/"Explain-like-I'm-five"-Java-Solution-in-O(n)/115644
 */
public class Solution {
    public String getPermutation(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();
        
        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        
        // create a list of numbers to get indices
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}
        
        k--;
        
        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }
        
        return String.valueOf(sb);
    }
}


/**
 * Other's improved solution of using pattern k = k - (index from pervious)
 * The runtime is O(n) because of LinkedList.remove() is O(1)
 */
class Solution {
    public String getPermutation(int n, int k) {

        List<Integer> nums = new LinkedList<>();
        int factorials = 1;
        for (int i = 1; i <= n; i++) {
            factorials *= i;
            
            nums.add(i);
        }
        // Now we get:
        // factorials = n!
        // nums = {1, 2, 3, ..., n}
        
        StringBuilder res = new StringBuilder();
        int idx = k - 1; // index of the Permutation has -1 shift with the count value
        for (int i = 0; i < n; i++) {
            factorials = factorials / (n - i); // factorials = (n-1)! when i = 0
            
            // calculate the index of the number which will be used and removed from the list of numbers
            int index = idx / factorials;
            
            res.append(nums.remove(index));
            
            // calculate the index of the Permutation of rest numbers
            idx = idx - index * factorials;
        }
        
        return res.toString();
    }
}
