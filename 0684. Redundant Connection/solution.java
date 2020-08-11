/**
 * Official solution of DFS
 *
 * Intuition and Algorithm
 * For each edge (u, v), traverse the graph with a depth-first search to see if we can connect u to v. If we can, then it must be the duplicate edge.
 *
 * Time Complexity: O(N^2) where N is the number of vertices (and also the number of edges) in the graph. In the worst case, for every edge we include,
 *                  we have to search every previously-occurring edge of the graph.
 * Space Complexity: O(N). The current construction of the graph has at most N nodes.
 */
class Solution {
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}


/**
 * Other's solution of Union-Find
 *
 * Ituition
 * An edge will connect two nodes into one connected component.
 * When we count an edge in, if two nodes have already been in the same connected component, the edge will result in
 * a cycle. That is, the edge is redundant.
 * 
 * We can make use of Disjoint Sets (Union Find).
 * If we regard a node as an element, a connected component is actually a disjoint set.
 * 
 * For example,
 * 
 * Given edges [1, 2], [1, 3], [2, 3],
 *   1
 *  / \
 * 2 - 3
 * Initially, there are 3 disjoint sets: 1, 2, 3.
 * Edge [1,2] connects 1 to 2, i.e., 1 and 2 are winthin the same connected component.
 * Edge [1,3] connects 1 to 3, i.e., 1 and 3 are winthin the same connected component.
 * Edge [2,3] connects 2 to 3, but 2 and 3 have been winthin the same connected component already, so [2, 3] is redundant.
 *
 * We use two techniques to improve the run-time complexity: path compression, and union-by-rank.
 * 
 * Path compression involves changing the x = parent[x] in the find function to parent[x] = find(parent[x]).
 * Basically, as we compute the correct leader for x, we should remember our calculation.
 * 
 * Union-by-rank involves distributing the workload of find across leaders evenly. Whenever we ds.union(x, y),
 * we have two leaders rootX, rootY and we choose the leader that has a higher following to pick up a new follower.
 * Specifically, the meaning of rank is that there are less than 2 ^ rank[x] followers of x. This strategy can be
 * shown to give us better bounds for how long the recursive loop in ds.find could run for.
 *
 * Time Complexity: O(Nα(N))≈O(N), where N is the number of vertices (and also the number of edges) in the graph,
 *                  and α is the Inverse-Ackermann function. We make up to N queries of ds.union, which takes
 *                  (amortized) O(α(N)) time. Outside the scope of this article, it can be shown why ds.union
 * 				    has O(α(N)) complexity, what the Inverse-Ackermann function is, and why O(α(N)) is approximately O(1).
 * 
 * Space Complexity: O(N). The current construction of the graph (embedded in our dsu structure) has at most N nodes.
 */
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet ds = new DisjointSet(edges.length);
        for (int[] edge: edges) {
            // shift vertex value by 1, since we need use the index 0 in the arrays parent[] and rank[] 
            if (!ds.union(edge[0] - 1, edge[1] - 1)) return edge;
        }
        throw new IllegalArgumentException();
    }
    
    static class DisjointSet {
        private int[] parent;
        private int[] rank;
        
        public DisjointSet(int size) {
            if (size < 0) throw new IllegalArgumentException();
            //for (int i = 0; i < size; i++) parent[i] = i;
            parent = new int[size];
            rank = new int[size];
        }
        
        public int find(int x) {
            //if (parent[x] != x) parent[x] = find(parent[x]);
            //return parent[x];
            if (parent[x] == 0) return x;
            return parent[x] = find(parent[x]); // Path compression by halving.
        }
        
        // Return false if x, y are connected.
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            
            // Make root of smaller rank point to root of larger rank.
            if (rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else if (rank[rootY] < rank[rootX])
                parent[rootY] = rootX;
            else {
                parent[rootX] = rootY;
                rootY++;
            }
            
            return true;
        }
    }
}


/**
 * Another's solution of Union-Find
 */
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] sets = new int[edges.length + 1];
        
        for (int[] edge: edges) {
            int u = find(sets, edge[0]);
            int v = find(sets, edge[1]);
            if (u == v)
                return edge;
            
            sets[u] = v;
        }
        return new int[]{};
    }
    
    private int find(int[] sets, int v) {
        return sets[v] == 0 ? v : find(sets, sets[v]);
    }
}
