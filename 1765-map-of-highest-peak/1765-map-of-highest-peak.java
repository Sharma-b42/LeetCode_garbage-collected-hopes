class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] height = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                } else {
                    height[i][j] = -1;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            for (int[] dir : directions) {
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];

                if (r >= 0 && r < m && c >= 0 && c < n && height[r][c] == -1) {
                    height[r][c] = height[cell[0]][cell[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        return height;
    }
}