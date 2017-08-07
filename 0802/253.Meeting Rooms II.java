
// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

// For example,
// Given [[0, 30],[5, 10],[15, 20]],
// return 2.


public class Solution {
    class Point {
        int time;
        int flag;
        Point (int time, int flag) {
            this.time = time;
            this.flag = flag;
        }
    }
    public class MyComparator implements Comparator <Point>{
        public int compare (Point p1, Point p2){
            if ( p1.time != p2.time) {
                // 小的排在前面
                return p1.time - p2.time;
            }else {
                return p1.flag - p2.flag;
            }
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if ( intervals == null || intervals.length == 0) return 0;
        if ( intervals.length == 1) return 1;
        // 把每个interval转换成两个点 (start, 1) 和 (end, 0)
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Point(intervals[i].start, 1));
            list.add(new Point(intervals[i].end, 0));
        }
        Collections.sort(list, new MyComparator());
        int count = 0;
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).flag == 1) {
                count++;
            }else {
                count--;
            }
            result = Math.max(count, result);
        }
        return result;
    }
}
