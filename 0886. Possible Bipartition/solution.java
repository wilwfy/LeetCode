/**
 * Official solution of Graph with DFS
 *
 * Intuition
 * It's natural to try to assign everyone to a group. Let's say people in the first group are red, and people in the second group are
 * blue.
 * If the first person is red, anyone disliked by this person must be blue. Then, anyone disliked by a blue person is red, then anyone
 * disliked by a red person is blue, and so on.
 * If at any point there is a conflict, the task is impossible, as every step logically follows from the first step. If there isn't a
 * conflict, then the coloring was valid, so the answer would be true.
 * 
 * Algorithm
 * Consider the graph on N people formed by the given "dislike" edges. We want to check that each connected component of this graph is
 * bipartite.
 * For each connected component, we can check whether it is bipartite by just trying to coloring it with two colors. How to do this is
 * as follows: color any node red, then all of it's neighbors blue, then all of those neighbors red, and so on. If we ever color a red
 * node blue (or a blue node red), then we've reached a conflict.
 *
 * Time Complexity: O(N + E), where E is the length of dislikes.
 * Space Complexity: O(N + E).
 */
class Solution {
    ArrayList<Integer>[] graph;
    Map<Integer, Integer> color;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; ++i)
            graph[i] = new ArrayList();

        for (int[] edge: dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        color = new HashMap();
        for (int node = 1; node <= N; ++node)
            if (!color.containsKey(node) && !dfs(node, 0))
                return false;
        return true;
    }

    public boolean dfs(int node, int c) {
        if (color.containsKey(node))
            return color.get(node) == c;
        color.put(node, c);

        for (int nei: graph[node])
            if (!dfs(nei, c ^ 1))
                return false;
        return true;
    }
}


/**
 * Other's solution of Graph with BFS
 */
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] color = new int[N + 1];
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for(int i = 0; i <= N; i++) adj.add(new ArrayList<Integer>());
        for(int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }
        
        for(int i = 1; i <= N; i++) {
            if(color[i] == 0) {
                color[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(!q.isEmpty()) {
                    int cur = q.poll();
                    for(int nb : adj.get(cur)) {
                        if(color[nb] == 0) {
                            color[nb] = color[cur] == 1 ? 2 : 1;
                            q.add(nb);
                        } else {
                            if(color[nb] == color[cur]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
