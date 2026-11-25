class Solution {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> bfsQueue = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            List<Integer> row = grid.get(r);
            for (int c = 0; c < n; c++) {
                if (row.get(c) == 1) {
                    dist[r][c] = 0;
                    bfsQueue.offer(new int[]{r, c});
                }
            }
        }

        while (!bfsQueue.isEmpty()) {
            int[] curr = bfsQueue.poll();
            int r = curr[0];
            int c = curr[1];
            int nextDist = dist[r][c] + 1;

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = nextDist;
                    bfsQueue.offer(new int[]{nr, nc});
                }
            }
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        boolean[][] visited = new boolean[n][n];

        maxHeap.offer(new int[]{0, 0, dist[0][0]});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int r = curr[0];
            int c = curr[1];
            int safeness = curr[2];

            if (r == n - 1 && c == n - 1) {
                return safeness;
            }

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    maxHeap.offer(new int[]{nr, nc, Math.min(safeness, dist[nr][nc])});
                }
            }
        }

        return 0;
    }
}