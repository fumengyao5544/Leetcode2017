You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int row = rooms.length;
        int col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            if (isValid(rooms, x + 1, y) && rooms[x + 1][y] > rooms[x][y] + 1) {
                rooms[x + 1][y] = rooms[x][y] + 1;
                queue.offer(new int[] {x + 1, y});
            }
            if (isValid(rooms, x - 1, y) && rooms[x - 1][y] > rooms[x][y] + 1) {
                rooms[x - 1][y] = rooms[x][y] + 1;
                queue.offer(new int[] {x - 1, y});
            }
            if (isValid(rooms, x, y + 1) && rooms[x][y + 1] > rooms[x][y] + 1) {
                rooms[x][y + 1] = rooms[x][y] + 1;
                queue.offer(new int[] {x, y + 1});
            }
            if (isValid(rooms, x, y - 1) && rooms[x][y - 1] > rooms[x][y] + 1) {
                rooms[x][y - 1] = rooms[x][y] + 1;
                queue.offer(new int[] {x, y - 1});
            }
        }
    }

    public boolean isValid (int[][] rooms, int row, int col) {
        int m = rooms.length;
        int n = rooms[0].length;
        if (row >= 0 && row < m && col >= 0 && col < n) {
            return true;
        }else {
            return false;
        }
    }
}
