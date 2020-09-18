/**
 * Other's solution without Sort
 *
 * Explanation
 *
 * 1. count letter appearance and store in charCnt[i]
 * 2. find the letter with largest occurence.
 * 3. put the letter into even index numbe (0, 2, 4 ...) char array
 * 4. put the rest into the array
 *
 * Time: O(n)
 * Space: O(n) for result. O(1) for charCnt[].
 */
class Solution {
    public String reorganizeString(String S) {
        int[] charCnt = new int[26]; // S will consist of lowercase letters
        for (char c: S.toCharArray())
            charCnt[c - 'a']++;
        
        int maxCnt = 0, maxLetter = 0;
        for (int i = 0; i < 26; i++) {
            if (charCnt[i] > maxCnt) {
                maxCnt = charCnt[i];
                maxLetter = i;
            }
        }

        if (maxCnt > (S.length() + 1) / 2) // if (S.length() - maxCnt < maxCnt - 1)
            return "";
        
        char[] res = new char[S.length()];
        // Firstly place the char which has max count into the res array
        int idx = 0;
        while (charCnt[maxLetter] > 0) {
            res[idx] = (char) ('a' + maxLetter);
            idx += 2;
            charCnt[maxLetter]--;
        }
        // Then place other chars which have less count
        for (int i = 0; i < 26; i++) {
            while (charCnt[i] > 0) {
                if (idx >= res.length) // after placing maxLetter, the idx is maybe at the tail of res
                    idx = 1; // so move it to index 1
                
                res[idx] = (char) ('a' + i);
                idx += 2;
                charCnt[i]--;
            }
        }
        return String.valueOf(res);
    }
}


/**
 * Other's solution with PriorityQueue
 */
class Solution {
    public String reorganizeString(String S) {
        // Create map of each char to its count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) return "";
            map.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : map.keySet()) {
            pq.add(new int[] {c, map.get(c)});
        }
        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }
}


/**
 * Official solution with Sort by Count
 *
 * Algorithm
 * 
 * Find the count of each character, and use it to sort the string by count.
 * 
 * If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task
 * is impossible.
 * 
 * Otherwise, interleave the characters in the order described above.
 *
 * Time Complexity: O(A(N+logA)), where N is the length of S, and A is the size of the alphabet. In Java,
 *                  our implementation is O(N+AlogA). If A is fixed, this complexity is O(N).
 * Space Complexity: O(N). In Java, our implementation is O(N+A).
 */
class Solution {
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }
}


/**
 * Official solution of Greedy with Heap
 * 
 * Intuition
 * 
 * One consequence of the reasoning in Approach #1, is that a greedy approach that tries to write the
 * most common letter (that isn't the same as the previous letter written) will work.
 * 
 * The reason is that the task is only impossible if the frequency of a letter exceeds (N+1) / 2. Writing
 * the most common letter followed by the second most common letter keeps this invariant.
 * 
 * A heap is a natural structure to repeatedly return the current top 2 letters with the largest remaining counts.
 * 
 * 
 * Approach
 * 
 * We store a heap of (count, letter). [In Python, our implementation stores negative counts.]
 * 
 * We pop the top two elements from the heap (representing different letters with positive remaining count),
 * and then write the most frequent one that isn't the same as the most recent one written. After, we push
 * the correct counts back onto the heap.
 * 
 * Actually, we don't even need to keep track of the most recent one written. If it is possible to organize
 * the string, the letter written second can never be written first in the very next writing.
 * 
 * At the end, we might have one element still on the heap, which must have a count of one. If we do, we'll
 * add that to the answer too.
 *
 *
 * Time Complexity: O(NlogA)), where N is the length of S, and A is the size of the alphabet.
 *                  If A is fixed, this complexity is O(N).
 * Space Complexity: O(A). If A is fixed, this complexity is O(1).
 */
class Solution {
    public String reorganizeString(String S) {
        int N = S.length();
        int[] count = new int[26];
        for (char c: S.toCharArray()) count[c-'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
            a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i) if (count[i] > 0) {
            if (count[i] > (N + 1) / 2) return "";
            pq.add(new MultiChar(count[i], (char) ('a' + i)));
        }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) pq.add(mc1);
            if (--mc2.count > 0) pq.add(mc2);
            }
        }

        if (pq.size() > 0) ans.append(pq.poll().letter);
        return ans.toString();
    }
}
class MultiChar {
    int count;
    char letter;
    MultiChar(int ct, char ch) {
        count = ct;
        letter = ch;
    }
}
