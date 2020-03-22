/*
 * My solution
 */
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr2.length == 0) return arr1;
        int[] res = new int[arr1.length];
        Arrays.sort(arr1);
        
        Set<Integer> set = new HashSet<>();
        for (int a2: arr2) {
            set.add(a2);
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int a1: arr1) {
            if (set.contains(a1)) {
                map.putIfAbsent(a1, 0);
                map.put(a1, map.get(a1) + 1);
            }           
            else
                queue.offer(a1);
        }
        
        int i = 0;
        for (int a2: arr2) {
            int cnt = map.get(a2);
            while (cnt > 0) {
                res[i] = a2;
                i++;
                cnt--;
            }
        }
        while (!queue.isEmpty()) {
            res[i++] = queue.poll();
        }
        return res;
    }
}
