
class Point implements Comparable<Point> {
    int x;
    int y;
    int step;
    String s;
    public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.step = Integer.MAX_VALUE;
            this.s = "";
    }
    public Point(int x, int y, int step, String s){
        this.x = x;
        this.y = y;
        this.step = step;
        this.s = s;
    }
    public int compareTo(Point p) {
        return step == p.step ? s.compareTo(p.s) : step - p.step;
    }
}
public class Solution {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(new Point(ball[0], ball[1], 0, ""));
        // 下，上，右，左
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        String[] dirs = {"d", "u", "r", "l"};
        Point[][] points = new Point[m][n];
        for (int i = 0; i < m * n; i++) {
            points[i / n][i % n] = new Point(i / n, i % n);
        }
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            if (points[x][y].compareTo(p) <= 0) continue; 
            points[x][y] = p;
            for (int i = 0; i < 4; i++) {
                int newX = p.x;
                int newY = p.y;
                int step = p.step;
                while (newX >= 0 && newX < m && newY >= 0 && newY < n && maze[newX][newY] == 0 && (newX != hole[0] || newY != hole[1])) {
                    newX += directions[i][0] ;
                    newY += directions[i][1]; 
                    step++;
                }
                if (newX != hole[0] || newY != hole[1]){
                    newX -= directions[i][0];
                    newY -= directions[i][1];
                    step--;
                }
                queue.offer(new Point(newX, newY, step, p.s + dirs[i]));
            }
        }
        if (points[hole[0]][hole[1]].step == Integer.MAX_VALUE) {
            return new String("impossible");
        }else{
            return points[hole[0]][hole[1]].s;
        }
    }
}
