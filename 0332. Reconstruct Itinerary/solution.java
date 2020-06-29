/**
 * Other's solution of PriorityQueue and DFS
 *
 * All the airports are vertices and tickets are directed edges. Then all these tickets form a directed graph.
 * The graph must be Eulerian since we know that a Eulerian path exists.
 * Thus, start from "JFK", we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is
 * a valid reconstruction.
 * Since the problem asks for lexical order smallest solution, we can put the neighbors in a min-heap. In this way,
 * we always visit the smallest possible neighbor first in our trip.
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path; // need use addFirst()
    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (List<String> ticket: tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }
    
    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
}


/**
 * Other's solution of PriorityQueue and Iteration
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll());
            route.add(0, stack.pop());
        }
        return route;
    }
}
