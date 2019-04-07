class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban_set = new HashSet<>();
        for (String ban: banned)
            ban_set.add(ban);
        
        String[] words = paragraph.toLowerCase().split("\\W+");
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            if (!ban_set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        
        int max = 0;
        String res = "";
        for (String w: map.keySet()) {
            if (map.get(w) > max) {
                max = map.get(w);
                res = w;
            }
        }
        return res;
    }
}
