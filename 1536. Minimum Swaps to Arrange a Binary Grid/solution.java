/**
 * Other's solution of Array
 */
class Solution {
    public int minSwaps(int[][] grid) {
        int[] a = gridToVec(grid);
        return minSwaps(a);
    }
    
    private int minSwaps(int[] a) {
        int n = a.length;
        int ans = 0;
        for (int i=0; i<n; i++) {
            if (a[i] < (n-i-1)) {
                int j=i;
                while (j < n && a[j] < (n-i-1)) {
                    j++;
                }
				
                if (j == n) {   // Did not find any number greater than or equal to n-i-1.
                    return -1;  // so its impossible to get the answer.
                }
                while (j>i) {
                    //swap(a[j], a[j-1]);
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                    ans++;
                    j--;
                }
            }
        }
        return ans;
    }
    
    private int zerosAtEnd(int[] a) {
        int ans = 0;
        int n = a.length;
        int i=n-1;
        while (i >= 0 && a[i] == 0) {
            ans++;
            i--;
        } 
        return ans;
    }
    
     private int[] gridToVec(int[][] grid) {
        int[] ans = new int[grid.length];
         int i =0;
        for (int[] x: grid) {
            ans[i++] = zerosAtEnd(x);
        }
        return ans;
    }
}


/**
 * Other's solution with LinkedList
 */
class Solution {
    public int minSwaps(int[][] grid) {
        int res = 0, n = grid.length;
        List<Integer> zeroList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int trailingZeroCnt = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--)
                trailingZeroCnt++;
            zeroList.add(trailingZeroCnt);
        }
        
        for (int i = 0; i < n; i++) {
            int target = n - 1 - i; // expected zero count in current row
            int curRow = i;
            while (curRow < n && zeroList.get(curRow) < target)
                curRow++;
            if (curRow == n) return -1;
            // Now find a desired row, then remove it from its
            // original index and place it to the index i
            int toRemove = zeroList.remove(curRow);
            zeroList.add(i, toRemove);
            res += curRow - i;
        }
        return res;
    }
}
