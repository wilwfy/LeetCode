class Solution {
    public String frequencySort(String s) {
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> charSet = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            (e1, e2) -> e2.getValue() - e1.getValue()
        );
        for (int i = 0; i < s.length(); i++)
            charSet.put(s.charAt(i), charSet.getOrDefault(s.charAt(i), 0) + 1);
        for (Map.Entry ent: charSet.entrySet())
            maxHeap.offer(ent);
        while(!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> e = maxHeap.poll();
            for (int j = 0; j < e.getValue(); j++)
                res.append(e.getKey());
        }
        return res.toString();
    }
}


/*
 * Other's solution
 */
public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
						
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
				
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int)e.getValue(); i++) 
                sb.append(e.getKey());
        }
        return sb.toString();
    }
}
