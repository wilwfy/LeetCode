/**
 * Official solution of BFS
 *
 * Complexity Analysis
 * 
 * Time complexity: O(N) since we will visit every index at most once.
 * Space complexity : O(N) since it needs q to store next index. In fact, q would keep at most two levels
 *                    of nodes. Since we got two children for each node, the traversal of this solution is
 *                    a binary tree. The maximum number of nodes within a single level for a binary tree
 *                    would be N/2, so the maximum length of q is O(N/2 + N/2)= O(N).
 */
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            // check if reach zero
            if (arr[node] == 0) {
                return true;
            }
            if (arr[node] < 0) {
                continue;
            }

            // check available next steps
            if (node + arr[node] < n) {
                q.offer(node + arr[node]);
            }
            if (node - arr[node] >= 0) {
                q.offer(node - arr[node]);
            }
            // mark as visited
            arr[node] = -arr[node];
        }
        return false;
    }
}


/**
 * Official solution of DFS
 *
 * Time complexity: O(N), since we will visit every index only once.
 * Space complexity: O(N) since it needs at most O(N) stacks for recursions.
 */
class Solution {
    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) return true;
            arr[start] = - arr[start]; // the negative value means having been visited
            return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
        }
        return false;
    }
}
