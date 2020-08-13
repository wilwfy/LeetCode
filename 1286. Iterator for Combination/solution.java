/**
 * Other's solution with preprocessing based on Queue and pruning
 *
 * Algorithm
 * There are amny problem in leetcode on combinations and permutations if you already solved them it is very easy.
 * 
 * Generate all the combinations form left to rigth and add into the queue when length is equals to the combinationLength.
 * next simply poll from the queue and reutrn.
 * hasNext return true until queue is not empty.
 *
 * Time Complexity:
 *                 construction: O(n+r-1Cr) where n is length of characters and r is the combinationLength.
 *                 next(): O(1).
 *                 hasNext(): O(1).
 * Space Complexity: O((n - r)!) where n is length of characters and r is the combinationLength.
 */
class CombinationIterator {
    Queue<String> qu;
    
    public CombinationIterator(String characters, int combinationLength) {
        qu = new LinkedList<>();
        combinations(characters, 0, "", combinationLength);
    }
    
    private void combinations(String characters, int start, String soFar, int k) {
        if (k == 0) {
            qu.add(soFar);
            return;
        }
        for (int i = start; i < characters.length(); i++)
            combinations(characters, i + 1, soFar + characters.charAt(i), k - 1);
    }
    
    public String next() {
        return qu.poll();
    }
    
    public boolean hasNext() {
        return !qu.isEmpty();
    }
}


/**
 * Other's solution without preprocessing
 *
 * Algorithm
 * Process the string (str) lexicographically and only store the next result in a stack of characters.
 * Dynamically generate the next combination every-time next() is called.
 *
 * To find the next combination:
 * 
 * 1. Based on the previous combination result, remove the tail part that shares the same trailing substrings as
 * the characters.
 * 2. In the remaining part, get the last (rightmost) character, memorize its place in characters, and remove it
 * from the remaining part.
 * 3. Now the remaining part is not long enough. To complete the combination, starting from the right side of the
 * removed character in step2, add characters one by one to the remaining part until the length is enough.
 * 4. Now you get a new combination.
 *
 * Time Complexity:
 *                 construction: O(n) where n is length of characters.
 *                 next(): O(r) where r is the combinationLength.
 *                 hasNext(): O(1).
 * Space Complexity: O(n + r) where n is length of characters and r is the combinationLength.
 */
class CombinationIterator {
    
    Stack<Character> st; // stack to store the characters leading to the creation of a single combination
    Map<Character, Integer> ch2Idx; // map to store character to index
    String result, str; // str - same as characters. result -- the result string representing a combination
    int combLength; // same as combinationLength
    
    public CombinationIterator(String characters, int combinationLength) {
        combLength = combinationLength;
        ch2Idx = new HashMap();
        str = characters;
        st = new Stack();
        result = "";
        // create the first combination
        for (Character ch : characters.toCharArray()) {
            st.push(ch);
            result = result + ch;
            if (st.size()==combinationLength) break;
        }
        int idx = 0;
        // set up the mapping between character --> index
        for (Character ch : characters.toCharArray()) {
            ch2Idx.put(ch, idx++);
        }
    }
    
    public String next() {
        String currResult = result;
        // process the next result
      
        int idx = str.length()-1;
        // keep on removing the last character from the stack/result till the position where idx can be moved ahead
        while (!st.isEmpty() && ch2Idx.get(st.peek())==idx) {
            st.pop();
            idx--;
            result = result.substring(0, result.length()-1);  
        }
        if (st.isEmpty()) return currResult; // there is no next result to pre-process
        
        idx = ch2Idx.get(st.pop()); // we need to add elements after this idx
        result = result.substring(0, result.length()-1);
        
        while (st.size()!=combLength) {
            Character temp = str.charAt(++idx);
            result+=temp;
            st.push(temp);
        }
        
        return currResult;
    }
    
    public boolean hasNext() {
        return !st.isEmpty();
    }
}
/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
