class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<int[]>[] forward = new List[n];
        List<int[]>[] backward = new List[n];
        for (int i = 0; i < n; i++) {
            forward[i] = new ArrayList<>();
            backward[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            forward[e[0]].add(new int[]{e[1], e[2]});
            backward[e[1]].add(new int[]{e[0], e[2]});
        }

        long[] d1 = dijkstra(forward, src1, n);
        long[] d2 = dijkstra(forward, src2, n);
        long[] dd = dijkstra(backward, dest, n);

        long INF = Long.MAX_VALUE / 4;
        long best = INF;

        for (int v = 0; v < n; v++) {
            if (d1[v] < INF && d2[v] < INF && dd[v] < INF) {
                best = Math.min(best, d1[v] + d2[v] + dd[v]);
            }
        }

        return best == INF ? -1 : best;
    }

    private long[] dijkstra(List<int[]>[] graph, int source, int n) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        heap.offer(new long[]{source, 0});

        while (!heap.isEmpty()) {
            long[] curr = heap.poll();
            int node = (int) curr[0];

            if (curr[1] > dist[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                long newDist = curr[1] + edge[1];
                if (newDist < dist[edge[0]]) {
                    dist[edge[0]] = newDist;
                    heap.offer(new long[]{edge[0], newDist});
                }
            }
        }

        return dist;
    }
}