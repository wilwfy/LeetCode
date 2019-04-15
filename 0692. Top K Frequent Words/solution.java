class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word: words)
            map.put(word, map.getOrDefault(word, 0)+1);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (ent1, ent2) -> {
            if (ent1.getValue() == ent2.getValue()) {
                return ent1.getKey().compareTo(ent2.getKey());
            } else {
                return (ent2.getValue() > ent1.getValue()) ? 1 : -1;
            }
        });
        for (int i = 0; i < k; i++)
            res.add(list.get(i).getKey());
        return res;
    }
}


/*
 * Official's solution based on Sorting
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}
