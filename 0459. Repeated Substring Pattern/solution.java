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

