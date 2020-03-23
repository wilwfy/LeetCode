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


/*
 * Other's solution of Counting Sort
 *
 * Time Complexity: O(1). Because of the fixed data size: 1001
 * Space Complexity: O(1). Because of the fixed data size: 1001
 */
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Counting Sort for limited range of arr1 element values
        // Because: 0 <= arr1[i], arr2[i] <= 1000
        int[] cnt = new int[1000+1];
        for (int n: arr1) cnt[n]++;
        
        int i = 0;
        for (int n: arr2) {
            while (cnt[n]-- > 0)
                arr1[i++] = n;
        }
        
        for (int j = 0; j < cnt.length; j++) {
            while (cnt[j]-- > 0)
                arr1[i++] = j;
        }
        return arr1;
    }
}

/*
 * Other's solution of using TreeMap
 *
 * Time Complexity: O(NlogN)
 * Space Complexity: O(N)
 */
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Use TreeMap for large range of arr1 element values
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int n: arr1)
            map.put(n, map.getOrDefault(n, 0)+1);
        
        int i = 0;
        for (int n: arr2) {
            while (map.get(n) > 0) {
                arr1[i++] = n;
                map.put(n, map.get(n)-1);
            }
        }
        
        for (int n: map.keySet()) {
            while (map.get(n) > 0) {
                arr1[i++] = n;
                map.put(n, map.get(n)-1);
            }
        }
        return arr1;
    }
}
