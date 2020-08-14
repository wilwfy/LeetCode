/**
 * Other's DFS solution based on Tracking Direction
 *
 * Algorithm
 * Based on the problem description, we have a tree, and node zero is the root.
 * 
 * However, the direction can point either from a parent to a child (positive), or from a child to its
 * parent (negative). To solve the problem, we traverse the tree and count edges that are directed from
 * a parent to a child. Direction of those edges need to be changed to arrive at zero node.
 * 
 * In the code below, I am using the adjacency list, and the sign indicates the direction. If the index
 * is positive -- the direction is from a parent to a child and we need to change it (change += (nodeTo > 0)).
 * 
 * Note that we cannot detect the direction for zero (-0 == 0), but it does not matter as we start our traversal from zero.
 */
class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjacencyList.add(new ArrayList<>());
        for (int[] con: connections) {
            adjacencyList.get(con[0]).add(con[1]); // positive value presents direction parent->child
            adjacencyList.get(con[1]).add(-con[0]);
        }
        
        return dfs(adjacencyList, 0, 0);
    }
    
    private int dfs(List<List<Integer>> adjacencyList, int nodeFrom, int nodePrev) {
        int changeCnt = 0;
        for (int nodeTo: adjacencyList.get(nodeFrom)) {
            // Instead of use a array of visited nodes, we can just pass the previous node to prevent going back.
            // This is possible because every node has only one parent in the tree.
            if (Math.abs(nodeTo) == nodePrev) continue;
            changeCnt += (nodeTo > 0) ? 1 : 0; // positive value presents direction parent->child, and we need change it
            changeCnt += dfs(adjacencyList, Math.abs(nodeTo), nodeFrom);
        }
        return changeCnt;
    }
}
