/**
 * Other's solution with HashMap and PriorityQueue based on Dijkstra's algorithm
 */
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] flight: flights) {
            if (!prices.containsKey(flight[0])) prices.put(flight[0], new HashMap<>());
            prices.get(flight[0]).put(flight[1], flight[2]);
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[]{0, src, K+1}); // {price, city, stops}
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0], city = top[1], stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a: adj.keySet()) {
                    pq.add(new int[]{price+adj.get(a), a, stops-1});
                }
            }
        }
        return -1;
    }
}


/**
 * Other's solution DP based on Bellman–Ford algorithm
 *
 * Time Complexity: O(|V| * |E|). |V| and |E| are the number of vertices and edges respectively.
 * Space Complexity: O(|V|).
 */
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        for (int i = 0; i <= K; i++) {
            int[] temp = Arrays.copyOf(prices, n);
            for (int[] flight : flights) {
                int cur = flight[0], next = flight[1], price = flight[2];
                if (prices[cur] == Integer.MAX_VALUE) continue;
                temp[next] = Math.min(temp[next], prices[cur] + price);
            }
            prices = temp;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}
