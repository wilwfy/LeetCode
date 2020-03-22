/*
 * Official solution of Sort
 *
 * Time complexity : O(nlogn). Other than the sort invocation, we do a simple linear scan of the list, so the runtime
 *                   is dominated by the O(nlgn) complexity of sorting.
 * Space complexity : O(1) (or O(n)). If we can sort intervals in place, we do not need more than constant additional
 *                    space. Otherwise, we must allocate linear space to store a copy of intervals and sort that.
 */
class Solution {
  private class IntervalComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
      return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
    }
  }

  public int[][] merge(int[][] intervals) {
    Collections.sort(Arrays.asList(intervals), new IntervalComparator());

    LinkedList<int[]> merged = new LinkedList<>();
    for (int[] interval : intervals) {
      // if the list of merged intervals is empty or if the current
      // interval does not overlap with the previous, simply append it.
      if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
        merged.add(interval);
      }
      // otherwise, there is overlap, so we merge the current and previous
      // intervals.
      else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
      }
    }

    return merged.toArray(new int[merged.size()][]);
  }
}
