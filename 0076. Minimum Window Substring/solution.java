/**
 * Other's solution of Two Pointers with Map based on Array
 *
 * This is a template for problems of Minimum Window in String:
 *     https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
 */
class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128]; // use Array instead of HashMap
        for (char c: t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] > 0) counter--;
            map[c1]--;
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start);
                map[c2]++;
                if (map[c2] > 0) counter++;
                start++;
            }
        }
        return minLen < Integer.MAX_VALUE ? s.substring(minStart, minStart + minLen) : "";
    }
}


/**
 * Official solution of Sliding Window with Two Pointers
 *
 * Intuition
 * 
 * We can use a simple sliding window approach to solve this problem.
 * 
 * In any sliding window based problem we have two pointers. One right pointer whose job is to expand
 * the current window and then we have the left pointer whose job is to contract a given window. At any
 * point in time only one of these pointers move and the other one remains fixed.
 * 
 * We keep expanding the window by moving the right pointer. When the window has all the desired characters,
 * we contract (if possible) and save the smallest window till now.
 * 
 * 
 * Algorithm
 * 
 * 1. We start with two pointers, left and right initially pointing to the first element of the string S.
 * 
 * 2. We use the right pointer to expand the window until we get a desirable window i.e. a window that contains
 *    all of the characters of T.
 * 
 * 3. Once we have a window with all the characters, we can move the left pointer ahead one by one. If the window
 *    is still a desirable one we keep on updating the minimum window size.
 * 
 * 4. If the window is not desirable any more, we repeat step 2 onwards.
 *
 * Time Complexity: O(|S| + |T|) where |S| and |T| represent the lengths of strings S and T. In the worst
 *                  case we might end up visiting every element of string S twice, once by left pointer and
 *                  once by right pointer. |T| represents the length of string T.
 * Space Complexity: O(|S| + |T|). |S| when the window size is equal to the entire string S. |T| when T has
 *                   all unique characters.
 */
class Solution {
    public String minWindow(String s, String t) {
    
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
    
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
    
        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();
    
        // Left and Right pointer
        int l = 0, r = 0;
    
        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;
    
        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
    
        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};
    
        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
    
            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
    
            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
    
                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
    
                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }
    
            // Keep expanding the window once we are done contracting.
            r++;   
        }
    
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}


/**
 * Official solution of Optimized Sliding Window
 *
 * Intuition
 * 
 * A small improvement to the above approach can reduce the time complexity of the algorithm
 * to O(2*|filtered_S| + |S| + |T|), where filtered_S is the string formed from S by removing
 * all the elements not present in T.
 * 
 * This complexity reduction is evident when |filtered_S| <<< |S|.
 * 
 * This kind of scenario might happen when length of string T is way too small than the length
 * of string S and string S consists of numerous characters which are not present in T.
 * 
 * Algorithm
 * 
 * We create a list called filtered\_Sfiltered_S which has all the characters from string S along
 * with their indices in S, but these characters should be present in T.
 *
 * Time Complexity : O(|S| + |T|) where |S| and |T| represent the lengths of strings S and T.
 *                   The complexity is same as the previous approach. But in certain cases
 *                   where |filtered_S| <<< |S|, the complexity would reduce because the number
 *                   of iterations would be 2*|filtered_S| + |S| + |T|.
 * Space Complexity : O(|S| + |T|).
 */
class Solution {
    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();  
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;   
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
