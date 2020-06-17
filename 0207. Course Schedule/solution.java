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
