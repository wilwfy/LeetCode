/**
 * Other's DFS solution with graph
 *
 * Explanation
 *
 * Binary relationship is represented as a graph usually.
 * Does the direction of an edge matters? -- Yes. Take a / b = 2 for example,
 * it indicates a --2--> b as well as b --1/2--> a.
 * Thus, it is a directed weighted graph.
 * In this graph, how do we evaluate division?
 * Take a / b = 2, b / c = 3, a / c = ? for example,
 *     a --2--> b --3--> c
 *
 * We simply find a path using DFS from node a to node c and multiply the weights of
 * edges passed, i.e. 2 * 3 = 6.
 * 
 * Please note that during DFS,
 *   - Rejection case should be checked before accepting case.
 *   - Accepting case is (graph.get(u).containsKey(v)) rather than (u.equals(v)) for it
 *     takes O(1) but (u.equals(v)) takes O(n) for n is the length of the longer one between u and v.
 */
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // build big double direction graph
        Map<String, Map<String, Double>> graphMap = new HashMap<>();       
        for (int i = 0; i < equations.size(); i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            double ratio = values[i];
            graphMap.putIfAbsent(start, new HashMap<>());
            graphMap.get(start).put(end, ratio);
            graphMap.putIfAbsent(end, new HashMap<>());
            graphMap.get(end).put(start, 1.0 / ratio);
        }
        // deal with every query
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            // first check if start or end exist in big map or not
            if (!graphMap.containsKey(start) || !graphMap.containsKey(end)) {
                res[i] = -1.0;
                continue;
            }
            // enter dfs loop, for each query, there is a new visited set
            res[i] = helper(graphMap, start, end, new HashSet<>());
        }
        return res;
    }
    
    public double helper(Map<String, Map<String, Double>> graphMap, String start, String end, HashSet<String> visited) {
        // actually no need to check quitting condition, because if one can enter this dfs, one must iterate all children

        if (graphMap.get(start).containsKey(end)) {
            return graphMap.get(start).get(end);
        }
        // mark visited
        visited.add(start);
        // Iterate all children and do DFS
        for (Map.Entry<String, Double> entry: graphMap.get(start).entrySet()) {
            if (visited.contains(entry.getKey())) continue;
            double res = helper(graphMap, entry.getKey(), end, visited);
            if (res == -1.0) continue;
            return res * entry.getValue();
        }
        
        return -1.0;
    }
}
