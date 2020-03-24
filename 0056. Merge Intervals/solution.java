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


/*
 * Other solution with different Comparator Implementation
 */
class Solution {
	public int[][] merge(int[][] intervals) {
		if (intervals.length <= 1)
			return intervals;

		// Sort by ascending starting point
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		List<int[]> result = new ArrayList<>();
		int[] newInterval = intervals[0];
		result.add(newInterval);
		for (int[] interval : intervals) {
			if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
				newInterval[1] = Math.max(newInterval[1], interval[1]);
			else {                             // Disjoint intervals, add the new interval to the list
				newInterval = interval;
				result.add(newInterval);
			}
		}

		return result.toArray(new int[result.size()][]);
	}
}

/*
 * Other solution with different Comparator Implementation
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval obj0, Interval obj1) {
                return obj0.start - obj1.start;
            }
        });

        List<Interval> ret = new ArrayList<>();
        Interval prev = null;
        for (Interval inter : intervals) {
            if (  prev==null || inter.start>prev.end ) {
                ret.add(inter);
                prev = inter;
            } else if (inter.end>prev.end) {
                // Modify the element already in list
                prev.end = inter.end;
            }
        }
        return ret;
    }
}


/*
 * Other solution with different Comparator Implementation
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervals)
            if (merged.isEmpty() || merged.getLast().end < interval.start)
                merged.add(interval);
            else merged.getLast().end = Math.max(merged.getLast().end, interval.end);
        return merged;
    }
}
