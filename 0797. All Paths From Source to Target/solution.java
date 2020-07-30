/**
 * Other's solution with DFS
 */
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
					
        path.add(0);
        dfs(graph, 0, res, path);
					
        return res;
    }
    
    private void dfs(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfs(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }
}


/**
 * Other's solution with BFS
 *
 * https://youtu.be/5neu2boa3TM
 */
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList();
        Queue<List<Integer>> queue = new LinkedList();
        queue.add(Arrays.asList(0));
        
        int destinationVertex = graph.length - 1;
        
        while(!queue.isEmpty()) {
            List<Integer> pathSoFar = queue.poll();
            int currentVertex = pathSoFar.get(pathSoFar.size() - 1);
            // check if currentVertex is destinationVertex add pathSoFar in result
            if(currentVertex == destinationVertex) result.add(new ArrayList(pathSoFar));
            for(int v : graph[currentVertex]) {
                List<Integer> newPath = new ArrayList(pathSoFar);
                newPath.add(v);
                queue.add(newPath);
            }
        }
        
        return result;
    }
}
