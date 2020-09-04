/**
 * Other's solution of Regex
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // [a-z] => a-z characters
        // [a-z]+ => [a-z] characters more than once
        // \1 => The backreference \1 (backslash one) references the first capturing group. \1 matches the exact same text that was matched by the first capturing group.
        // \1+ => more such groups
        return s.matches("^([a-z]+)\\1+$");
    }
}


/**
 * Other's solution of String contains()
 *
 * Explanation
 * If we can form the string s with repeated pattern m =>
 * s = m + m + ... + m + m(n times)
 * s + s = m + m + ... + m + m(2n times)
 * Now if we remove first and last character from s + s which even after that we should
 * have 2n - 2 consecutive m so which measn we can check for s in 2s.
 * 
 * s + s substirng(1, 2n-2) ==> m1 + m + m + ..... + m + m + m2
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // Suppose that s = "abab"
        // Let m = ab, so we have:
        // s = m + m
        // s + s = ab + ab + ab + ab, then we can get:
        // s + s substirng(1, 2n-2) ==> b + ab + ab + a => b + s + a => contains s
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }
}


/**
 * Other's solution of Two Pointers with HashSet
 *
 * Algorithm
 * 1. Start from l = length / 2;
 * 2. check if length % l != 0 continue b/c not possiblem to divide stirng in length / l strings.
 * 3. Cut length / l substrings and add into set.
 * 4. Check if set size is once which means we can form string by repeasting that substring return true.
 * 5. try for all possible l until 1.
 * 6. return false if not already returned.
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for(int l = s.length() / 2; l > 0; l--) {
            if(s.length() % l != 0) 
                continue;
            Set<String> set = new HashSet();
            for(int i = 0; i < s.length(); i += l)
                set.add(s.substring(i, i + l));
            if(set.size() == 1)
                return true;
        }
        return false;
    }
}


/**
 * Other's solution of Two Pointers
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int d = n / 2; d > 0; d--) {
            // search the distance between two pointers d where n % d == 0 which means
            // the left pointer index + d is the position of the right pointer index
            // which is also the start position of one possible duplicated substring pattern.
            // And the distance should be 1 at least, so d > 0
            if (n % d == 0) {
                int l = 0; // set the left pointer l to be 0, then the right pointer is l + d
                while (l + d < n && s.charAt(l) == s.charAt(l + d))
                    l++; // increase the left pointer
                if (l + d == n) return true;
            }
        }
        return false;
    }
}


/**
 * Other's solution of Two Pointers with clear explanation
 */
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // corner case
        if(s == null || s.length() == 0) return true;
        
        int n = s.length();
        for(int len = 1; len <= n/2; len++){ // pattern must repeat at least twice, i.e. pattern length is at most n/2
            if(n % len != 0) continue; // s length must can be divided by the pattern length
            
            String pattern = s.substring(0, len); // pattern string
            int i = len; // start index of 2nd pattern
            int j = i + len - 1; // end index of 2nd pattern
            while(j < n){
                String substr = s.substring(i, j + 1);
                if(!pattern.equals(substr)) break; // failed for this pattern, try next pattern		
                i += len;
                j += len;
            }
			
			// if it past the last substring check, i will be n
            if(i == n) return true;
        }
        return false;
    }
}
