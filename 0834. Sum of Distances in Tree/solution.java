/**
 * Other's solution with Pre-order and Post-order DFS
 *
 * Intuition
 * What if given a tree, with a certain root 0?
 * In O(N) we can find sum of distances in tree from root and all other nodes.
 * Now for all N nodes?
 * Of course, we can do it N times and solve it in O(N^2).
 * C++ and Java may get accepted luckily, but it's not what we want.
 * 
 * When we move our root from one node to its connected node,
 * one part of nodes get closer, one the other part get further.
 * 
 * If we know exactly how many nodes in both parts, we can solve this problem.
 * 
 * With one single traversal in tree, we should get enough information for it and
 * don't need to do it again and again.
 * 
 * 
 * Explanation
 * Let's solve it with node 0 as root.
 * 
 * Initial an array of hashset tree, tree[i] contains all connected nodes to i.
 * Initial an array count, count[i] counts all nodes in the subtree i.
 * Initial an array of res, res[i] counts sum of distance in subtree i.
 * 
 * Post order dfs traversal, update count and res:
 * count[root] = sum(count[i]) + 1
 * res[root] = sum(res[i]) + sum(count[i])
 * 
 * Pre order dfs traversal, update res:
 * When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i] nodes
 * get 1 futhur to root.
 * res[i] = res[root] - count[i] + N - count[i]
 * 
 * return res, done.
 * 
 * 
 * Time Complexity:
 * dfs: O(N) time
 * dfs2: O(N) time
 */
class Solution {
    int[] res; // res is ans
    int[] count;
    List<Set<Integer>> treeGraph;
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        treeGraph = new ArrayList<>();
        res = new int[N];
        count = new int[N];
        for (int i = 0; i < N; i++)
            treeGraph.add(new HashSet<Integer>());
        for (int[] edge: edges) {
            treeGraph.get(edge[0]).add(edge[1]);
            treeGraph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return res;
    }
    
    // Post-order DFS
    private void dfs(int node, int parent) {
        for (int child: treeGraph.get(node)) {
            if (child == parent) continue;
            dfs(child, node);
            count[node] += count[child];
            res[node] += res[child] + count[child];
        }
        count[node]++; // add number 1 for current node itself
    }
    
    // Pre-order DFS
    private void dfs2(int node, int parent) {
        for (int child: treeGraph.get(node)) {
            if (child == parent) continue;
            res[child] = res[node] - count[child] + count.length - count[child]; // count.length = N
            dfs2(child, node);
        }
    }
}
