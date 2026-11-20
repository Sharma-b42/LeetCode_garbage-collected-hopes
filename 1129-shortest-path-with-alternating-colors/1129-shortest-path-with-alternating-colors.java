class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] graph = new List[2][n];
        for (int c = 0; c < 2; c++) {
            for (int i = 0; i < n; i++) {
                graph[c][i] = new ArrayList<>();
            }
        }

        for (int[] e : redEdges) {
            graph[0][e[0]].add(e[1]);
        }
        for (int[] e : blueEdges) {
            graph[1][e[0]].add(e[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;
        answer[0] = 0;

        int steps = 0;

        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int nextColor = 1 - curr[1];

                for (int next : graph[nextColor][node]) {
                    if (!visited[next][nextColor]) {
                        visited[next][nextColor] = true;
                        queue.offer(new int[]{next, nextColor});
                        if (answer[next] == -1) {
                            answer[next] = steps;
                        }
                    }
                }
            }
        }

        return answer;
    }
}