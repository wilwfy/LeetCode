/**
 * Other's solution of Recursion with Greedy
 *
 * Given the string s, the greedy choice (i.e., the leftmost letter in the answer) is the smallest s[i], s.t.
 * the suffix s[i .. ] contains all the unique letters. (Note that, when there are more than one smallest s[i]'s,
 * we choose the leftmost one. Why? Simply consider the example: "abcacb".)
 * 
 * After determining the greedy choice s[i], we get a new string s' from s by
 * 
 * removing all letters to the left of s[i],
 * removing all s[i]'s from s.
 * We then recursively solve the problem w.r.t. s'.
 * 
 * The runtime is O(26 * n) = O(n).
 * Note each time the recursive function builds one leftmost char of the smallest unique chars in s. Since there is
 * at most 26 unique chars in any string, the function is called at most 26 times.
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}


/**
 * Other's solution of Iteration with Stack
 */
class Solution {
    public String removeDuplicateLetters(String sr) {
        int[] cnt = new int[26]; //will contain number of occurences of character (i+'a')
        boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
        char[] ch = sr.toCharArray();
        for(char c: ch){  //count number of occurences of character 
            cnt[c-'a']++;
        }
        
        Stack<Character> st = new Stack<>(); // answer stack
        int index;
        for(char s:ch){ 
            index= s-'a';
            cnt[index]--;   //decrement number of characters remaining in the string to be analysed
            if(visited[index]) //if character is already present in stack, dont bother
                continue;
            //if current character is smaller than last character in stack which occurs later in the string again
            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            while(!st.isEmpty() && s<st.peek() && cnt[st.peek()-'a']!=0){ 
                visited[st.pop()-'a']=false;
            }
            st.push(s); //add current character and mark it as visited
            visited[index]=true;
        }
    
        StringBuilder sb = new StringBuilder();
        //pop character from stack and build answer string from back
        while(!st.isEmpty()){
            sb.insert(0,st.pop());
        }
        return sb.toString();
    }
}


/**
 * Another solution of Iteration with Stack based on StringBuilder
 */
public class Solution {
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26]; // will contain number of occurences of character (i+'a')
        boolean[] visited = new boolean[26]; // will contain if character ('a' + i) is present in current result Stack
        char[] ch = s.toCharArray();
        for(char c : ch){  // count number of occurences of character 
            cnt[c-'a']++;
        }
        
        StringBuilder sb = new StringBuilder();; // answer stack
        int index;
        for(char c : ch){ 
            index = c - 'a';
            cnt[index]--;   // decrement number of characters remaining in the string to be analysed
            if(visited[index]) // if character is already present in stack, dont bother
                continue;
            // if current character is smaller than last character in stack which occurs later in the string again
            // it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            while( (sb.length() > 0) && c < sb.charAt(sb.length()-1) && cnt[sb.charAt(sb.length()-1)-'a']!=0){ 
                visited[sb.charAt(sb.length()-1) - 'a'] = false;
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(c); // add current character and mark it as visited
            visited[index] = true;
        }
        
        return sb.toString();
    
    }
}
