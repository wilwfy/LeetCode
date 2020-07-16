/**
 * My solution with split() and StringBuilder
 *
 * Time: O(N). N is the number of words.
 * Space: O(n). n is the length of the string s.
 */
class Solution {
    public String reverseWords(String s) {
        // String[] words = s.split("\\s+");
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            //System.out.println("index = " + i + ", " + words[i]);
            if (words[i].equals("")) continue;
            if (res.length() != 0) res.append(" ");
            res.append(words[i]);
        }
        return res.toString();
    }
}


/**
 * Other's solution of Two Pointers without trim(), split( ), StringBuilder
 */
public class Solution {
  
    public String reverseWords(String s) {
        if (s == null) return null;
        
        char[] a = s.toCharArray();
        int n = a.length;
        
        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }
    
    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;
          
        while (i < n) {
          while (i < j || i < n && a[i] == ' ') i++; // skip spaces
          while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
          reverse(a, i, j - 1);                      // reverse the word
        }
    }
    
    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;
          
        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }
	    
        return new String(a).substring(0, i);
    }
    
    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
}
