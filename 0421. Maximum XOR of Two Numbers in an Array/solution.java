/**
 * Other's solution with Trie
 *
 * Time: O(n).  2 * O(32 * n) = O(n)
 * Space: constant 2^32
 */
class Solution {
    // binary trie, where each trie node has only 2 child 
    // one child will represent bit "1" in the binary representation of num
    // one child will represent bit "0" in the binary representation of num
    private static class TrieNode {
        // children[0]: represent bit "1" in the binary representation 
        // children[1]: represent bit "0" in the binary representation 
        private final TrieNode[] children;
        
        private TrieNode() {
            children = new TrieNode[2];
        }
    }
    
    private TrieNode root;
    
    public int findMaximumXOR(int[] nums) {
        // initialize the root node
        root = new TrieNode();
        
        TrieNode curr;
        
        // build the trie 
        for (int num : nums) {
            // starting from root;
            curr = root;
            
            // since every num is positive, their 31th bit is always 0
            // we can ignore that bit and directy build from the 30th bit 
            for (int i = 30; i >= 0; --i) {
                int bit = getIthBit(num, i);
                
                // if current bit is 0, it will go to children[1]
                // if current bit is 1, it will go to children[0]
                if (curr.children[bit ^ 1] == null) {
                    curr.children[bit ^ 1] = new TrieNode();
                }
                
                curr = curr.children[bit ^ 1];
            }
        }
        
        int ans = Integer.MIN_VALUE;
        
        // iterate through each num again
        // starting from those significant bits of num, we try the best 
        // to go to the node that represent the negation of current bit 
        // if such node doesn't exist, we have to go the node that represents
        // the current bit 
        for (int num : nums) {
            // starting from root
            curr = root;
            
            // keep track of the maximum result of XOR current num with 
            // other num in the array
            int rst = 0;
            for (int i = 30; i >= 0; --i) {
                int bit = getIthBit(num, i);
                
                // check to see if node that represents the negation of 
                // current bit exists or not. If exists, go to that way 
                
                // if current bit is 1, then we want to go to children[1] (which represents 0)
                // if current bit is 0, then we want to go to children[0] (which represents 1)
                if (curr.children[bit] != null) {
                    curr = curr.children[bit];
                    
                    // if exists, then we will have a "1" at the current index
                    // in the result of maximum XOR 
                    rst += (1 << i);
                } 
                // if not exists
                else {
                    curr = curr.children[bit ^ 1];
                }
            }
            
            // keep track of global maximum 
            ans = Math.max(ans, rst);
            // there is no need to continue when final result has reached max value 
            if (ans == Integer.MAX_VALUE) break;
        }
        
        return ans;
    }
    
    // get the ith bit (count from LSB, 0-based) of num
    private int getIthBit(int num, int i) {
        return (num & (1 << i)) == 0 ? 0 : 1;
    }
}


/**
 * Other's Greedy solution with Bit Manipulation and HashMap
 *
 */
class Solution {
    public int findMaximumXOR(int[] nums) {
        int maxResult = 0; 
        int mask = 0;
        /*The maxResult is a record of the largest XOR we got so far. if it's 11100 at i = 2, it means 
        before we reach the last two bits, 11100 is the biggest XOR we have, and we're going to explore
        whether we can get another two '1's and put them into maxResult
        
        This is a greedy part, since we're looking for the largest XOR, we start 
        from the very begining, aka, the 31st postition of bits. */
        for (int i = 31; i >= 0; i--) {
            
            // The mask will grow like  100..000 , 110..000, 111..000,  then 1111...111
            // for each iteration, we only care about the left parts
            mask = mask | (1 << i);
            
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // we only care about the left parts, for example, if i = 2, then we have
                // {1100, 1000, 0100, 0000} from {1110, 1011, 0111, 0010}
                int leftPartOfNum = num & mask;
                set.add(leftPartOfNum);
            }
            
            // if i = 1 and before this iteration, the maxResult we have now is 1100, 
            // my wish is the maxResult will grow to 1110, so I will try to find a candidate
            // which can give me the greedyTry;
            int greedyTry = maxResult | (1 << i);
            
            for (int leftPartOfNum : set) {
                // This is the most tricky part, coming from a fact that if a ^ b = c, then a ^ c = b;
                // now we have the 'c', which is greedyTry, and we have the 'a', which is leftPartOfNum
                // If we hope the formula a ^ b = c to be valid, then we need the b, 
                // and to get b, we need a ^ c, if a ^ c exisited in our set, then we're good to go
                int anotherNum = leftPartOfNum ^ greedyTry;
                if (set.contains(anotherNum)) {
                    maxResult= greedyTry;
                    break;
                }
            }
            
            // If unfortunately, we didn't get the greedyTry, we still have our max, 
            // So after this iteration, the max will stay at 1100.
        }
        
        return maxResult;
    }
}
