/**
 * My solution of Two HashMaps
 *
 * Time complexity: O(N). N is the length of pattern.
 * Space complexity : O(M) where M represents the number of unique words in str. Even though we have two hash maps,
 *                    the character to word hash map has space complexity of O(1) since there can at most be 26 keys.
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split("\\s");
        if (pattern.length() != words.length) return false;
        
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map1.putIfAbsent(c, words[i]) != null) {
                if (!map1.get(c).equals(words[i])) return false;
            }
            if (map2.putIfAbsent(words[i], c) != null) {
                if (map2.get(words[i]) != c) return false;
            }
        }
        return true;
    }
}


/**
 * Official solution of Single Index Hash Map
 *
 * Intuition
 * 
 * Rather than having two hash maps, we can have a single index hash map which keeps track of the
 * first occurrences of each character in pattern and each word in str. As we go through each
 * character-word pair, we insert unseen characters from pattern and unseen words from str.
 * 
 * The goal is to make sure that the indices of each character and word match up. As soon as we find
 * a mismatch, we can return False.
 * 
 * Let's go through some examples.
 * 
 * pattern: 'abba'
 * 
 * str: 'dog cat cat dog'
 * 
 *   1.  'a' and 'dog' -> map_index = {'a': 0, 'dog': 0}
 *         - Index of 'a' and index of 'dog' are the same.
 *   2.  'b' and 'cat' -> map_index = {'a': 0, 'dog': 0, 'b': 1, 'cat': 1}
 *         - Index of 'b' and index of 'cat' are the same.
 *   3.  'b' and 'cat' -> map_index = {'a': 0, 'dog': 0, 'b': 1, 'cat': 1}
 *         - 'b' is already in the mapping, no need to update.
 *         - 'cat' is already in the mapping, no need to update.
 *         - Index of 'b' and index of 'cat' are the same.
 *   4.  'a' and 'dog' -> map_index = {'a': 0, 'dog': 0, 'b': 1, 'cat': 1}
 *         - 'a' is already in the mapping, no need to update.
 *         - 'dog' is already in the mapping, no need to update.
 *         - Index of 'a' and index of 'dog' are the same.
 * 
 * pattern: 'abba'
 * 
 * str: 'dog cat fish dog'
 * 
 *   1.  'a' and 'dog' -> map_index = {'a': 0, 'dog': 0}
 *         - Index of 'a' and index of 'dog' are the same.
 *   2.  'b' and 'cat' -> map_index = {'a': 0, 'dog': 0, 'b': 1, 'cat': 1}
 *         - Index of 'b' and index of 'cat' are the same.
 *   3.  'b' and 'fish' -> map_index = {'a': 0, 'dog': 0, 'b': 1, 'cat': 1, 'fish': 2}
 *         - 'b' is already in the mapping, no need to update.
 *         - Index of 'b' and index of 'fish' are NOT the same. Returns False.
 *
 * Time complexity : O(N) where N represents the number of words in the str or the number of characters
 *                   in the pattern.
 * Space complexity : O(M) where M is the number of unique characters in pattern and words in str.
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        HashMap map_index = new HashMap();
        String[] words = str.split(" ");

        if (words.length != pattern.length())
            return false;

        for (Integer i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (!map_index.containsKey(c))
                map_index.put(c, i);

            if (!map_index.containsKey(w))
                map_index.put(w, i);

            if (map_index.get(c) != map_index.get(w))
                return false;
        }

        return true;
    }
}


/**
 * Other's solution of Single Index Hash Map
 *
 * Time complexity : O(N) where N represents the number of words in the str or the number of characters
 *                   in the pattern.
 * Space complexity : O(M) where M is the number of unique characters in pattern and words in str.
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
		    // put() will return the previous value associated with key, or null if there was no mapping for key
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
}
