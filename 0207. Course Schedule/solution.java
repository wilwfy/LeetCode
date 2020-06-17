/**
 * Other's solution of BFS Topological Sort with adjacency matrix
 *
 * Time & Space: O(V+E)
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];
        
        for (int i=0; i<prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }
        
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i=0; i<indegree.length; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }
}


/**
 * Other's solution of BFS Topological Sort with LinkedList
 *
 * According to the Wiki about what Topological sorting is (https://en.wikipedia.org/wiki/Topological_sorting)
 * and the Kahn's algorithm as shown below:
 *   L ← Empty list that will contain the sorted elements
 *   S ← Set of all nodes with no incoming edges
 *
 * while S is non-empty do
 *     remove a node n from S
 *     add n to tail of L
 *     for each node m with an edge e from n to m do
 *         remove edge e from the graph
 *         if m has no other incoming edges then
 *             insert m into S
 * if graph has edges then
 *     return error (graph has at least one cycle)
 * else
 *     return L (a topologically sorted order)
 *
 * Time & Space: O(V+E)
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] goCourses = new List[numCourses];
        for(int i = 0; i < goCourses.length; i++){
            goCourses[i] = new LinkedList<Integer>();
        }
        for(int[] pair: prerequisites){
            incomingEdges[pair[0]]++;
            goCourses[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < incomingEdges.length; i++){
            if(incomingEdges[i] == 0){
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int goCrs: goCourses[cur]){
                 edgeCnt--;
                 if(--incomingEdges[goCrs] == 0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt == 0;
    }
}


/**
 * Other's solution of BFS Topological Sort with LinkedList
 *
 * BFS Solution: (Topological sorting)
 * 
 * The basic idea is to use Topological algorithm: put NODE with 0 indgree into the queue, then make indegree of NODE's successor
 * dereasing 1. Keep the above steps with BFS.
 * 
 * Finally, if each node' indgree equals 0, then it is validated DAG (Directed Acyclic Graph), which means the course schedule can
 * be finished.
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;
    
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;    
    
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) 
            if (indegree[i] == 0)
                queue.add(i);
    
        // How many courses don't need prerequisites.
        int canFinishCount = queue.size();  
        while (!queue.isEmpty()) {
            int prerequisite = queue.remove(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) { 
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        canFinishCount++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
    
        return (canFinishCount == numCourses);    
    }
}


/**
 * Other's solution of DFS
 *
 * DFS Solution:
 * 
 * The basic idea is using DFS to check if the current node was already included in the traveling path. In this case, we need to
 * convert graph presentation from edge list to adjacency list first, and then check if there's cycle existing in any subset. Because
 * tree is a connected graph, we can start from any node.
 * 
 * The graph is possibly not connected, so need to check every node.
 * 
 * To do memorization and pruning, a HashMap is used to save the previous results for the specific node.
 */
class Solution {
    HashMap<Integer, Boolean>memo = new HashMap<Integer, Boolean>();//Memorization HashMap for DFS pruning 
    public boolean canFinish(int n, int[][] edges) {		 
        if (edges.length != 0) { 
            HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<Integer, HashSet<Integer>>(); // Neighbors of each node
            HashSet<Integer>curPath = new HashSet<Integer>(); // Nodes on the current path
            for (int i = 0; i < edges.length; i++) {// Convert graph presentation from edge list to adjacency list 
                if (!neighbors.containsKey(edges[i][1])) 
                    neighbors.put(edges[i][1], new HashSet<Integer>());            
                neighbors.get(edges[i][1]).add(edges[i][0]);
            }
            
            for (int a[] : edges) // The graph is possibly not connected, so need to check every node.
    	        if (hasCycle(neighbors, a[0], -1, curPath))// Use DFS method to check if there's cycle in any curPath
    	            return false;
        } 
        return true;
    }     
    
    boolean hasCycle(HashMap<Integer, HashSet<Integer>> neighbors, int kid, int parent, HashSet<Integer>curPath) {
    	if (memo.containsKey(kid)) return memo.get(kid);
        if (curPath.contains(kid)) return true;// The current node is already in the set of the current path	    
        if (!neighbors.containsKey(kid)) return false;	   
        
        curPath.add(kid);
        for (Integer neighbor : neighbors.get(kid)) {
        	boolean hasCycle = hasCycle(neighbors, neighbor, kid, curPath);// DFS
        	memo.put(kid, hasCycle);	        	
            if (hasCycle) return true;
        }
        curPath.remove(kid);	    
        return false;
    }
}
