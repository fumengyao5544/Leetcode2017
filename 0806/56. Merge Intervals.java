/*
56. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
	public List<Interval> merge(List<Interval> intervals) {
		// corner case
		if (intervals == null || intervals.size() < 2) {
			return intervals;
		}
		
		// normal case
		IntervalComparator comp = new IntervalComparator();
		Collections.sort(intervals, comp);
		ArrayList<Interval> result = new ArrayList<>();
		Interval prev = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			// 跟prev有交集
			if (prev.end >= curr.start) {
				Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
				prev = merged;
			}
			// 无交集
			else {
				result.add(prev);
				prev = curr;
			}
		}
		result.add(prev);
		return result;
	}
	
	class IntervalComparator implements Comparator<Interval>{
		public int compare(Interval i1, Interval i2) {
			return i1.start - i2.start;
		}
	};
}