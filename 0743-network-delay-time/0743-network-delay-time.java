class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            graph[t[0]].add(new int[]{t[1], t[2]});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        heap.offer(new int[]{k, 0});

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int node = curr[0];
            int d = curr[1];

            if (d > dist[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int newDist = d + edge[1];
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    heap.offer(new int[]{next, newDist});
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dist[i]);
        }

        return max;
    }
}