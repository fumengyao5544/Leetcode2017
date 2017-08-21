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
