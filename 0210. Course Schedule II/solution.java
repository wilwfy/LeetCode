/**
 * Official solution of DFS
 *
 * Algorithm
 * 1. Initialize a stack S that will contain the topologically sorted order of the courses in our graph.
 * 2. Construct the adjacency list using the edge pairs given in the input. An important thing to note about the input for the problem is that a pair such as [a, b] represents
 *    that the course b needs to be taken in order to do the course a. This implies an edge of the form b ➔ a. Please take note of this when implementing the algorithm.
 * 3. For each of the nodes in our graph, we will run a depth first search in case that node was not already visited in some other node's DFS traversal.
 * 4. Suppose we are executing the depth first search for a node N. We will recursively traverse all of the neighbors of node N which have not been processed before.
 * 5. Once the processing of all the neighbors is done, we will add the node N to the stack. We are making use of a stack to simulate the ordering we need. When we add the
 *    node N to the stack, all the nodes that require the node N as a prerequisites (among others) will already be in the stack.
 * 6. Once all the nodes have been processed, we will simply return the nodes as they are present in the stack from top to bottom.
 * 
 * Complexity Analysis
 * Time Complexity: O(N) considering there are N courses in all. We essentially perform a complete depth first search covering all the nodes in the forest. It's a forest and
 *                  not a graph because not all nodes will be connected together. There can be disjoint components as well.
 * Space Complexity: O(N), the space utilized by the recursion stack (not the stack we used to maintain the topologically sorted order)
 */
class Solution {
    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;
    
    boolean isPossible;
    Map<Integer, Integer> colorMap;
    Map<Integer, List<Integer>> adjListMap;
    List<Integer> topologicalOrder;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        init(numCourses);
        
        // Create the adjecency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjListMap.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjListMap.put(src, lst);
        }
        
        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.colorMap.get(i) == WHITE)
                dfs(i);
        }
        
        int[] order;
        if (this.isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++)
                order[i] = topologicalOrder.get(numCourses - i - 1);
        } else
            order = new int[0];
        
        return order;
    }
    
    private void init(int numCourses) {
        this.isPossible = true;
        this.colorMap = new HashMap<>();
        this.adjListMap = new HashMap<>();
        this.topologicalOrder = new ArrayList<Integer>();
        
        // By default all vertices are WHITE
        for (int i = 0; i < numCourses; i++)
            this.colorMap.put(i, WHITE);
    }
    
    private void dfs(int node) {
        // Don't recurse further if we found a cycle already
        if (!this.isPossible) return;
        
        // Start the recursion
        this.colorMap.put(node, GRAY); // change its color WHITE -> GRAY
        
        // Traverse on neighbouring vertices
        for (Integer neighbor: adjListMap.getOrDefault(node, new ArrayList<Integer>())) {
            if (this.colorMap.get(neighbor) == WHITE) {
                dfs(neighbor);
            } else if (this.colorMap.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
            }
        }
        
        // Recursion ends. We mark it as black
        this.colorMap.put(node, BLACK);
        this.topologicalOrder.add(node);
    }
}


/**
 * Official solution of BFS with using Node Indegree
 *
 * Intuition
 * We first process all the nodes/course with 0 in-degree implying no prerequisite courses required. If we remove all these courses from the graph, along with their outgoing
 * edges, we can find out the courses/nodes that should be processed next. These would again be the nodes with 0 in-degree. We can continuously do this until all the courses
 * have been accounted for.
 *
 * Algorithm
 * 1. Initialize a queue, Q to keep a track of all the nodes in the graph with 0 in-degree.
 * 2. Iterate over all the edges in the input and create an adjacency list and also a map of node v/s in-degree.
 * 3. Add all the nodes with 0 in-degree to Q.
 * 4. The following steps are to be done until the Q becomes empty.
 *    1. Pop a node from the Q. Let's call this node, N.
 *    2. For all the neighbors of this node, N, reduce their in-degree by 1. If any of the nodes' in-degree reaches 0, add it to the Q.
 *    3. Add the node N to the list maintaining topologically sorted order.
 *    4. Continue from step 4.1.
 *
 * Complexity Analysis
 * Time Complexity: O(V + E) where V represents the number of vertices and E represents the number of edges. We pop each node exactly once from the zero in-degree queue and
 *                  that gives us V. Also, for each vertex, we iterate over its adjacency list and in totality, we iterate over all the edges in the graph which gives us E.
 *                  Hence, O(V + E)
 * Space Complexity: O(V + E). We use an intermediate queue data structure to keep all the nodes with 0 in-degree. In the worst case, there won't be any prerequisite
 *                   relationship and the queue will contain all the vertices initially since all of them will have 0 in-degree. That gives us O(V). Additionally, we
 *                   also use the adjacency list to represent our graph initially. The space occupied is defined by the number of edges because for each node as the key,
 *                   we have all its adjacent nodes in the form of a list as the value. Hence, O(E). So, the overall space complexity is O(V + E).
 */
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjListMap = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];
        
        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjListMap.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjListMap.put(src, lst);
            // Record in-degree of each vertex
            indegree[dest]++;
        }
        
        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        
        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.poll();
            topologicalOrder[i++] = node;
            
            // Reduce the in-degree of each neighbor by 1
            if (adjListMap.containsKey(node)) {
                for (Integer neighbor: adjListMap.get(node)) {
                    indegree[neighbor]--;
                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0)
                        q.offer(neighbor);
                }
            }
        }
        
        // Check to see if topological sort is possible or not
        if (i == numCourses)
            return topologicalOrder;
        else
            return new int[0];
    }
}
