/**
 * Other's DFS solution with building tree graph with edges
 * 
 * Algorithm:
 * 
 * 1. You need to consume 2 seconds to simply collect an apple node (come and go)
 * 2. Consider a node:
 *    If none of descendant (including itself) has an apple, we don't need to waste time on this node
 *    If any of descendant has an apple (no matter if it-self has an apple or not), we need to consume 2 seconds on this node anyway
 * 3. Collect node 0 does not need to consume any time
 * 
 * Then, we can have a helper dfs function meaning: time needs to waste on this node to collect all apples. (0 or > 0).
 *
 * Need bi-direction graph and use visited set. Example test case for that:
 * 4
 * [[0,2],[0,3],[1,2]]
 * [false,true,false,false]
 * Expected Answer: 4
 */
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> graphMap = new HashMap<>(); // to store the graph of the tree
        buildTreeGraph(edges, graphMap); // to stop exploring same nodes again and again.
        
        Set<Integer> visited = new HashSet<>();
        return helper(0, graphMap, hasApple, visited);
    }
    
    private void buildTreeGraph(int[][] edges, Map<Integer, List<Integer>> graphMap) {
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graphMap.putIfAbsent(u, new ArrayList<Integer>());
            graphMap.get(u).add(v);
            graphMap.putIfAbsent(v, new ArrayList<Integer>());
            graphMap.get(v).add(u);
        }
    }
    
    private int helper(int node, Map<Integer, List<Integer>> graphMap, List<Boolean> hasApple, Set<Integer> visited) {
        visited.add(node);
        
        int childCost = 0; // cost of traversing all children.
        for (int child: graphMap.getOrDefault(node, new ArrayList<Integer>())) {
            if (visited.contains(child)) continue;
            childCost += helper(child, graphMap, hasApple, visited); // check recursively for all apples in subtrees.
        }
        // If the child of the node or the node itself has apple, and the node itself is not the root node '0'
        // then add 2 to the path cost
        if ((childCost > 0 || hasApple.get(node)) && node != 0) childCost += 2;
        return childCost;
    }
}
