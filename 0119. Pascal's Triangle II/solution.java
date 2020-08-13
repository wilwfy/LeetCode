/**
 * My solution
 */
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        while (rowIndex-- > 0) {
            int rowSize = row.size();
            int prev = rowSize > 1 ? 1 : 0;
            for (int i = 1; i < rowSize; i++) {
                int curr = row.get(i);
                row.set(i, prev + curr);
                prev = curr;
            }
            row.add(1);
        }
        return row;
    }
}
